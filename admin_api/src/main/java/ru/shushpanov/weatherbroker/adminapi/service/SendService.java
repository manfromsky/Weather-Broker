package ru.shushpanov.weatherbroker.adminapi.service;

import ru.shushpanov.weatherbroker.error.exeption.WeatherBrokerServiceException;

/**
 * Севрис для отправки сообщения содержащего название города
 */
public interface SendService {

    /**
     * Создание и отправка JMS сообщения
     *
     * @param city название города
     * @throws WeatherBrokerServiceException Ошибка возникшая при попытке отправки JMS сообщения
     */
    void send(String city) throws WeatherBrokerServiceException;
}
