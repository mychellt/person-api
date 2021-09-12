package br.com.dio.bootcamp.gft.personapi.repository;

import br.com.dio.bootcamp.gft.personapi.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
    boolean existsByCpf(long cpf);
}
