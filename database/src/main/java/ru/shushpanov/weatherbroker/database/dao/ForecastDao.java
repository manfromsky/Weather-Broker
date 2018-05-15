package ru.shushpanov.weatherbroker.database.dao;

import ru.shushpanov.weatherbroker.database.entity.ForecastEntity;

import java.util.Date;

/**
 * Сервис, устанавливающий связь с базой данных, для последующего сохранения прогноза
 */
public interface ForecastDao {
    /**
     * Сохранение прогноза погоды в базу данных
     *
     * @param entity Сущность, соответствующая таблице в базе данных, которая содержит данные о погоде
     */
    void save(ForecastEntity entity);

    /**
     * Проверка на наличие идентичных данных в базе.
     *
     * @param date Дата запрашиваемого прогноза
     * @param city Название города запрашиваемого прогноза
     * @return Возвращает true если в базе данных нет информации о прогнозе на соответствующую дату и город
     */
    boolean isForecastDuplicate(Date date, String city);
}
