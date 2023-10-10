package de.atruvia.webapp.persistence.repository;


import de.atruvia.webapp.persistence.entity.PersonEntity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@Transactional
@Repository
public class PersonCustomRepositoryImpl implements PersonCustomRepository{

    @Autowired
    private EntityManager entityManager;
    @Override
    public void nurSpeichern(final PersonEntity toSave) {
        entityManager.persist(toSave);
    }
}
