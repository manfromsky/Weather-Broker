package ru.shushpanov.weatherforecast.weatherservice.dao;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;
import ru.shushpanov.weatherbroker.database.entity.ForecastEntity;
import ru.shushpanov.weatherbroker.error.exeption.WeatherBrokerServiceException;
import ru.shushpanov.weatherforecast.weatherservice.view.ForecastFilter;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.Date;

/**
 * {@inheritDoc}
 */
@Repository
public class WeatherDaoImpl implements WeatherDao {

    @PersistenceContext
    private EntityManager em;

    /**
     * {@inheritDoc}
     */
    @Override
    public ForecastEntity getByCityAndDate(ForecastFilter filter) throws WeatherBrokerServiceException {
        if (filter.date == null || StringUtils.isBlank(filter.city)) {
            throw new WeatherBrokerServiceException("date or city is empty");
        }
        Date date = filter.date;
        String city = filter.city;
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<ForecastEntity> criteriaQuery = builder.createQuery(ForecastEntity.class);
        Root<ForecastEntity> root = criteriaQuery.from(ForecastEntity.class);
        criteriaQuery.select(root);
        Predicate criteria = builder.conjunction();
        Predicate datePredicate = builder.equal(root.get("date"), date);
        criteria = builder.and(criteria, datePredicate);
        Predicate cityPredicate = builder.equal(root.get("city"), city);
        criteria = builder.and(criteria, cityPredicate);
        criteriaQuery.where(criteria);

        return em.createQuery(criteriaQuery).getSingleResult();
    }
}
