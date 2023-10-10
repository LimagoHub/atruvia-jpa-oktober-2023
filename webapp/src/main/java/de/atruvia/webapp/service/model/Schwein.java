package de.atruvia.webapp.service.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Id;


@Data
@Setter(AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Schwein {


    private String id;


    private String name;


    private int gewicht;

    public void taufen(String neuerName) {
        setName(neuerName);
    }

    public void fuettern() {
        setGewicht(getGewicht() +1);
    }
}
