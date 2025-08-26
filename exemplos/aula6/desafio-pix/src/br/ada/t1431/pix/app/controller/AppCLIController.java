package br.ada.t1431.pix.app.controller;

import br.ada.t1431.pix.app.service.GerenciarChavePix;

import java.util.HashMap;
import java.util.Map;

public class AppCLIController {

    private final Map<String, String> mapaDeParametros = new HashMap<>();
    private final GerenciarChavePix gerenciarChavePix;

    public AppCLIController(GerenciarChavePix gerenciarChavePix) {
        this.gerenciarChavePix = gerenciarChavePix;
    }

    public void processar(String[] parametros) {
        if (parametros.length == 0) {
            System.out.println("Nenhum comando informado. Use -cmd cadastrar|listar|remover");
            return;
        }

        // Monta mapa de parâmetros: -x valor
        for (int i = 0; i < parametros.length - 1; i += 2) {
            String chave = parametros[i].replace("-", "");
            String valor = parametros[i + 1];
            mapaDeParametros.put(chave, valor);
        }

        String comando = mapaDeParametros.get("cmd");

        if (comando == null) {
            System.out.println("Informe o comando com -cmd (cadastrar, listar, remover)");
            return;
        }

        switch (comando.toLowerCase()) {
            case "cadastrar":
                cadastrar();
                break;
            case "listar":
                listar();
                break;
            case "remover":
                remover();
                break;
            default:
                System.out.println("Comando inválido: " + comando);
        }
    }

    private void cadastrar() {
        try {
            String instituicao = mapaDeParametros.get("i");
            String agencia = mapaDeParametros.get("a");
            String numeroDaConta = mapaDeParametros.get("cn");
            String tipoDaConta = mapaDeParametros.get("ct");
            String tipoDaChave = mapaDeParametros.get("t");
            String valorDaChave = mapaDeParametros.get("v");

            gerenciarChavePix.salvar(valorDaChave, tipoDaChave, instituicao, agencia, numeroDaConta, tipoDaConta);
            System.out.println(" Chave cadastrada com sucesso!");
        } catch (Exception e) {
            System.out.println(" Erro ao salvar a chave: " + e.getMessage());
        }
    }

    private void listar() {
        System.out.println("📋 Listando chaves (em breve: chamar service.listar())");
        // TODO: implementar usando gerenciarChavePix.listar()
    }

    private void remover() {
        System.out.println("🗑️ Removendo chave (em breve: chamar service.remover())");
        // TODO: implementar usando gerenciarChavePix.remover()
    }
}
