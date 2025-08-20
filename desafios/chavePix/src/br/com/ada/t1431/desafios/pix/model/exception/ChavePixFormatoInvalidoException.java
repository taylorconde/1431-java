package br.com.ada.t1431.desafios.pix.model.exception;

public class ChavePixFormatoInvalidoException extends ChavePixException  {
    public ChavePixFormatoInvalidoException(String message) {
        super(message);
    }

    public ChavePixFormatoInvalidoException(String message, Throwable cause) {
        super(message, cause);
    }
    public ChavePixFormatoInvalidoException(Throwable cause) {
        super(cause);       
    }   
}   