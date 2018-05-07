package ru.shushpanov.weatherbroker.database.dao;

import ru.shushpanov.weatherbroker.database.entity.ForecastEntity;

import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * {@inheritDoc}
 */
@RequestScoped
public class ForecastDaoImpl implements ForecastDao {

    @PersistenceContext(unitName = "manager")
    private EntityManager em;

    /**
     * {@inheritDoc}
     */
    @Override
    public void save(ForecastEntity entity) {
        em.persist(entity);
    }
}
