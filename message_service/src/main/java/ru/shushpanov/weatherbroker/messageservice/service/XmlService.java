package ru.shushpanov.weatherbroker.messageservice.service;

import ru.shushpanov.weatherbroker.error.exeption.WeatherBrokerServiceException;
import ru.shushpanov.weatherbroker.messageservice.model.XmlModel;

/**
 * Сервис для создания и чтения xml
 */
public interface XmlService {

    /**
     * Создание xml для сообщения
     *
     * @param xml Обект, передаваеммый для преобразования в xml, реализующий интерфейс {@link XmlModel}
     * @return Строка в формате xml, полученная после преобразования объекта {@link XmlModel}
     * @throws WeatherBrokerServiceException Ошибка возникшая при преобразовании объекста в xml
     */
    String createXmlMessage(XmlModel xml) throws WeatherBrokerServiceException;

    /**
     * Чтение xml сообщения
     *
     * @param xml        Строка в формате xml, переданныая для преобразования в объект,
     *                   реализующий интерфейс {@link XmlModel}
     * @param modelClass Класс в объект которого будет выполнено преобразование входного xml
     * @param <T>        Класс реализующий интерфейс {@link XmlModel}
     * @return Объект, получившийся после преобразования строки xml
     * @throws WeatherBrokerServiceException Ошибка, возникшая при преобразовании xml в объект
     */
    <T extends XmlModel> T readXmlMessage(String xml, Class<T> modelClass) throws WeatherBrokerServiceException;
}
