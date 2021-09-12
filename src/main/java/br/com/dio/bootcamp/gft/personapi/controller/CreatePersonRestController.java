package br.com.dio.bootcamp.gft.personapi.controller;

import br.com.dio.bootcamp.gft.personapi.controller.dto.SavePersonDTO;
import br.com.dio.bootcamp.gft.personapi.exception.BusinessException;
import br.com.dio.bootcamp.gft.personapi.mappers.PersonMapper;
import br.com.dio.bootcamp.gft.personapi.model.Person;
import br.com.dio.bootcamp.gft.personapi.service.CreatePersonService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@AllArgsConstructor
@RestController
@RequestMapping(value = "/person")
public class CreatePersonRestController {
    private final CreatePersonService service;
    private final PersonMapper mapper;

    @PostMapping
    public ResponseEntity<Person> execute(@Valid @RequestBody SavePersonDTO body) throws BusinessException {
        Person person = service.execute(mapper.getEntity(body));
        return ResponseEntity.ok().body(person);
    }
}
