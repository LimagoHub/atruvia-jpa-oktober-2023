package de.atruvia.webapp.demo;

import de.atruvia.webapp.persistence.entity.PersonEntity;
import de.atruvia.webapp.persistence.entity.SchweinEntity;
import de.atruvia.webapp.persistence.repository.PersonenRepository;
import de.atruvia.webapp.persistence.repository.SchweinRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@RequiredArgsConstructor
public class PersistenceDemo {

    private final SchweinRepository repo;

    @PostConstruct
    public void init() {
        SchweinEntity babe = SchweinEntity.builder().id("1").name("Babe").gewicht(10).build();
        SchweinEntity piggy = SchweinEntity.builder().id("2").name("Piggy").gewicht(10).build();
        SchweinEntity rudi = SchweinEntity.builder().id("3").name("Rudi").gewicht(9).build();

        repo.einfuegen(babe);
        repo.einfuegen((piggy));
        repo.einfuegen(rudi);

        repo.piggyByGewicht(10).forEach(System.out::println);
    }
}
