package ru.shushpanov.weatherforecast.weatherservice.service;

import ru.shushpanov.weatherforecast.weatherservice.view.ForecastView;

import java.util.Date;

/**
 * Сервис запрашивает информацию о погоде согласно введеным пользователем параметрам у базы данных
 */
public interface WeatherService {
    /**
     * Получить прогноз погоды на день по городу и дате
     *
     * @param date Дата для котрой запрашивается прогноз
     * @param city Название города для которого запрашивается прогноз
     * @return Объект содержащий информацию о запрашиваемом пронозе погоды
     */
    ForecastView getForecastByDate(Date date, String city);
}
