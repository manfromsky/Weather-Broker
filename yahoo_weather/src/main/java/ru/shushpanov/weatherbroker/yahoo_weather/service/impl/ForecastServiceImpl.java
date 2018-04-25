package ru.shushpanov.weatherbroker.yahoo_weather.service.impl;

import org.springframework.web.client.RestTemplate;
import ru.shushpanov.weatherbroker.messageservice.service.MessageService;
import ru.shushpanov.weatherbroker.messageservice.service.model.ReadCity;
import ru.shushpanov.weatherbroker.messageservice.service.model.WriteForecast;
import ru.shushpanov.weatherbroker.yahoo_weather.service.ForecastService;
import ru.shushpanov.weatherbroker.yahoo_weather.yahooWeatherResponseView.YahooLocationItem;
import ru.shushpanov.weatherbroker.yahoo_weather.yahooWeatherResponseView.YahooWeatherResponse;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.jms.Topic;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashSet;
import java.util.Set;

public class ForecastServiceImpl implements ForecastService {

    private static final String WEATHER_TOPIC = "java:jboss/weatherTopic";

    @Resource(name = WEATHER_TOPIC)
    private Topic topic;

    private final MessageService messageService;
    private final RestTemplate restTemplate;

    @Inject
    public ForecastServiceImpl(MessageService messageService) {
        this.messageService = messageService;
        this.restTemplate = new RestTemplate();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void createAndSendMessage(String xml) throws URISyntaxException {
        ReadCity readCity = (ReadCity) messageService.readXmlMessage(ReadCity.class, xml);
        String cityName = readCity.getName();
        YahooWeatherResponse response = getResponseFromYahooWeather(cityName);
        Set<WriteForecast> writeForecastSet = getForecastSetFromYahooResponse(response);
        String weatherResult = messageService.createXmlMessage(writeForecastSet);
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
    private Set<WriteForecast> getForecastSetFromYahooResponse(YahooWeatherResponse response) {
        Set<WriteForecast> writeForecastSet = new HashSet<>();
        YahooLocationItem[] yahooLocationItems = response
                .getYahooQuery()
                .getResults()
                .getChannel()
                .getYahooLocationItems();
        for (YahooLocationItem item : yahooLocationItems) {
            WriteForecast writeForecast = new WriteForecast();
            writeForecast.setDate(item.getItem().getForecast().getDate());
            writeForecast.setCity(item.getLocation().getCity());
            writeForecast.setDay(item.getItem().getForecast().getDay());
            writeForecast.setHighTemp(item.getItem().getForecast().getHigh());
            writeForecast.setLowTemp(item.getItem().getForecast().getLow());
            writeForecast.setDescription(item.getItem().getForecast().getText());
            writeForecastSet.add(writeForecast);
        }
        return writeForecastSet;
    }
}