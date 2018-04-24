package ru.shushpanov.weatherbroker.yahoo_weather.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Модель для преобразования xml в объект
 */
@XmlRootElement(name = "City")
public class City {
    @XmlElement(name = "name")
    private String name;

    public String getName() {
        return name;
    }
}
