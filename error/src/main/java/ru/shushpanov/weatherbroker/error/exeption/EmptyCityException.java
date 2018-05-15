package ru.shushpanov.weatherbroker.error.exeption;

/**
 * Ошибка, возникающая при передачи пустого значения вместо названия города
 */
public class EmptyCityException extends RuntimeException {
    public EmptyCityException(String message) {
        super(message);
    }
}
