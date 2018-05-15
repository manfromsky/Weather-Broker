package ru.shushpanov.weatherbroker.database.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.shushpanov.weatherbroker.database.entity.ForecastEntity;

import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Date;

/**
 * {@inheritDoc}
 */
@RequestScoped
public class ForecastDaoImpl implements ForecastDao {
    private final Logger log = LoggerFactory.getLogger(ForecastDaoImpl.class);

    @PersistenceContext(unitName = "manager")
    private EntityManager em;

    /**
     * {@inheritDoc}
     */
    @Override
    public void save(ForecastEntity entity) {
        em.persist(entity);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isForecastDuplicate(Date date, String city) {
        javax.persistence.Query query = em.createQuery(
                "select count(*) from ForecastEntity f where f.date =:date  and f.city =:city"
        );
        query.setParameter("date", date);
        query.setParameter("city", city);
        Long count = (Long) query.getSingleResult();
        log.debug("Number of entries with duplicate values: {}", count);
        return count == 0;
    }
}
