package br.com.dio.bootcamp.gft.personapi.exception;

public class PersonAlreadyExistsException extends BusinessException {
    public PersonAlreadyExistsException(String message) {
        super(message);
    }
}
