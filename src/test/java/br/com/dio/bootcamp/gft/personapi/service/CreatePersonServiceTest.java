package br.com.dio.bootcamp.gft.personapi.service;

import br.com.dio.bootcamp.gft.personapi.exception.BusinessException;
import br.com.dio.bootcamp.gft.personapi.exception.PersonAlreadyExistsException;
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

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class CreatePersonServiceTest {

    @Mock
    private PersonRepository repository;

    @InjectMocks
    private CreatePersonService service;


    @DisplayName("Shouldn't be able to create a new person when already exists one with the given cpf number")
    @Test
    public void shouldntBeAbleToCreateANewPersonWhenAlreadyExistsOneWithTheGivenCPFNumber() {
        when(repository.existsByCpf(any(Long.class))).thenReturn(true);

        PersonAlreadyExistsException personAlreadyExistsException = assertThrows(PersonAlreadyExistsException.class, () -> service.execute(PersonUtils.createFakePerson()));

        verify(repository, times(1)).existsByCpf(any(Long.class));

        Assertions.assertTrue(personAlreadyExistsException.getMessage().contains("Already exists"));

    }

    @DisplayName("Should be able to create a new person")
    @Test
    public void shouldBeAbleToCreateANewPerson() throws BusinessException {
        when(repository.existsByCpf(any(Long.class))).thenReturn(false);
        Person fakePerson = PersonUtils.createFakePerson();
        when(repository.save(any(Person.class))).thenReturn(fakePerson);

        service.execute(fakePerson);

        verify(repository, times(1)).existsByCpf(any(Long.class));
        verify(repository, times(1)).save(any(Person.class));

    }

}
