package br.com.ada.t1431.desafios.pix.validador.impl;

import br.com.ada.t1431.desafios.pix.model.exception.ChavePixFormatoInvalidoException;
import br.com.ada.t1431.desafios.pix.validador.ValidadorChavePix;

/**
 * Implementação de ValidadorChavePix para chaves do tipo Celular.
 * Formato esperado: +55 (DDD) 9XXXX-XXXX, apenas números.
 */
public class ValidadorCelular implements ValidadorChavePix {
    @Override
    public void validar(String valor) throws ChavePixFormatoInvalidoException {
        final String REGEX_FORMATO_CELULAR = "^\\d{11}$";
        final String MENSAGEM_FORMATO_INVALIDO = "Formato de celular inválido. Esperado 11 dígitos numéricos (ex: 11987654321).";

        if (valor == null || !valor.matches(REGEX_FORMATO_CELULAR)) {
            throw new ChavePixFormatoInvalidoException(MENSAGEM_FORMATO_INVALIDO);
        }
    }
}