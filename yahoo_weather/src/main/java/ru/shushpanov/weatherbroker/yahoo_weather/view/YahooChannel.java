package ru.shushpanov.weatherbroker.yahoo_weather.view;


public class YahooChannel {
    private YahooLocation location;
    private YahooItem item;

    public YahooLocation getLocation() {
        return location;
    }

    public void setLocation(YahooLocation location) {
        this.location = location;
    }

    public YahooItem getItem() {
        return item;
    }

    public void setItem(YahooItem item) {
        this.item = item;
    }
}