package br.ada.t1431.pix.domain.exception;

public class ChavePixInvalidaException extends RuntimeException {

    public ChavePixInvalidaException() {
    }

    public ChavePixInvalidaException(String message) {
        super(message);
    }

    public ChavePixInvalidaException(String message, Throwable cause) {
        super(message, cause);
    }

    public ChavePixInvalidaException(Throwable cause) {
        super(cause);
    }

    public ChavePixInvalidaException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
