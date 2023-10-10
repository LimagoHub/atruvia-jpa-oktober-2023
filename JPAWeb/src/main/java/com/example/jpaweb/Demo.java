package com.example.jpaweb;


import com.example.jpaweb.entity.Person;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class )
public class Demo {
    private final PersonRepository repo;

    @PostConstruct
    public void doIt() {
        Person p = Person.builder().vorname("John").nachname("Doe").build();

        repo.nurSpeichern(p);
    }
}
