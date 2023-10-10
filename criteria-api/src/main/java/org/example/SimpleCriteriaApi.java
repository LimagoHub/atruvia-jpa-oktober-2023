package org.example;


import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.AllArgsConstructor;
import org.example.entities.Customer;
import org.example.entities.Customer_;

import java.util.List;

@AllArgsConstructor
public class SimpleCriteriaApi {

    private final EntityManagerFactory entityManagerFactory;

    public void go() {

        // Create Session (First-Level-Chache)
        EntityManager em = entityManagerFactory.createEntityManager();

        // Um Criteria-Api nutzen zu können
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();

        // Create Query mit ZielTyp
        CriteriaQuery<Customer> criteriaQuery = criteriaBuilder.createQuery(Customer.class);

        Root<Customer> customerRoot = criteriaQuery.from(Customer.class);
        criteriaQuery
                .select(customerRoot);
        Predicate pAnd = criteriaBuilder.conjunction();
        pAnd = criteriaBuilder.and(pAnd,criteriaBuilder.equal(customerRoot.get(Customer_.COUNTRY), "USA"));
        pAnd = criteriaBuilder.and(pAnd,criteriaBuilder.like(customerRoot.get(Customer_.COMPANY_NAME), "W%"));
//        pAnd = cb.and(pAnd,p3);


        criteriaQuery .where(pAnd);

        TypedQuery<Customer> q = em.createQuery(criteriaQuery);
        List<Customer> allCustomers = q.getResultList();
        allCustomers.forEach(System.out::println);
        em.close();
    }
}
