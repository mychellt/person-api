package br.com.dio.bootcamp.gft.personapi.service;

import br.com.dio.bootcamp.gft.personapi.exception.BusinessException;
import br.com.dio.bootcamp.gft.personapi.exception.PersonNotFoundException;
import br.com.dio.bootcamp.gft.personapi.model.Person;
import br.com.dio.bootcamp.gft.personapi.repository.PersonRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;


@AllArgsConstructor
@Service
public class FetchPersonService {
    private final PersonRepository repository;

    public Person execute(Long id) throws BusinessException {
        Optional<Person> person = repository.findById(id);
        if(person.isEmpty()) {
            throw new PersonNotFoundException("No person found with id " + id);
        }
        return person.get();
    }
}
