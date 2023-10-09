package de.atruvia;

import de.atruvia.entities.Company;
import de.atruvia.entities.Employee;
import de.atruvia.entities.Kontakt;
import de.atruvia.entities.Person;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;

import java.util.List;

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
        queryEmployee();
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

        TypedQuery<Employee> companyQuery = entityManager.createQuery("Select e from Employee e inner join fetch e.company c where c.country = 'China'", Employee.class);
        List<Employee> employees = companyQuery.getResultList();
        for (var employee: employees) {
            System.out.println(employee);

        }

        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
