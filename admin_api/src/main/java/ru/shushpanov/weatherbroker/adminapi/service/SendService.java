package ru.shushpanov.weatherbroker.adminapi.service;

/**
 * Севрис для отправки сообщения
 */
public interface SendService {

    /**
     * Создание xml сообщения и его отправка
     */
    void createAndSendMessage(String name);
}
