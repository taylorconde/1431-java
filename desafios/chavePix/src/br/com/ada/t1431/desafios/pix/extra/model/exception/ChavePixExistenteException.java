package br.com.ada.t1431.desafios.pix.extra.model.exception;

import br.com.ada.t1431.desafios.pix.model.exception.ChavePixException;

public class ChavePixExistenteException extends ChavePixException {
    public ChavePixExistenteException(String message) {
        super(message);
    }

    public ChavePixExistenteException(String message, Throwable cause) {
        super(message, cause);
    }
    public ChavePixExistenteException(Throwable cause) {
        super(cause);       
    }
}