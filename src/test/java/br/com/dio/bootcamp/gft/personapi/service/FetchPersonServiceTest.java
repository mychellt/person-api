package br.com.dio.bootcamp.gft.personapi.service;

import br.com.dio.bootcamp.gft.personapi.exception.BusinessException;
import br.com.dio.bootcamp.gft.personapi.exception.PersonNotFoundException;
import br.com.dio.bootcamp.gft.personapi.repository.PersonRepository;
import br.com.dio.bootcamp.gft.personapi.utils.PersonUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class FetchPersonServiceTest {

    @Mock
    private PersonRepository repository;

    @InjectMocks
    private FetchPersonService service;

    @DisplayName("Shouldn't be able to fetch a person when give a invalid id")
    @Test
    public void shouldntBeAbleToFetchAPersonWhenGiveAInvalidId() {
        when(repository.findById(any(Long.class))).thenReturn(Optional.empty());

        PersonNotFoundException personNotFoundException = assertThrows(PersonNotFoundException.class, () -> service.execute(1L));

        verify(repository, times(1)).findById(any(Long.class));

        Assertions.assertTrue(personNotFoundException.getMessage().contains("No person"));

    }

    @DisplayName("Should be able to fetch a person when give a valid id")
    @Test
    public void shouldBeAbleToFetchAPersonWhenGiveAValidId() throws BusinessException {
        when(repository.findById(any(Long.class))).thenReturn(Optional.of(PersonUtils.createFakePerson()));
        service.execute(1L);
        verify(repository, times(1)).findById(any(Long.class));
    }
}
