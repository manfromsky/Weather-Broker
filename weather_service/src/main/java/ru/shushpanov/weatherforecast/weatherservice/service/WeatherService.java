package ru.shushpanov.weatherforecast.weatherservice.service;

import ru.shushpanov.weatherbroker.error.exeption.WeatherBrokerServiceException;
import ru.shushpanov.weatherforecast.weatherservice.view.ForecastFilter;
import ru.shushpanov.weatherforecast.weatherservice.view.ForecastView;

/**
 * Сервис запрашивает информацию о погоде согласно введеным пользователем параметрам у базы данных
 */
public interface WeatherService {
    /**
     * Получить прогноз погоды на день по фильтру
     *
     * @param filter Объект содержащий информацию для поиска конкретного прогноза погоды
     * @return Объект содержащий информацию о запрашиваемом пронозе погоды
     */
    ForecastView getForecastByFilter(ForecastFilter filter) throws WeatherBrokerServiceException;
}
