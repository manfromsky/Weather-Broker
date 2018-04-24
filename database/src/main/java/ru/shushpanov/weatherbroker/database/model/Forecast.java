package ru.shushpanov.weatherbroker.database.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;

/**
 * Модель для преобразование xml в объект
 */
@XmlRootElement(name = "forecast")
public class Forecast {

    @XmlElement(name = "date")
    public Date date;

    @XmlElement(name = "city")
    public String city;

    @XmlElement(name = "day")
    public String day;

    @XmlElement(name = "highTemp")
    public String highTemp;

    @XmlElement(name = "lowTemp")
    public String lowTemp;

    @XmlElement(name = "description")
    public String description;

    public void setDate(Date date) {
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
}
