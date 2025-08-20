package br.com.ada.t1431.desafios.pix.extra.model.exception;

import br.com.ada.t1431.desafios.pix.model.exception.ChavePixException;

public class ChavePixNaoExistenteException extends ChavePixException {
    public ChavePixNaoExistenteException(String message) {
        super(message);
    }

    public ChavePixNaoExistenteException(String message, Throwable cause) {
        super(message, cause);
    }

    public ChavePixNaoExistenteException(Throwable cause) {
        super(cause);
    }
}       