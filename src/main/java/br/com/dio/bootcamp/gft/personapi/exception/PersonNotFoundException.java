package br.com.dio.bootcamp.gft.personapi.exception;

public class PersonNotFoundException extends BusinessException {
    public PersonNotFoundException() {
        super();
    }

    public PersonNotFoundException(String message) {
        super(message);
    }

}
