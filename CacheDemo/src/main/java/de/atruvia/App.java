package de.atruvia;


import de.atruvia.entities.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;

import java.util.UUID;


public class App {
    final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("atruvia");

    public static void main(String[] args) {

        new App().run();
    }

    private void run() {


        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("Shutdown Hook is running !");
            entityManagerFactory.close();
        }
        ));

        //createPerson();
        //mergePerson();
        //removePerson();
        //createBarKeeper();
        //createBar();
        optimisticExcample();
    }

    private void optimisticExcample() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        NeuePerson p = NeuePerson.builder().vorname("John").nachname("Doe").build();

        p = entityManager.merge(p);// Persistent

        p.setVorname("Max");

        entityManager.getTransaction().commit();
        entityManager.close();

        entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        p.setVersion(0);

        p = entityManager.merge(p);// Persistent



        entityManager.getTransaction().commit();
        entityManager.close();
    }

    private void createPerson() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        Person p = Person.builder().vorname("John").nachname("Doe").build();
        p.getKontakte().add(Kontakt.builder().art("Mobil").wert("12345").build());
        p.getKontakte().add(Kontakt.builder().art("Email").wert("a@bcd.de").build());

        entityManager.persist(p);// Persistent

        p.setVorname("Jane");

        entityManager.getTransaction().commit();
        entityManager.close();
    }

    private void mergePerson() {
        // d70c84b3-51d5-483e-9460-52b10be51734
        //Person p = Person.builder().id(UUID.fromString("d70c84b3-51d5-483e-9460-52b10be51734")).vorname("Max").nachname("Mustermann").build();
        Person p = Person.builder().id(UUID.fromString("d70c84b3-51d5-483e-9460-52b10be51735")).vorname("John").nachname("Wayne").build();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();


        // Vorsicht : Bei Merge ist nur der Rückgabewert attached
        p = entityManager.merge(p);

        p.setVorname("Erika");

        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public void removePerson() {
        Person p = Person.builder().id(UUID.fromString("d70c84b3-51d5-483e-9460-52b10be51734")).vorname("John").nachname("Wayne").build();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        p = entityManager.find(Person.class, UUID.fromString("d70c84b3-51d5-483e-9460-52b10be51734"));


        entityManager.remove(p);


        entityManager.getTransaction().commit();
        entityManager.close();

    }

    private void createBarKeeper() {

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        Bar bar = Bar.builder().barname("Milchbar").build();

        entityManager.persist(bar);

        BarKeeper keeper = BarKeeper.builder().bar(bar).keeperName("Jonny").build();


        entityManager.persist(keeper);


        BarKeeper b1 = entityManager.find(BarKeeper.class, keeper.getId());
        System.out.println(b1);

        bar.setBarname("Upps");

        entityManager.getTransaction().commit();
        entityManager.close();


    }

    private void createBar() {

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        Bar bar = Bar.builder().barname("Foobar").build();

        bar.getKeepers().add(BarKeeper.builder().bar(bar).keeperName("Jimmy").build());

        entityManager.persist(bar);

        entityManager.getTransaction().commit();
        entityManager.close();


    }
}