package ru.shushpanov.weatherbroker.yahoo_weather.view;


public class YahooItem {

    private YahooForecast forecast;

    public YahooItem(YahooForecast forecast) {
        this.forecast = forecast;
    }

    public YahooItem() {
    }

    public YahooForecast getForecast() {
        return forecast;
    }

    public void setForecast(YahooForecast forecast) {
        this.forecast = forecast;
    }
}
