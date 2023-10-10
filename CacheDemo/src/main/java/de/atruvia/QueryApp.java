package de.atruvia;

import de.atruvia.entities.Company;
import de.atruvia.entities.Employee;
import de.atruvia.entities.Kontakt;
import de.atruvia.entities.Person;
import jakarta.persistence.*;
import org.hibernate.jpa.QueryHints;

import java.util.*;

public class QueryApp {

    final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("atruvia");

    public static void main(String[] args) {

        new QueryApp().run();
    }

    private void run() {


        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("Shutdown Hook is running !");
            entityManagerFactory.close();
        }
        ));

        //queryCompany();
        //queryEmployee();
        queryCache();
        queryCache();
    }

    private void queryCompany() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        TypedQuery<Company> companyQuery = entityManager.createQuery("Select c from Company c left join fetch c.employees e", Company.class);
        List<Company> companies = companyQuery.getResultList();
        for (var company: companies) {
            System.out.println(company);
            company.getEmployees().forEach(System.out::println);
        }

        entityManager.getTransaction().commit();
        entityManager.close();
    }

    private void queryEmployee() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        //EntityGraph<Company> entityGraph = entityManager.createEntityGraph(Company.class);
        //entityGraph.addAttributeNodes("employees");

//        TypedQuery<Company> companyQuery = entityManager.createQuery(
//                "Select c from Company c  where c.country = 'China'", Company.class);
//        companyQuery.setHint("jakarta.persistence.fetchgraph", entityGraph);
//        List<Company> companies = companyQuery.getResultList();
//        for (var company: companies) {
//            System.out.println(company);
//
//        }
        Map<String, Object> hint = new HashMap<>();
        hint.put("jakarta.persistence.fetchgraph", entityManager.getEntityGraph("entityGraph.Company.employees"));
        var c = entityManager.find(Company.class, 1L, hint);
        System.out.println(c);

        entityManager.getTransaction().commit();
        entityManager.close();

        System.out.println(c.getEmployees());
    }

    private void queryCache() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();


        TypedQuery<Company> companyQuery = entityManager.createQuery(
                "Select c from Company c  where c.country = 'China'", Company.class);

        List<Company> companies = companyQuery.setHint(QueryHints.HINT_CACHEABLE, "true")
                .setHint(QueryHints.HINT_CACHE_REGION, "queryCache").getResultList();
        for (var company: companies) {
            System.out.println(company);

        }


        entityManager.getTransaction().commit();
        entityManager.close();


    }
}
