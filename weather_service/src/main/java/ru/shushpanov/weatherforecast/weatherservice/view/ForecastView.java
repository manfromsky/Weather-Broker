package ru.shushpanov.weatherforecast.weatherservice.view;

import java.util.Date;

public class ForecastView {
    public Date date;

    public String city;

    public String day;

    public String highTemp;

    public String lowTemp;

    public String description;

    @Override
    public String toString() {
        return "ForecastView{" +
                "date=" + date +
                ", city='" + city + '\'' +
                ", day='" + day + '\'' +
                ", highTemp='" + highTemp + '\'' +
                ", lowTemp='" + lowTemp + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
