package ru.shushpanov.weatherbroker.database.service;

import ru.shushpanov.weatherbroker.database.entity.ForecastEntity;
import ru.shushpanov.weatherbroker.error.exeption.WeatherBrokerServiceException;
import ru.shushpanov.weatherbroker.messageservice.model.Forecast;
import ru.shushpanov.weatherbroker.messageservice.service.MessageService;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@ApplicationScoped
public class DataServiceImpl implements DataService {

    private MessageService service;

//    @PersistenceContext(unitName = "manager")
//    private EntityManager em;

    @Inject
    public DataServiceImpl(MessageService service) {
        this.service = service;
    }

    public DataServiceImpl() {
    }

    @Transactional
    public void save(String xml) throws WeatherBrokerServiceException {
        Forecast forecast = transformXmlMessageToModel(xml);
        ForecastEntity entity = transformFromModelToEntity(forecast);
//        em.persist(entity);
    }

    private Forecast transformXmlMessageToModel(String xml) throws WeatherBrokerServiceException {
        return service.readXmlMessage(xml, Forecast.class);
    }

    private ForecastEntity transformFromModelToEntity(Forecast forecast) throws WeatherBrokerServiceException {
        ForecastEntity entity = new ForecastEntity();
        Date date = transformFromStringToDate(forecast.getDate());
        entity.setDate(date);
        entity.setCity(forecast.getCity());
        entity.setDay(forecast.getDay());
        entity.setLowTemp(forecast.getLowTemp());
        entity.setLowTemp(forecast.getLowTemp());
        entity.setDescription(forecast.getDescription());
        return entity;
    }

    private Date transformFromStringToDate(String date) throws WeatherBrokerServiceException {
        SimpleDateFormat format = new SimpleDateFormat();
        format.applyPattern("dd.MM.yyyy");
        Date result = null;
        try {
            result = format.parse(date);
        } catch (ParseException e) {
            throw new WeatherBrokerServiceException("Internal service error", e);
        }
        return result;
    }
}
