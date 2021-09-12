package br.com.dio.bootcamp.gft.personapi.mappers;

import br.com.dio.bootcamp.gft.personapi.controller.dto.FetchPersonDTO;
import br.com.dio.bootcamp.gft.personapi.model.Person;
import org.mapstruct.*;

import java.time.ZoneId;

@Mapper(builder = @Builder(disableBuilder = true))
public interface FetchPersonDTOMapper {
    @Mapping(target = "birthDate", ignore = true)
    FetchPersonDTO getDTO(Person source);

    @AfterMapping
    default void after(@MappingTarget FetchPersonDTO target, Person source) {
        target.setBirthDate(source.getBirthDate().atStartOfDay(ZoneId.systemDefault()).toInstant().toEpochMilli());
    }
}
