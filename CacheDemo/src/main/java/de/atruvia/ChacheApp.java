package de.atruvia;

import de.atruvia.entities.Company;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;
import org.hibernate.jpa.QueryHints;

import java.time.Duration;
import java.time.Instant;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChacheApp {

    final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("atruvia");

    public static void main(String[] args) {

        new ChacheApp().run();
    }

    private void run() {


        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("Shutdown Hook is running !");
            entityManagerFactory.close();
        }
        ));
        queryCompany();
        queryCompany();

    }

    private void queryCompany() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        var start = Instant.now();

        Company c = entityManager.find(Company.class, 1L);
        System.out.println(c);
        var end = Instant.now();

        var duration = Duration.between(start, end);

        System.out.println("Duratation = " + duration.toMillis());
        entityManager.getTransaction().commit();
        entityManager.close();
    }


}
