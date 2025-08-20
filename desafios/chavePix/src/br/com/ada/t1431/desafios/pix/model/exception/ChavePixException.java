package br.com.ada.t1431.desafios.pix.model.exception;

public class ChavePixException extends RuntimeException {
    public ChavePixException(String message) {
        super(message);
    }

    public ChavePixException(String message, Throwable cause) {
        super(message, cause);
    }
    public ChavePixException(Throwable cause) {
        super(cause);       
    }   
    
}