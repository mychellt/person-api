package br.com.dio.bootcamp.gft.personapi.controller;

import br.com.dio.bootcamp.gft.personapi.controller.dto.FetchPersonDTO;
import br.com.dio.bootcamp.gft.personapi.controller.dto.SavePersonDTO;
import br.com.dio.bootcamp.gft.personapi.exception.BusinessException;
import br.com.dio.bootcamp.gft.personapi.mappers.FetchPersonDTOMapper;
import br.com.dio.bootcamp.gft.personapi.mappers.PersonMapper;
import br.com.dio.bootcamp.gft.personapi.model.Person;
import br.com.dio.bootcamp.gft.personapi.service.UpdatePersonService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@AllArgsConstructor
@RestController
@RequestMapping(value = "/person")
public class UpdatePersonRestController {
    private final UpdatePersonService service;
    private final PersonMapper mapper;
    private final FetchPersonDTOMapper fetchMapper;


    @PutMapping(value = "/{id}")
    public ResponseEntity<FetchPersonDTO> execute(@Valid @RequestBody SavePersonDTO body, @PathVariable Long id) throws BusinessException {
        Person person = mapper.getEntity(body);
        person.setId(id);
        Person savedPerson = service.execute(person);
        return ResponseEntity.ok().body(fetchMapper.getDTO(savedPerson));

    }
}
