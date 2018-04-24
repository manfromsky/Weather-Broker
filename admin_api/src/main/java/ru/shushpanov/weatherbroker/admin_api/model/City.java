package ru.shushpanov.weatherbroker.admin_api.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Модель для преобразования названия города в xml
 */
@XmlRootElement(name = "City")
public class City {
    @XmlElement(name = "description")
    private String name;

    public void setName(String name) {
        this.name = name;
    }
}
