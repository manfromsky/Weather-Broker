package ru.shushpanov.weatherbroker.messageservice.model;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Модель для преобразования названия города в xml
 */
@XmlRootElement(name = "City")
public class City implements XmlModel{
    @XmlAttribute(name = "name")
    private String name;

    public City() {

    }

    public City(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "{" + name + "}";
    }
}
