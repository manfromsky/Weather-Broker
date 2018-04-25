package ru.shushpanov.weatherbroker.messageservice.service.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Модель для преобразования названия города в xml
 */
@XmlRootElement(name = "ReadCity")
public class WriteCity {
    @XmlElement(name = "description")
    private String name;

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "{" + name + "}";
    }
}
