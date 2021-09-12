package br.com.dio.bootcamp.gft.personapi.service;

import br.com.dio.bootcamp.gft.personapi.exception.PersonNotFoundException;
import br.com.dio.bootcamp.gft.personapi.repository.PersonRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

@AllArgsConstructor
@Repository
public class DeletePersonService {
    private final PersonRepository repository;

    public void execute(Long id) throws PersonNotFoundException {
        if(!repository.existsByCpf(id)){
            throw new PersonNotFoundException("No person found with id " + 1);
        }
        repository.deleteById(id);
    }
}
