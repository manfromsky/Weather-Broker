package ru.shushpanov.weatherbroker.database.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.shushpanov.weatherbroker.database.entity.ForecastEntity;

import java.util.Set;

public interface ForecastDao extends JpaRepository<ForecastEntity, Long> {

    /**
     * Получение погоды за 10 дней по названию города
     */
    Set<ForecastEntity> findAllByCity(String City);
}
