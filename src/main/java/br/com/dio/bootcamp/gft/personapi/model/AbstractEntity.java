package br.com.dio.bootcamp.gft.personapi.model;

import org.springframework.data.domain.Persistable;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class AbstractEntity<E> implements Persistable<Long> {
    @Column(nullable = false)
    private boolean active = true;


    @Override
    public boolean isNew() {
        return (getId() == null);
    }


}
