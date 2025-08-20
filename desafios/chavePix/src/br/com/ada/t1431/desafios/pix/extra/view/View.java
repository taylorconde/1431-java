package br.com.ada.t1431.desafios.pix.extra.view;

import br.com.ada.t1431.desafios.pix.extra.model.ChavePixComDadosBancarios;

public class View {

    public void imprimirAjuda() {
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

    public void showMessage(String message) {
        System.out.println(message);
    }

    public void showError(String message) {
        System.err.println(message);
    }

    public void showChave(ChavePixComDadosBancarios chave) {
        System.out.println("Chave encontrada: " + chave.getChavePix().getValor() +
                " | Instituição: " + chave.getDadosBancarios().codigoInstituicao() +
                " | Agência: " + chave.getDadosBancarios().agencia() +
                " | Conta: " + chave.getDadosBancarios().conta());
    }
}
