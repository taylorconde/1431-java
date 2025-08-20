package br.com.ada.t1431.desafios.pix.validador.impl;

import br.com.ada.t1431.desafios.pix.model.exception.ChavePixFormatoInvalidoException;
import br.com.ada.t1431.desafios.pix.validador.ValidadorChavePix;

import java.util.regex.Pattern;

/**
 * Implementação de ValidadorChavePix para chaves do tipo E-mail.
 */
public class ValidadorEmail implements ValidadorChavePix {
    private static final String EMAIL_REGEX = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
    private static final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX);
    private static final String MENSAGEM_FORMATO_INVALIDO = "Formato de e-mail inválido.";
    
    @Override
    public void validar(String valor) throws ChavePixFormatoInvalidoException {
        if (valor == null || !EMAIL_PATTERN.matcher(valor).matches()) {
            throw new ChavePixFormatoInvalidoException(MENSAGEM_FORMATO_INVALIDO);
        }
    }
}