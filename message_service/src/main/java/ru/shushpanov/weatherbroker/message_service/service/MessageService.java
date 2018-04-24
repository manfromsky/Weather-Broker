package ru.shushpanov.weatherbroker.message_service.service;

import java.io.File;

public interface MessageService {

    /**
     * Создание xml для сообщения
     */
    String createXmlMessage(Object xml);

    /**
     * Чтение xml сообщения
     */
    Object readXmlMessage(Object xmlModel, String xml);
}
