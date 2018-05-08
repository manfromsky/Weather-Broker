package ru.shushpanov.weatherforecast.weatherservice.view;

import java.util.Date;

public class ForecastFilter {

    public Date date;
    public String city;

    @Override
    public String toString() {
        return "ForecastFilter{" +
                "date=" + date +
                ", city='" + city + '\'' +
                '}';
    }
}
