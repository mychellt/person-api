package br.com.dio.bootcamp.gft.personapi.utils;

import br.com.dio.bootcamp.gft.personapi.controller.dto.SavePersonDTO;
import br.com.dio.bootcamp.gft.personapi.model.Person;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;

public class PersonUtils {
    private static final String FIRST_NAME = "John";
    private static final String LAST_NAME = "Doe";
    private static final Long CPF = 2476859095L;
    private static final Long ID = 1L;
    private static final LocalDate BIRTH_DATE = LocalDate.now();

    public static Person createFakePerson() {
        return Person.builder()
            .id(ID)
            .firstName(FIRST_NAME)
            .lastName(LAST_NAME)
            .cpf(CPF)
            .birthDate(BIRTH_DATE)
            .build();
    }

    public static SavePersonDTO createPersonDTO() {
        return SavePersonDTO.builder()
                .cpf(CPF)
                .firstName(FIRST_NAME)
                .birthDate(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli())
                .lastName(LAST_NAME)
                .build();
    }

    public static SavePersonDTO createPersonDTOWithoutFirstName() {
        SavePersonDTO person = createPersonDTO();
        person.setFirstName(null);
        return person;
    }

    public static SavePersonDTO createPersonDTOWithoutLastName() {
        SavePersonDTO person = createPersonDTO();
        person.setLastName(null);
        return person;
    }

    public static SavePersonDTO createPersonDTOWithoutCPFNumber() {
        SavePersonDTO person = createPersonDTO();
        person.setCpf(null);
        return person;
    }

    public static SavePersonDTO createPersonDTOWithoutBirthDate() {
        SavePersonDTO person = createPersonDTO();
        person.setBirthDate(null);
        return person;
    }
}
