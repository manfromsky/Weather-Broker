package ru.shushpanov.weatherbroker.database.service;

import ru.shushpanov.weatherbroker.error.exeption.WeatherBrokerServiceException;

/**
 * Сервис для сохранения в базу данных о погоде
 */
public interface DataService {

    /**
     * Сохранение в базу данных о погоде
     *
     * @param xml Строка, содержащая информацию о погоде
     * @throws WeatherBrokerServiceException Исключение, сгенерированное при полученни методом пустой или равной null
     *                                       строки
     */
    void save(String xml) throws WeatherBrokerServiceException;
}
