package br.com.dio.bootcamp.gft.personapi.mappers;

import br.com.dio.bootcamp.gft.personapi.controller.dto.SavePersonDTO;
import br.com.dio.bootcamp.gft.personapi.model.Person;
import org.mapstruct.*;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;

@Mapper(builder = @Builder(disableBuilder = true))
public interface PersonMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "birthDate", ignore = true)
    Person getEntity(SavePersonDTO source);

    @AfterMapping
    default void after(@MappingTarget Person target, SavePersonDTO source) {
        Instant instant = Instant.ofEpochMilli(source.getBirthDate());
        target.setBirthDate(LocalDate.ofInstant(instant, ZoneId.systemDefault()));
    }

}
