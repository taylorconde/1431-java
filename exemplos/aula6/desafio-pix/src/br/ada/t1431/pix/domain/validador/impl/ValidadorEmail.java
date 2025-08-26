package br.ada.t1431.pix.domain.validador.impl;
import br.ada.t1431.pix.domain.validador.Validador;
import br.ada.t1431.pix.domain.exception.ChavePixInvalidaException;

public class ValidadorEmail implements Validador {
    @Override
    public void validar(String valor) throws ChavePixInvalidaException {

        String email = valor.trim();
        String regexEmail = "^[a-zA-Z0-9à-úÀ-Ú._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";

        if(!email.matches(regexEmail)){
            throw new ChavePixInvalidaException("EMAIL invalido");
        };
    }
}
