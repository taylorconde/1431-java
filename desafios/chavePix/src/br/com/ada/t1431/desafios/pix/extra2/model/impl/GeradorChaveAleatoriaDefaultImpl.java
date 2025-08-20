package br.com.ada.t1431.desafios.pix.extra2.model.impl;

import br.com.ada.t1431.desafios.pix.extra2.model.GeradorChaveAleatoria;

/**
 * Implementação padrão da interface {@link GeradorChaveAleatoria}.
 * Utiliza a classe {@link java.util.UUID} para gerar uma chave aleatória universalmente única.
 */
public class GeradorChaveAleatoriaDefaultImpl implements GeradorChaveAleatoria {

    /**
     * Gera uma nova chave PIX aleatória no formato UUID.
     *
     * @return uma String representando a chave aleatória gerada.
     */
    @Override
    public String gerarChaveAleatoria() {
        return java.util.UUID.randomUUID().toString();
    }
}