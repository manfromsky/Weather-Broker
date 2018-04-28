package ru.shushpanov.weatherbroker.yahoo_weather.service;

import ru.shushpanov.weatherbroker.error.exeption.WeatherBrokerServiceException;

import java.net.URISyntaxException;

/**
 * Сервис для отправки сообщения содержащего данные о погоде на 10 дней
 */
public interface ForecastService {

    /**
     * Создание xml сообщения и его отправка
     *
     * @param xml Строка содержащая xml с названием города
     * @throws WeatherBrokerServiceException Ошибка сгенерированная при попытке запроса с сервесу Yahoo Weather API
     */
    void createAndSendMessage(String xml) throws WeatherBrokerServiceException;
}
