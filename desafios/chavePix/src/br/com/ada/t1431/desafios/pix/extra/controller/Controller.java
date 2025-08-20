package br.com.ada.t1431.desafios.pix.extra.controller;

import br.com.ada.t1431.desafios.pix.extra.model.ChavePixComDadosBancarios;
import br.com.ada.t1431.desafios.pix.extra.model.exception.ChavePixExistenteException;
import br.com.ada.t1431.desafios.pix.extra.model.exception.ChavePixNaoExistenteException;
import br.com.ada.t1431.desafios.pix.extra.service.GerenciadorChavesPix;
import br.com.ada.t1431.desafios.pix.model.exception.ChavePixFormatoInvalidoException;
import br.com.ada.t1431.desafios.pix.extra.view.View;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class Controller {

    protected final GerenciadorChavesPix gerenciador;
    protected final View view;

    public Controller(GerenciadorChavesPix gerenciador, View view) {
        this.gerenciador = gerenciador;
        this.view = view;
    }

    public void processar(String[] args) {
        if (args.length < 1) {
            view.imprimirAjuda();
            return;
        }

        String operacao = args[0].toLowerCase();

        try {
            switch (operacao) {
                case "cadastrar" -> cadastrarChave(args);
                case "buscar" -> buscarChave(args);
                case "remover" -> removerChave(args);
                case "help" -> view.imprimirAjuda();
                default -> {
                    view.showError("Operação inválida: " + operacao);
                    view.imprimirAjuda();
                }
            }
        } catch (ChavePixExistenteException | ChavePixNaoExistenteException | ChavePixFormatoInvalidoException e) {
            view.showError("Erro de negócio: " + e.getMessage());
        } catch (IllegalArgumentException | IllegalStateException e) {
            view.showError("Erro de validação: " + e.getMessage());
        } catch (Exception e) {
            view.showError("Ocorreu um erro inesperado: " + e.getMessage());
            e.printStackTrace();
        }
    }

    protected void cadastrarChave(String[] args) throws ChavePixExistenteException, ChavePixFormatoInvalidoException, ChavePixNaoExistenteException {
        Map<String, String> params = parseArgs(args);

        ChavePixComDadosBancarios chave = ChavePixComDadosBancarios.builder()
                .codigoInstituicao(params.get("-i"))
                .agencia(params.get("-a"))
                .conta(params.get("-c"))
                .tipoChave(params.get("-t"))
                .valorChave(params.get("-v"))
                .build();

        gerenciador.save(chave);
        view.showMessage("Chave PIX '" + chave.getChavePix().getValor() + "' cadastrada com sucesso.");
    }

    protected void buscarChave(String[] args) {
        if (args.length != 2) {
            view.showError("Uso: buscar <valor_da_chave>");
            return;
        }
        String valorChave = args[1];
        Optional<ChavePixComDadosBancarios> chaveOptional = gerenciador.findBy(valorChave);

        chaveOptional.ifPresentOrElse(
                view::showChave,
                () -> view.showMessage("Chave PIX não encontrada.")
        );
    }

    protected void removerChave(String[] args) throws ChavePixNaoExistenteException {
        if (args.length != 2) {
            view.showError("Uso: remover <valor_da_chave>");
            return;
        }
        String valorChave = args[1];
        gerenciador.remove(valorChave);
        view.showMessage("Chave PIX '" + valorChave + "' removida com sucesso.");
    }

    protected Map<String, String> parseArgs(String[] args) {
        Map<String, String> params = new HashMap<>();
        for (int i = 1; i < args.length; i += 2) {
            if (i + 1 < args.length) {
                params.put(args[i], args[i + 1]);
            }
        }
        return params;
    }
}
