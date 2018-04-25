package ru.shushpanov.weatherbroker.messageservice.service;

public interface MessageService {

    /**
     * Создание xml для сообщения
     */
     String createXmlMessage(Object xml);

    /**
     * Чтение xml сообщения
     */
    Object readXmlMessage(Object model, String xml);
}
