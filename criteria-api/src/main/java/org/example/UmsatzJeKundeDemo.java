package org.example;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.*;
import lombok.AllArgsConstructor;
import org.example.entities.Order;
import org.example.entities.*;

import java.util.List;

@AllArgsConstructor
public class UmsatzJeKundeDemo {
    private final EntityManagerFactory entityManagerFactory;

    public void go() {
        EntityManager em = entityManagerFactory.createEntityManager();
        CriteriaBuilder cb = em.getCriteriaBuilder();

        var cq = cb.createQuery(UmsatzJeKunde.class);
        Root<Customer> customerRoot = cq.from(Customer.class);

        final Join< Customer, Order> orderRoot = customerRoot.join(Customer_.ORDERS, JoinType.LEFT);
        final Join<Order, OrderDetail> orderDetailRoot = orderRoot.join(Order_.ORDER_DETAILS, JoinType.LEFT);

        Expression<Double> sumExpression = cb.sum(cb.prod(orderDetailRoot.get(OrderDetail_.QUANTITY), orderDetailRoot.get(OrderDetail_.UNIT_PRICE)));

        cq.multiselect(
                        customerRoot.get(Customer_.COMPANY_NAME),sumExpression
                )
                .groupBy(customerRoot.get(Customer_.COMPANY_NAME)).orderBy(cb.desc(sumExpression));

        var amis = cb.equal(customerRoot.get(Customer_.COUNTRY), "USA");
        cq.where(amis).having(cb.ge( sumExpression, 50000.0));


        TypedQuery<UmsatzJeKunde> q = em.createQuery(cq);
        List<UmsatzJeKunde> allCustomers = q.getResultList();
        allCustomers.forEach(System.out::println);
        em.close();



    }
}
