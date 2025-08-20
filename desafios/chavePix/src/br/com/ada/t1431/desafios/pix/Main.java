package br.com.ada.t1431.desafios.pix;

import br.com.ada.t1431.desafios.pix.model.ChavePix;
import br.com.ada.t1431.desafios.pix.model.exception.ChavePixFormatoInvalidoException;

class Main{
    public static void main(String[] args) {

        if (args.length != 2) {
            System.out.println("Uso: java Main <tipoChave> <valorChave>");
            return;
        }
        
        String tipoChave = args[0];
        String valorChave = args[1];

        try {
            ChavePix chavePix = ChavePix.of(tipoChave, valorChave);
            chavePix.validar();
            System.out.println("Chave Pix válida.");
        } catch (ChavePixFormatoInvalidoException e) {
            System.out.println("Chave Pix inválida: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println("Erro: " + e.getMessage());
        }

    }
}