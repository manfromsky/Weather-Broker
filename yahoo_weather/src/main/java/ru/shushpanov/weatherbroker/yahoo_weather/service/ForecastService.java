package ru.shushpanov.weatherbroker.yahoo_weather.service;

import java.net.URISyntaxException;

public interface ForecastService {

    /**
     * Создание xml сообщения и его отправка
     */
    void createAndSendMessage(String City) throws URISyntaxException;
}
