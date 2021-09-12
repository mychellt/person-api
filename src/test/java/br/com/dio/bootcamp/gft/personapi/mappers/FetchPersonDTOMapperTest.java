package br.com.dio.bootcamp.gft.personapi.mappers;

import br.com.dio.bootcamp.gft.personapi.controller.dto.FetchPersonDTO;
import br.com.dio.bootcamp.gft.personapi.model.Person;
import br.com.dio.bootcamp.gft.personapi.utils.PersonUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class FetchPersonDTOMapperTest {

    @DisplayName("Should be able to retrieve a DTO from person entity")
    @Test
    public void shoudlBeAbleToRetrieveADTOFromPersonEntity() {
        FetchPersonDTOMapper mapper = Mappers.getMapper(FetchPersonDTOMapper.class);
        Person fakePerson = PersonUtils.createFakePerson();
        FetchPersonDTO dto = mapper.getDTO(fakePerson);
        assertNotNull(dto);
        assertNotNull(dto.getBirthDate());
        assertNotNull(dto.getFirstName());
        assertNotNull(dto.getLastName());
        assertNotNull(dto.getCpf());
    }
}
