package ru.shushpanov.weatherbroker.admin_api.service;

/**
 * Севрис для отправки сообщения
 */
public interface SendService {

    /**
     * Создание xml сообщения и его отправка
     */
    void createAndSendMessage(String name);
}
