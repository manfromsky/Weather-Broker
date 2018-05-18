package ru.shushpanov.weatherbroker.error.exeption;

/**
 * Ошибка, возникающая при передачи пустого значения вместо названия города
 */
public class EmptyOrNullCityException extends RuntimeException {
    public EmptyOrNullCityException(String message) {
        super(message);
    }
}
