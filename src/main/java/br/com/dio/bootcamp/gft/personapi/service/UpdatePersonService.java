package br.com.dio.bootcamp.gft.personapi.service;

import br.com.dio.bootcamp.gft.personapi.exception.BusinessException;
import br.com.dio.bootcamp.gft.personapi.exception.PersonNotFoundException;
import br.com.dio.bootcamp.gft.personapi.model.Person;
import br.com.dio.bootcamp.gft.personapi.repository.PersonRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

@AllArgsConstructor
@Service
public class UpdatePersonService {

    private final PersonRepository repository;

    public Person execute(Person person) throws BusinessException {
        if(Objects.isNull(person.getId())) {
            throw new BusinessException("Must be give a valid id");
        }

        if(!repository.existsById(person.getId())) {
            throw new PersonNotFoundException("No person found with id " + person.getId());
        }
        return repository.save(person);
    }
}
