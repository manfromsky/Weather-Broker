package ru.shushpanov.weatherforecast.weatherservice.dao;

import ru.shushpanov.weatherbroker.database.entity.ForecastEntity;
import ru.shushpanov.weatherbroker.error.exeption.WeatherBrokerServiceException;
import ru.shushpanov.weatherforecast.weatherservice.view.ForecastFilter;

/**
 * Сервис, устанавливающий связь с базой данных, для получения прогноза погоды
 */
public interface WeatherDao {

    /**
     * Получить прогноз погоды из базы данных
     *
     * @param filter Объек, содержащий в себе информацию, необходимую для получения прогноза по конкретному городу,
     *               на конкретную дату
     * @return Объект, содержащий прогноз подогы, полученный по запросу к базе данных
     * @throws WeatherBrokerServiceException Ошибка возникающая при получении от пользователя неккоретной информации,
     *                                       необходимой для запроса к базе данных
     */
    ForecastEntity getByCityAndDate(ForecastFilter filter) throws WeatherBrokerServiceException;
}
