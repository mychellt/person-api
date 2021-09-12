package br.com.dio.bootcamp.gft.personapi.service;

import br.com.dio.bootcamp.gft.personapi.exception.BusinessException;
import br.com.dio.bootcamp.gft.personapi.exception.PersonNotFoundException;
import br.com.dio.bootcamp.gft.personapi.model.Person;
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
public class UpdatePersonServiceTest {
    @Mock
    private PersonRepository repository;

    @InjectMocks
    private UpdatePersonService service;

    @DisplayName("Shouldn't be able to update a person when give a null id")
    @Test
    public void shouldntBeAbleToUpdateAPersonWhenGiveANullId() {
        BusinessException businessException = assertThrows(BusinessException.class, () -> service.execute(Person.builder().id(null).build()));
        Assertions.assertTrue(businessException.getMessage().contains("Must be"));
    }

    @DisplayName("Shouldn't be able to update when no person found by given id")
    @Test
    public void shouldntBeAbleToUpdateWhenNoPersonFoundByGivenId() {
        Person fakePerson = PersonUtils.createFakePerson();

        when(repository.existsById(any(Long.class))).thenReturn(false);

        PersonNotFoundException personNotFoundException = assertThrows(PersonNotFoundException.class, () -> service.execute(fakePerson));
        verify(repository, times(1)).existsById(any(Long.class));
        Assertions.assertTrue(personNotFoundException.getMessage().contains("No person"));
    }

    @DisplayName("Should be able to update a person when give a valid id")
    @Test
    public void shouldBeAbleToUpdateAPersonWhenGiveAValidId() throws BusinessException {
        Person fakePerson = PersonUtils.createFakePerson();
        when(repository.existsById(any(Long.class))).thenReturn(true);
        when(repository.save(any(Person.class))).thenReturn(fakePerson);

        service.execute(fakePerson);

        verify(repository, times(1)).existsById(any(Long.class));
        verify(repository, times(1)).save(any(Person.class));
    }

}
