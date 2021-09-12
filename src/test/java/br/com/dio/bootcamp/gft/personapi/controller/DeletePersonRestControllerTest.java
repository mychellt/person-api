package br.com.dio.bootcamp.gft.personapi.controller;

import br.com.dio.bootcamp.gft.personapi.exception.PersonNotFoundException;
import br.com.dio.bootcamp.gft.personapi.service.DeletePersonService;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(value = DeletePersonRestController.class)
public class DeletePersonRestControllerTest {
    @MockBean
    private DeletePersonService service;

    @Autowired
    private MockMvc mvc;

    @DisplayName("Should throw an exception when the person is not found")
    @Test
    public void shouldThrowAnExceptionWhenThePersonIsNotFound() throws Exception {
        doThrow(new PersonNotFoundException()).when(service).execute(any(Long.class));

        mvc.perform(delete("/person/1")
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andExpect(status()
                        .isNotFound());
    }

    @DisplayName("Should be able to remover the person when is given a valid id")
    @Test
    public void shouldBeAbleToRemoveThePersonWhenIsGivenAValidId() throws Exception {
        doAnswer(invocationOnMock -> null).when(service).execute(any(Long.class));

        mvc.perform(delete("/person/1")
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andExpect(status()
                        .isNoContent());

        verify(service, times(1)).execute(any(Long.class));
    }
}
