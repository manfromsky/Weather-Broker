package ru.shushpanov.weatherbroker.database.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.shushpanov.weatherbroker.database.dao.ForecastDao;
import ru.shushpanov.weatherbroker.database.entity.ForecastEntity;
import ru.shushpanov.weatherbroker.database.model.Forecast;
import ru.shushpanov.weatherbroker.database.model.Weather;
import ru.shushpanov.weatherbroker.database.service.DataService;
import ru.shushpanov.weatherbroker.messageservice.service.MessageService;

import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

@Repository
public class DataServiceImpl implements DataService {
    private final MessageService messageService;
    private final ForecastDao dao;

    @Autowired
    public DataServiceImpl(MessageService messageService, ForecastDao dao) {
        this.messageService = messageService;
        this.dao = dao;
    }

    private Set<Forecast> transformXmlMessageToEntityList(String xml) {
        Weather weather = (Weather) messageService.readXmlMessage(Weather.class, xml);
        return weather.getForecasts();
    }

    private Set<ForecastEntity> transformModelToEntity(Set<Forecast> forecastSet) {
        Function<Forecast, ForecastEntity> mapForecast = f -> {
            ForecastEntity entity = new ForecastEntity();
            entity.setDate(f.date);
            entity.setCity(f.city);
            entity.setDay(f.day);
            entity.setDescription(f.description);
            entity.setHighTemp(f.highTemp);
            entity.setLowTemp(f.lowTemp);
            return entity;
        };
        return forecastSet.stream().map(mapForecast).collect(Collectors.toSet());
    }

    @Transactional
    boolean save(Set<ForecastEntity> entitySet) {
        boolean flag = true;
        for (ForecastEntity entity : entitySet) {
            ForecastEntity newEntity = dao.save(entity);
            if (entity.equals(newEntity)) {
                flag = true;
            } else {
                flag = false;
                break;
            }
        }
        return flag;
    }
}
