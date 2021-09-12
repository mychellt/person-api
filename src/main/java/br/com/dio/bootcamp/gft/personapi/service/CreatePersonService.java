package br.com.dio.bootcamp.gft.personapi.service;

import br.com.dio.bootcamp.gft.personapi.exception.BusinessException;
import br.com.dio.bootcamp.gft.personapi.exception.PersonAlreadyExistsException;
import br.com.dio.bootcamp.gft.personapi.model.Person;
import br.com.dio.bootcamp.gft.personapi.repository.PersonRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@AllArgsConstructor
public class CreatePersonService {
    private final PersonRepository repository;

    public Person execute(Person person) throws BusinessException {

        if(repository.existsByCpf(person.getCpf())) {
            throw new PersonAlreadyExistsException(String.format("Already exists a person with a CPF like %d", person.getCpf()));
        }

        return repository.save(person);
    }
}
