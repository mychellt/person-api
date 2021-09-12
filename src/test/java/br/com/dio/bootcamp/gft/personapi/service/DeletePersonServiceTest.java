package br.com.dio.bootcamp.gft.personapi.service;

import br.com.dio.bootcamp.gft.personapi.exception.PersonNotFoundException;
import br.com.dio.bootcamp.gft.personapi.repository.PersonRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class DeletePersonServiceTest {

    @Mock
    private PersonRepository repository;

    @InjectMocks
    private DeletePersonService service;

    @DisplayName("Shouldn't be able to remove a person when give a invalid id")
    @Test
    public void shouldntBeAbleToRemoveAPersonWhenGiveAInvalidId() {
        when(repository.existsByCpf(any(Long.class))).thenReturn(false);

        PersonNotFoundException personNotFoundException = assertThrows(PersonNotFoundException.class, () -> service.execute(1L));

        verify(repository, times(1)).existsByCpf(any(Long.class));

        Assertions.assertTrue(personNotFoundException.getMessage().contains("No person"));

    }

    @DisplayName("Should be able to remove a person when give a valid id")
    @Test
    public void shouldBeAbleToRemoveAPersonWhenGiveAValidId() throws PersonNotFoundException {
        when(repository.existsByCpf(any(Long.class))).thenReturn(true);
        doAnswer(invocationOnMock -> null).when(repository).deleteById(any(Long.class));

        service.execute(1L);

        verify(repository, times(1)).existsByCpf(any(Long.class));
        verify(repository, times(1)).deleteById(any(Long.class));
    }
}
