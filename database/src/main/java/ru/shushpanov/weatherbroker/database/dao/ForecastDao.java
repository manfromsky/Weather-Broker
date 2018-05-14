package ru.shushpanov.weatherbroker.database.dao;

import ru.shushpanov.weatherbroker.database.entity.ForecastEntity;

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
}
