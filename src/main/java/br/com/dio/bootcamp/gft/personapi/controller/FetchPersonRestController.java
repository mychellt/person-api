package br.com.dio.bootcamp.gft.personapi.controller;

import br.com.dio.bootcamp.gft.personapi.controller.dto.FetchPersonDTO;
import br.com.dio.bootcamp.gft.personapi.exception.BusinessException;
import br.com.dio.bootcamp.gft.personapi.mappers.FetchPersonDTOMapper;
import br.com.dio.bootcamp.gft.personapi.model.Person;
import br.com.dio.bootcamp.gft.personapi.service.FetchPersonService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping(value = "/person")
public class FetchPersonRestController {
    private final FetchPersonService service;
    private final FetchPersonDTOMapper mapper;

    @GetMapping(value = "/{id}")
    public ResponseEntity<FetchPersonDTO> execute(@PathVariable Long id) throws BusinessException {
        Person person = service.execute(id);
        return ResponseEntity.ok().body(mapper.getDTO(person));
    }
}
