package br.com.dio.bootcamp.gft.personapi.controller;


import br.com.dio.bootcamp.gft.personapi.exception.PersonNotFoundException;
import br.com.dio.bootcamp.gft.personapi.service.DeletePersonService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping(value = "/person")
public class DeletePersonRestController {

    private final DeletePersonService service;

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> execute(@PathVariable Long id) throws PersonNotFoundException {
        service.execute(id);
        return ResponseEntity.noContent().build();
    }

}
