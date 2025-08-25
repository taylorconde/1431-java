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
        for (int i = 0; i < parametros.length - 1; i++) {
            mapaDeParametros.put(parametros[i].replace("-", ""), parametros[i + 1]);
        }

        String comando = mapaDeParametros.get("cmd");

        if (comando.equals("salvar")) {
            String instituicao = mapaDeParametros.get("i");
            String agencia = mapaDeParametros.get("a");
            String numeroDaConta = mapaDeParametros.get("cn");
            String tipoDaConta = mapaDeParametros.get("ct");
            String tipoDaChave = mapaDeParametros.get("t");
            String valorDaChave = mapaDeParametros.get("v");
            try {
                gerenciarChavePix.salvar(valorDaChave, tipoDaChave, instituicao, agencia, numeroDaConta, tipoDaConta);
                System.out.println("Chave cadastrada com sucesso!");
            } catch (Exception e) {
                System.out.println("Erro ao salvar a chave." + e.getMessage());
            }
        }

    }

    // -i: instituicao
    // -cn: numero da conta
    // -a: agencia
    // -ct: tipo de conta
    // -t: tipo da chave
    // -v: valor da chave
    // -cmd comando

    // java Main -a asaas -b sadsds -c dfddfd -d sdsdsfs

    // Main (entrada dos parametros) -> Controller -> Service -> Usar o dominio (modelo) e orquestar (se necessario) com o Repository -> Repository

}
