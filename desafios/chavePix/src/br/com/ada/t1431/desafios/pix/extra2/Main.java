package br.com.ada.t1431.desafios.pix.extra2;

import br.com.ada.t1431.desafios.pix.extra.model.exception.ChavePixExistenteException;
import br.com.ada.t1431.desafios.pix.extra.model.exception.ChavePixNaoExistenteException;
import br.com.ada.t1431.desafios.pix.extra.model.ChavePixComDadosBancarios;
import br.com.ada.t1431.desafios.pix.extra.service.GerenciadorChavesPix;
import br.com.ada.t1431.desafios.pix.extra.service.impl.GerenciadorChavesPixEmArquivo;
import br.com.ada.t1431.desafios.pix.model.exception.ChavePixFormatoInvalidoException;
import br.com.ada.t1431.desafios.pix.model.exception.ChavePixException;
import br.com.ada.t1431.desafios.pix.extra2.model.GeradorChaveAleatoria;
import br.com.ada.t1431.desafios.pix.extra2.model.impl.GeradorChaveAleatoriaDefaultImpl;
import br.com.ada.t1431.desafios.pix.extra2.model.ChavePixComDadosBancariosFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * Ponto de entrada principal para a aplicação de gerenciamento de chaves PIX via linha de comando (CLI).
 * <p>
 * Esta classe interpreta os argumentos da linha de comando, direciona para a operação correta (cadastrar, buscar, remover) e lida com as exceções, fornecendo feedback ao usuário.
 */
public class Main {
    private static final String DIRETORIO_DADOS = "chaves_pix_data";

    private final static GerenciadorChavesPix gerenciador = new GerenciadorChavesPixEmArquivo(DIRETORIO_DADOS);

    private final static GeradorChaveAleatoria geradorChaveAleatoria = new GeradorChaveAleatoriaDefaultImpl();

    /**
     * Método principal que inicia a aplicação.
     *
     * @param args os argumentos da linha de comando.
     */
    public static void main(String[] args) {
        if (args.length < 1) {
            imprimirAjuda();
            return;
        }

        String operacao = args[0].toLowerCase();

        try {
            switch (operacao) {
                case "cadastrar" -> cadastrarChave(args, gerenciador);
                case "buscar" -> buscarChave(args, gerenciador);
                case "remover" -> removerChave(args, gerenciador);
                case "help" -> imprimirAjuda();
                default -> {
                    System.err.println("Operação inválida: " + operacao);
                    imprimirAjuda();
                }
            }
        } catch (ChavePixExistenteException | ChavePixNaoExistenteException | ChavePixFormatoInvalidoException e) {
            System.err.println("Erro de negócio: " + e.getMessage());
        } catch (IllegalArgumentException | IllegalStateException e) {
            System.err.println("Erro de validação: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Ocorreu um erro inesperado: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static void cadastrarChave(String[] args, GerenciadorChavesPix gerenciador) throws ChavePixExistenteException, ChavePixFormatoInvalidoException, ChavePixNaoExistenteException {
        Map<String, String> params = parseArgs(args);

        ChavePixComDadosBancarios chave = ChavePixComDadosBancariosFactory.getInstance(geradorChaveAleatoria).criarChavePixComDadosBancarios(
                params.get("-i"), 
                params.get("-a"), 
                params.get("-c"), 
                params.get("-t"), 
                params.get("-v")
        );

        gerenciador.save(chave);
        System.out.println("Chave PIX '" + chave.getChavePix().getValor() + "' cadastrada com sucesso.");
    }

    private static void buscarChave(String[] args, GerenciadorChavesPix gerenciador) {
        if (args.length != 2) {
            System.err.println("Uso: buscar <valor_da_chave>");
            return;
        }
        String valorChave = args[1];
        Optional<ChavePixComDadosBancarios> chaveOptional = gerenciador.findBy(valorChave);

        chaveOptional.ifPresentOrElse(
                chave -> System.out.println("Chave encontrada: " + chave.getChavePix().getValor() +
                        " | Instituição: " + chave.getDadosBancarios().codigoInstituicao() +
                        " | Agência: " + chave.getDadosBancarios().agencia() +
                        " | Conta: " + chave.getDadosBancarios().conta()),
                () -> System.out.println("Chave PIX não encontrada.")
        );
    }

    private static void removerChave(String[] args, GerenciadorChavesPix gerenciador) throws ChavePixNaoExistenteException {
        if (args.length != 2) {
            System.err.println("Uso: remover <valor_da_chave>");
            return;
        }
        String valorChave = args[1];
        gerenciador.remove(valorChave);
        System.out.println("Chave PIX '" + valorChave + "' removida com sucesso.");
    }

    private static Map<String, String> parseArgs(String[] args) {
        Map<String, String> params = new HashMap<>();
        for (int i = 1; i < args.length; i += 2) {
            if (i + 1 < args.length) {
                params.put(args[i], args[i + 1]);
            }
        }
        return params;
    }

    private static void imprimirAjuda() {
        System.out.println("\n--- Manual de Uso do Gerenciador de Chaves PIX ---");
        System.out.println("\nSintaxe geral: java br.com.ada.t1431.desafios.pix.extra.Main <operacao> [argumentos]");
        System.out.println("\nOperações disponíveis:");

        System.out.println("\n  cadastrar");
        System.out.println("    Descrição: Cadastra uma nova chave PIX no sistema.");
        System.out.println("    Uso: cadastrar -i <instituicao> -a <agencia> -c <conta> -t <tipo> -v <valor>");
        System.out.println("    Parâmetros (obrigatórios e em qualquer ordem):");
        System.out.println("      -i: Código da instituição financeira (ex: 001).");
        System.out.println("      -a: Número da agência (ex: 1234).");
        System.out.println("      -c: Número da conta (ex: 56789-0).");
        System.out.println("      -t: Tipo da chave (CPF, CNPJ, EMAIL, CELULAR).");
        System.out.println("      -v: Valor da chave PIX.");

        System.out.println("\n  buscar <valor_da_chave>");
        System.out.println("    Descrição: Busca uma chave PIX pelo seu valor.");
        System.out.println("    Exemplo: buscar usuario@email.com");

        System.out.println("\n  remover <valor_da_chave>");
        System.out.println("    Descrição: Remove uma chave PIX do sistema pelo seu valor.");
        System.out.println("    Exemplo: remover 12345678901");

        System.out.println("\n  help");
        System.out.println("    Descrição: Exibe este manual de ajuda.");
    }
}