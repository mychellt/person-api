package br.com.dio.bootcamp.gft.personapi.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FetchPersonDTO {
    private String firstName;
    private String lastName;
    private Long cpf;
    private Long birthDate;
}
