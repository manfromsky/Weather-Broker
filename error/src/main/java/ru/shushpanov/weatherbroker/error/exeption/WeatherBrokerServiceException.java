package ru.shushpanov.weatherbroker.error.exeption;

/**
 * Ошибка, сгенерированная в результате внутренней работы сервиса
 */
public class WeatherBrokerServiceException extends Exception {
    public WeatherBrokerServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public WeatherBrokerServiceException(String message) {
        super(message);
    }
}
