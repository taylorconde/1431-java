package br.com.ada.t1431.desafios.pix.extra2.model.validador;

import br.com.ada.t1431.desafios.pix.model.exception.ChavePixFormatoInvalidoException;
import br.com.ada.t1431.desafios.pix.validador.ValidadorChavePix;

/**
 * Implementação de {@link ValidadorChavePix} para chaves do tipo Aleatória.
 * Valida se a chave está no formato de um UUID (Universally Unique Identifier).
 */
public class ValidadorChaveAleatoria implements ValidadorChavePix {

    private static final String REGEX_UUID = "^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}$";
    private static final String MENSAGEM_FORMATO_INVALIDO = "Formato de chave aleatória inválido. Esperado um UUID (ex: 123e4567-e89b-12d3-a456-426614174000).";
    private static final String MENSAGEM_NULA_OU_VAZIA = "Chave aleatória não pode ser nula ou vazia.";

    @Override
    public void validar(String valor) throws ChavePixFormatoInvalidoException {
        if (valor == null || valor.isBlank()) {
            throw new ChavePixFormatoInvalidoException(MENSAGEM_NULA_OU_VAZIA);
        }

        if (!valor.matches(REGEX_UUID)) {
            throw new ChavePixFormatoInvalidoException(MENSAGEM_FORMATO_INVALIDO);
        }
    }
}