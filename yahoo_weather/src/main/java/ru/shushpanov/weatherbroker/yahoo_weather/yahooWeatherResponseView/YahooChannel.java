package ru.shushpanov.weatherbroker.yahoo_weather.yahooWeatherResponseView;


public class YahooChannel {

    private YahooLocationItem[] yahooLocationItems;

    public YahooLocationItem[] getYahooForecast() {
        return yahooLocationItems;
    }

    public YahooLocationItem[] getYahooLocationItems() {
        return yahooLocationItems;
    }

    public void setYahooLocationItems(YahooLocationItem[] yahooLocationItems) {
        this.yahooLocationItems = yahooLocationItems;
    }
}
