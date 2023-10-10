package com.example.jpaweb;

import com.example.jpaweb.entity.Person;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public class PersonCustomRepositoryImpl implements PersonCustomRepository{

    @Autowired
    private EntityManager entityManager;
    @Override
    public void nurSpeichern(final Person toSave) {
        entityManager.persist(toSave);
    }
}
