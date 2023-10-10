package de.atruvia.webapp.persistence.repository;

import de.atruvia.webapp.persistence.entity.SchweinEntity;

public interface SchweinCustomRepository {

    void einfuegen(final SchweinEntity entity);
    Iterable<SchweinEntity> piggyByGewicht(final int gewicht);
}
