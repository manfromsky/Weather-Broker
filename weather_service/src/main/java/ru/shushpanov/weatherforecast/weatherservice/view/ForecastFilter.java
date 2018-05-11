package ru.shushpanov.weatherforecast.weatherservice.view;

import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.springframework.stereotype.Component;
import ru.shushpanov.weatherforecast.weatherservice.service.JsonDataDeserializer;

import java.util.Calendar;
import java.util.Date;

@Component
public class ForecastFilter {

    public Calendar date;

    public String city;

    public void setDate(Calendar date) {
        this.date = date;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @JsonDeserialize(using = JsonDataDeserializer.class)
    public Calendar getDate() {
        return date;
    }

    public String getCity() {
        return city;
    }

    @Override
    public String toString() {
        return "ForecastFilter{" +
                "date=" + date +
                ", city='" + city + '\'' +
                '}';
    }
}
