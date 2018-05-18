package ru.shushpanov.weatherforecast.weatherservice.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.shushpanov.weatherbroker.database.entity.ForecastEntity;
import ru.shushpanov.weatherbroker.error.exeption.WeatherBrokerServiceException;
import ru.shushpanov.weatherforecast.weatherservice.dao.WeatherDao;
import ru.shushpanov.weatherforecast.weatherservice.view.ForecastFilter;
import ru.shushpanov.weatherforecast.weatherservice.view.ForecastView;

/**
 * {@inheritDoc}
 */
@Service
public class WeatherServiceImpl implements WeatherService {

    private final Logger log = LoggerFactory.getLogger(WeatherServiceImpl.class);
    private WeatherDao dao;

    public WeatherServiceImpl(WeatherDao dao) {
        this.dao = dao;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public ForecastView getForecastByFilter(ForecastFilter filter) throws WeatherBrokerServiceException {
        ForecastEntity entity = dao.getByCityAndDate(filter);
        log.debug("received weather forecast: {}", entity);
        return transformFromEntityToView(entity);
    }

    /**
     * Преобразование сущности, связанной с таблицей в базе данных, в объект содержащий информацию о погоде
     *
     * @param entity Сущность, связанная с таблицей в базе данных
     * @return Объект содержащий информацию о погоде
     */
    private ForecastView transformFromEntityToView(ForecastEntity entity) {

        ForecastView view = new ForecastView();
        view.city = entity.getCity();
        view.date = entity.getDate();
        view.day = entity.getDay();
        view.highTemp = entity.getHighTemp();
        view.lowTemp = entity.getLowTemp();
        view.description = entity.getDescription();
        log.debug("view after transform from entity: {}", view);
        return view;
    }
}
