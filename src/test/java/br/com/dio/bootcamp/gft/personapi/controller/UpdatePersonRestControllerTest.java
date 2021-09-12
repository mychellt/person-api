package br.com.dio.bootcamp.gft.personapi.controller;


import br.com.dio.bootcamp.gft.personapi.controller.dto.FetchPersonDTO;
import br.com.dio.bootcamp.gft.personapi.controller.dto.SavePersonDTO;
import br.com.dio.bootcamp.gft.personapi.exception.PersonNotFoundException;
import br.com.dio.bootcamp.gft.personapi.mappers.FetchPersonDTOMapper;
import br.com.dio.bootcamp.gft.personapi.mappers.PersonMapper;
import br.com.dio.bootcamp.gft.personapi.model.Person;
import br.com.dio.bootcamp.gft.personapi.service.UpdatePersonService;
import br.com.dio.bootcamp.gft.personapi.utils.PersonUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(value = UpdatePersonRestController.class)
public class UpdatePersonRestControllerTest {

    @MockBean
    private UpdatePersonService service;

    @MockBean
    private PersonMapper mapper;

    @MockBean
    private FetchPersonDTOMapper fetchMapper;

    @Autowired
    private MockMvc mvc;

    @DisplayName("Should throw an exception when the person is not found")
    @Test
    public void shouldThrowAnExceptionWhenThePersonIsNotFound() throws Exception {
        doThrow(new PersonNotFoundException()).when(service).execute(any(Person.class));
        when(mapper.getEntity(any(SavePersonDTO.class))).thenReturn(PersonUtils.createFakePerson());
        when(fetchMapper.getDTO(any(Person.class))).thenReturn(FetchPersonDTO.builder().build());

        mvc.perform(put("/person/1")
                        .content(new ObjectMapper().writeValueAsBytes(PersonUtils.createPersonDTO()))
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andExpect(status()
                        .isNotFound());
    }

    @DisplayName("Should be able to update a person")
    @Test
    public void shouldBeAbleToUpdateAPerson() throws Exception {
        Person fakePerson = PersonUtils.createFakePerson();
        when(mapper.getEntity(any(SavePersonDTO.class))).thenReturn(fakePerson);
        when(service.execute(any(Person.class))).thenReturn(fakePerson);
        when(fetchMapper.getDTO(any(Person.class))).thenReturn(FetchPersonDTO.builder().build());

        mvc.perform(put("/person/1")
                        .content(new ObjectMapper().writeValueAsBytes(PersonUtils.createPersonDTO()))
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andExpect(status()
                        .isOk());

        verify(service, times(1)).execute(any(Person.class));
        verify(mapper, times(1)).getEntity(any(SavePersonDTO.class));
        verify(fetchMapper, times(1)).getDTO(any(Person.class));

    }
}
