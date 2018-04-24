package ru.shushpanov.weatherbroker.yahoo_weather.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.shushpanov.weatherbroker.yahoo_weather.model.City;
import ru.shushpanov.weatherbroker.yahoo_weather.model.Forecast;
import ru.shushpanov.weatherbroker.yahoo_weather.service.ForecastService;
import ru.shushpanov.weatherbroker.message_service.service.MessageService;
import ru.shushpanov.weatherbroker.yahoo_weather.yahooWeatherResponseView.YahooLocationItem;
import ru.shushpanov.weatherbroker.yahoo_weather.yahooWeatherResponseView.YahooWeatherResponse;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashSet;
import java.util.Set;

@Service
public class ForecastServiceImpl implements ForecastService {

    private final MessageService messageService;
    private final RestTemplate restTemplate;

    @Autowired
    public ForecastServiceImpl(MessageService messageService) {
        this.messageService = messageService;
        this.restTemplate = new RestTemplate();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void createAndSendMessage(String xml) throws URISyntaxException {
        City city = (City) messageService.readXmlMessage(City.class, xml);
        String cityName = city.getName();
        YahooWeatherResponse response = getResponseFromYahooWeather(cityName);
        Set<Forecast> forecastSet = getForecastSetFromYahooResponse(response);
        String weatherResult = messageService.createXmlMessage(forecastSet);
        sendMessage(weatherResult);
    }

    /**
     * Получить ответ от сервиса Yahoo.weather
     */
    private YahooWeatherResponse getResponseFromYahooWeather(String city) throws URISyntaxException {
        String url = "https://query.yahooapis.com/v1/public/yql?q=select%20location%2C%20item." +
                "forecast%20from%20weather.forecast%20where%20woeid%20in%20(select%20woeid%20from%20geo." +
                "places(1)%20where%20text%3D%22" + city + "%2C%20ak%22)&format=json&env=store%3A%2F%2Fdatatables." +
                "org%2Falltableswithkeys";

        return restTemplate.getForObject(new URI(url), YahooWeatherResponse.class);
    }

    /**
     * Получить список погоды за 10 дней
     */
    private Set<Forecast> getForecastSetFromYahooResponse(YahooWeatherResponse response) {
        Set<Forecast> forecastSet = new HashSet<>();
        YahooLocationItem[] yahooLocationItems = response
                .getYahooQuery()
                .getResults()
                .getChannel()
                .getYahooLocationItems();
        for (YahooLocationItem item : yahooLocationItems) {
            Forecast forecast = new Forecast();
            forecast.setDate(item.getItem().getForecast().getDate());
            forecast.setCity(item.getLocation().getCity());
            forecast.setDay(item.getItem().getForecast().getDay());
            forecast.setHighTemp(item.getItem().getForecast().getHigh());
            forecast.setLowTemp(item.getItem().getForecast().getLow());
            forecast.setDescription(item.getItem().getForecast().getText());
            forecastSet.add(forecast);
        }
        return forecastSet;
    }

    /**
     * Отправка сообщения
     */
    private void sendMessage(String xml) {

    }
}