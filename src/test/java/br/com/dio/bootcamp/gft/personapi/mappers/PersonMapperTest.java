package br.com.dio.bootcamp.gft.personapi.mappers;

import br.com.dio.bootcamp.gft.personapi.controller.dto.SavePersonDTO;
import br.com.dio.bootcamp.gft.personapi.model.Person;
import br.com.dio.bootcamp.gft.personapi.utils.PersonUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import static org.junit.jupiter.api.Assertions.assertNotNull;


public class PersonMapperTest {


    @DisplayName("Should be able to retrieve a person entity from DTO")
    @Test
    public void shouldBeAbleToRetrieveAPersonEntityFromDTO() {
        PersonMapper mapper = Mappers.getMapper(PersonMapper.class);

        SavePersonDTO dto = PersonUtils.createPersonDTO();
        Person person = mapper.getEntity(dto);
        assertNotNull(person);
        assertNotNull(person.getBirthDate());
        assertNotNull(person.getFirstName());
        assertNotNull(person.getLastName());
    }
}
