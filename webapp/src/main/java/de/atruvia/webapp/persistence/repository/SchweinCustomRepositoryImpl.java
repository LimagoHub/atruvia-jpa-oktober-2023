package de.atruvia.webapp.persistence.repository;

import de.atruvia.webapp.persistence.entity.SchweinEntity;
import de.atruvia.webapp.persistence.entity.SchweinEntity_;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;

@Repository
@Transactional
public class SchweinCustomRepositoryImpl implements SchweinCustomRepository{

    @Autowired
    private EntityManager entityManager;
    @Override
    public void einfuegen(final SchweinEntity entity) {
        entityManager.persist(entity);
    }

    @Override
    public Iterable<SchweinEntity> piggyByGewicht(final int gewicht) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();

        CriteriaQuery<SchweinEntity> cQuery = cb.createQuery(SchweinEntity.class);
        Root<SchweinEntity> c = cQuery.from(SchweinEntity.class);
        ParameterExpression<Integer> paramWeigth = cb.parameter(Integer.class);
        cQuery.select(c).where(cb.equal(c.get(SchweinEntity_.gewicht), paramWeigth));

        TypedQuery<SchweinEntity> query = entityManager.createQuery(cQuery);

        return query.setParameter(paramWeigth, gewicht).getResultList();

    }
}
