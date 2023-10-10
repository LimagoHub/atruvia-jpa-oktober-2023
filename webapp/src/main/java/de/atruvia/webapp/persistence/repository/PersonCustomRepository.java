package de.atruvia.webapp.persistence.repository;



import de.atruvia.webapp.persistence.entity.PersonEntity;

public interface PersonCustomRepository {
    void nurSpeichern(PersonEntity toSave);
}
