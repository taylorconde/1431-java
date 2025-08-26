package br.ada.t1431.pix.domain.validador.impl;

import br.ada.t1431.pix.domain.exception.ChavePixInvalidaException;
import br.ada.t1431.pix.domain.validador.Validador;

import java.util.UUID;

public class ValidadorAleatoria implements Validador {

    @Override
    public void validar(String valor) throws ChavePixInvalidaException {
        try {
            UUID chave = UUID.fromString(valor); // tenta converter
            if (chave.version() != 4) {
                throw new ChavePixInvalidaException("Chave aleatória deve ser um UUID v4 válido.");
            }
        } catch (IllegalArgumentException e) {
            throw new ChavePixInvalidaException("Chave aleatória inválida. Deve estar no formato UUID.");
        }
    }
}
