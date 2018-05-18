package ru.shushpanov.weatherbroker.error.exeption;

/**
 * Исключение, сгенерированное при передачи пустого значения вместо названия города
 */
public class EmptyOrNullCityException extends RuntimeException {
    public EmptyOrNullCityException(String message) {
        super(message);
    }
}
