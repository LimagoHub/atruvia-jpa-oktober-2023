package de.atruvia.webapp.persistence.repository;

import de.atruvia.webapp.persistence.entity.PersonEntity;
import de.atruvia.webapp.persistence.entity.TinyPerson;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface PersonenRepository extends CrudRepository<PersonEntity, String> , PersonCustomRepository{


    Iterable<PersonEntity> findByVorname(String vorname);

    @Query("select p from PersonEntity p where p.nachname like :nachname")
    Iterable<PersonEntity> xyz(String nachname);

    @Query("select p.id, p.nachname from PersonEntity p where p.nachname like :nachname")
    Iterable<Object[]> abc(String nachname);

    @Query("select new de.atruvia.webapp.persistence.entity.TinyPerson(p.id, p.nachname) from PersonEntity p where p.nachname like :nachname")
    Iterable<TinyPerson> bcd(String nachname);

    @Query("update PersonEntity p set p.vorname = :vorname where p.id = :id")
    void updateVorname(String id, String vorname);
}
