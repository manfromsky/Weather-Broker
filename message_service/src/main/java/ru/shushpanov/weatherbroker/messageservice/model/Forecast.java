package ru.shushpanov.weatherbroker.messageservice.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Модель для преобразование погоды в xml для отправки сообщения
 */
@XmlRootElement(name = "forecast")
@XmlAccessorType(XmlAccessType.FIELD)
public class Forecast implements XmlModel {

    private String date;

    private String city;

    private String day;

    private String highTemp;

    private String lowTemp;

    private String description;

    public void setDate(String date) {
        this.date = date;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public void setHighTemp(String highTemp) {
        this.highTemp = highTemp;
    }

    public void setLowTemp(String lowTemp) {
        this.lowTemp = lowTemp;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public String getCity() {
        return city;
    }

    public String getDay() {
        return day;
    }

    public String getHighTemp() {
        return highTemp;
    }

    public String getLowTemp() {
        return lowTemp;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "Forecast{" +
                "date='" + date + '\'' +
                ", city='" + city + '\'' +
                ", day='" + day + '\'' +
                ", highTemp='" + highTemp + '\'' +
                ", lowTemp='" + lowTemp + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
