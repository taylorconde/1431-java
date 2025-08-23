package model.validador;

import model.exception.ChavePixInvalidaException;

public interface Validador {

    void validar(String valor) throws ChavePixInvalidaException;

}