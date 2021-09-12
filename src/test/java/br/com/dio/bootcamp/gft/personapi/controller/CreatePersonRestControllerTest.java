package br.com.dio.bootcamp.gft.personapi.controller;

import br.com.dio.bootcamp.gft.personapi.controller.dto.SavePersonDTO;
import br.com.dio.bootcamp.gft.personapi.mappers.PersonMapper;
import br.com.dio.bootcamp.gft.personapi.model.Person;
import br.com.dio.bootcamp.gft.personapi.service.CreatePersonService;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(value = CreatePersonRestController.class)
public class CreatePersonRestControllerTest {
    @MockBean
    private CreatePersonService service;

    @MockBean
    private PersonMapper mapper;

    @Autowired
    private MockMvc mvc;

    @DisplayName("Should be able to create a new person")
    @Test
    public void shouldBeAbleToCreateANewPerson() throws Exception {
        when(service.execute(any(Person.class))).thenReturn(PersonUtils.createFakePerson());
        when(mapper.getEntity(any(SavePersonDTO.class))).thenReturn(PersonUtils.createFakePerson());

        mvc.perform(post("/person")
                        .content(new ObjectMapper().writeValueAsBytes(PersonUtils.createPersonDTO()))
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andExpect(status()
                        .isOk());

        verify(service, times(1)).execute(any(Person.class));
        verify(mapper, times(1)).getEntity(any(SavePersonDTO.class));

    }

    @DisplayName("Shouldn't be able to create a new person when first name is not provided ")
    @Test
    public void shouldntBeAbleToCreateANewPersonWhenAFirstNameIsNotProvided() throws Exception {
        when(service.execute(any(Person.class))).thenReturn(PersonUtils.createFakePerson());
        when(mapper.getEntity(any(SavePersonDTO.class))).thenReturn(PersonUtils.createFakePerson());

        mvc.perform(post("/person")
                        .content(new ObjectMapper().writeValueAsBytes(PersonUtils.createPersonDTOWithoutFirstName()))
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andExpect(status()
                        .isBadRequest());

    }

    @DisplayName("Shouldn't be able to create a new person when last name is not provided ")
    @Test
    public void shouldntBeAbleToCreateANewPersonWhenLastNameIsNotProvided() throws Exception {
        when(service.execute(any(Person.class))).thenReturn(PersonUtils.createFakePerson());
        when(mapper.getEntity(any(SavePersonDTO.class))).thenReturn(PersonUtils.createFakePerson());

        mvc.perform(post("/person")
                        .content(new ObjectMapper().writeValueAsBytes(PersonUtils.createPersonDTOWithoutLastName()))
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andExpect(status()
                        .isBadRequest());

    }


    @DisplayName("Shouldn't be able to create a new person when CPF number is not provided ")
    @Test
    public void shouldntBeAbleToCreateANewPersonWhenCPFIsNotProvided() throws Exception {
        when(service.execute(any(Person.class))).thenReturn(PersonUtils.createFakePerson());
        when(mapper.getEntity(any(SavePersonDTO.class))).thenReturn(PersonUtils.createFakePerson());

        mvc.perform(post("/person")
                        .content(new ObjectMapper().writeValueAsBytes(PersonUtils.createPersonDTOWithoutCPFNumber()))
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andExpect(status()
                        .isBadRequest());

    }

    @DisplayName("Shouldn't be able to create a new person when CPF number is not provided ")
    @Test
    public void shouldntBeAbleToCreateANewPersonWhenBirthDateIsNotProvided() throws Exception {
        when(service.execute(any(Person.class))).thenReturn(PersonUtils.createFakePerson());
        when(mapper.getEntity(any(SavePersonDTO.class))).thenReturn(PersonUtils.createFakePerson());

        mvc.perform(post("/person")
                        .content(new ObjectMapper().writeValueAsBytes(PersonUtils.createPersonDTOWithoutBirthDate()))
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andExpect(status()
                        .isBadRequest());

    }
}
