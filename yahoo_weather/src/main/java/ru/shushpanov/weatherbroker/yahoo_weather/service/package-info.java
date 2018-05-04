/**
 * Пакет представляет собой сервис для получения прогноза погоды, по названию города, при помощи Yahoo Weather Api
 * {@link ru.shushpanov.weatherbroker.yahoo_weather.service.YahooReceiver} Сервис принимает JMS сообщение с названием
 * города, по которому делает запрос погоды
 * {@link ru.shushpanov.weatherbroker.yahoo_weather.service.ForecastService} Сервис делает запрос к Yahoo Weather Api,
 * по полученному названию города, и передает результат в виде JMS сообщения.
 */
package ru.shushpanov.weatherbroker.yahoo_weather.service;