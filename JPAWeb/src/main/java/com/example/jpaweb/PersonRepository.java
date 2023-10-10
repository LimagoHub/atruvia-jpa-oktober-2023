package com.example.jpaweb;

import com.example.jpaweb.entity.Person;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface PersonRepository extends CrudRepository<Person, UUID>, PersonCustomRepository {
}
