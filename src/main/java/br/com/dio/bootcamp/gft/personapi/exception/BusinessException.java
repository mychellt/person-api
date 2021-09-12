package br.com.dio.bootcamp.gft.personapi.exception;

public class BusinessException extends Exception {
    public BusinessException() {
        super();
    }

    public BusinessException(String message) {
        super(message);
    }
}
