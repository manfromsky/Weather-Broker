package ru.shushpanov.weatherbroker.messageservice.service.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Модель для преобразования xml в объект
 */
@XmlRootElement(name = "ReadCity")
public class ReadCity {
    @XmlElement(name = "name")
    private String name;

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "{" + name + "}";
    }
}
