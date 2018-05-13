package ru.shushpanov.weatherforecast.weatherservice.view;

import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Date;

@Component
public class ForecastFilter {
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    public Date date;

    public String city;

    public void setDate(Date date) {
        this.date = date;
    }

    public void setCity(String city) {
        this.city = city;
    }


    public Date getDate() {
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
