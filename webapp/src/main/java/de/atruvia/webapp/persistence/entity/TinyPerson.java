package de.atruvia.webapp.persistence.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.annotation.PostConstruct;

@Data
@RequiredArgsConstructor
public class TinyPerson {

    private final String id;
    private final String nachname;


}
