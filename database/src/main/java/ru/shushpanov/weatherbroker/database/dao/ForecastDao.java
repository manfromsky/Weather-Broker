package ru.shushpanov.weatherbroker.database.dao;

import ru.shushpanov.weatherbroker.database.entity.ForecastEntity;

public interface ForecastDao {
    /**
     * Сохранение прогноза погоды
     *
     * @param entity Сущность, соответствующая таблице в базе данных, которая содержит данные о погоде
     */
    void save(ForecastEntity entity);
}
