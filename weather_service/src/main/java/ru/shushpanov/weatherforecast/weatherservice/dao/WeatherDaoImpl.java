package ru.shushpanov.weatherforecast.weatherservice.dao;

import org.springframework.stereotype.Repository;
import ru.shushpanov.weatherbroker.database.entity.ForecastEntity;

import java.util.Date;

@Repository
public class WeatherDaoImpl implements WeatherDao {
    @Override
    public ForecastEntity getByCityAndDate(Date date, String city) {
        return new ForecastEntity();
    }
}
