package ru.shushpanov.weatherbroker.messageservice.service.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;

/**
 * Модель для преобразование погоды в xml для отправки сообщения
 */
@XmlRootElement(name = "forecast")
public class WriteForecast {

    @XmlElement(name = "date")
    private Date date;

    @XmlElement(name = "city")
    private String city;

    @XmlElement(name = "day")
    private String day;

    @XmlElement(name = "highTemp")
    private String highTemp;

    @XmlElement(name = "lowTemp")
    private String lowTemp;

    @XmlElement(name = "description")
    private String description;


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
