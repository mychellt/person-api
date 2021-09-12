package br.com.dio.bootcamp.gft.personapi.controller;

import br.com.dio.bootcamp.gft.personapi.controller.dto.FetchPersonDTO;
import br.com.dio.bootcamp.gft.personapi.exception.PersonNotFoundException;
import br.com.dio.bootcamp.gft.personapi.mappers.FetchPersonDTOMapper;
import br.com.dio.bootcamp.gft.personapi.model.Person;
import br.com.dio.bootcamp.gft.personapi.service.FetchPersonService;
import br.com.dio.bootcamp.gft.personapi.utils.PersonUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.time.ZoneId;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(value = FetchPersonRestController.class)
public class FetchPersonRestControllerTest {

    @MockBean
    private FetchPersonService service;

    @MockBean
    private FetchPersonDTOMapper mapper;

    @Autowired
    private MockMvc mvc;

    @DisplayName("Should throw an exception when the person is not found")
    @Test
    public void shouldThrowAnExceptionWhenThePersonIsNotFound() throws Exception {
        when(service.execute(any(Long.class))).thenThrow(PersonNotFoundException.class);
        when(mapper.getDTO(any(Person.class))).thenReturn(FetchPersonDTO.builder().build());

        mvc.perform(get("/person/1")
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andExpect(status()
                        .isNotFound());
    }

    @DisplayName("Should be able to return a person given a valid id")
    @Test
    public void shouldBeAbleToReturnAPersonGivenAValidId() throws Exception {
        when(service.execute(any(Long.class))).thenReturn(PersonUtils.createFakePerson());
        when(mapper.getDTO(any(Person.class))).thenReturn(FetchPersonDTO.builder()
                        .birthDate(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli())
                        .cpf(1L)
                        .firstName("John")
                        .lastName("Doe")
                .build());

        mvc.perform(get("/person/1")
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andExpect(status()
                        .isOk());
    }
}
