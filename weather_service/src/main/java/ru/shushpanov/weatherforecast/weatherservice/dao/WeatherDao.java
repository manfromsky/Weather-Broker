package ru.shushpanov.weatherforecast.weatherservice.dao;

import ru.shushpanov.weatherbroker.database.entity.ForecastEntity;

import java.util.Date;

public interface WeatherDao {

    ForecastEntity getByCityAndDate(Date date, String city);
}
