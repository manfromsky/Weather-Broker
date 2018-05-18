package ru.shushpanov.weatherbroker.yahoo_weather.service;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestTemplate;
import ru.shushpanov.weatherbroker.error.exeption.WeatherBrokerServiceException;
import ru.shushpanov.weatherbroker.messageservice.model.City;
import ru.shushpanov.weatherbroker.messageservice.model.Forecast;
import ru.shushpanov.weatherbroker.messageservice.service.XmlService;
import ru.shushpanov.weatherbroker.yahoo_weather.view.YahooChannel;
import ru.shushpanov.weatherbroker.yahoo_weather.view.YahooWeatherResponse;

import javax.annotation.Resource;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.jms.ConnectionFactory;
import javax.jms.DeliveryMode;
import javax.jms.JMSContext;
import javax.jms.JMSProducer;
import javax.jms.Queue;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashSet;
import java.util.Set;

/**
 * {@inheritDoc}
 */
@RequestScoped
public class ForecastServiceImpl implements ForecastService {
    private final Logger log = LoggerFactory.getLogger(ForecastServiceImpl.class);
    private static final String WEATHER_QUEUE = "java:jboss/weatherQueue";
    private static final String CONNECTION = "java:comp/DefaultJMSConnectionFactory";

    @Resource(name = WEATHER_QUEUE)
    private Queue queue;

    @Resource(name = CONNECTION)
    private ConnectionFactory connection;

    private XmlService xmlService;
    private RestTemplate restTemplate = new RestTemplate();

    @Inject
    public ForecastServiceImpl(XmlService xmlService) {
        this.xmlService = xmlService;
    }

    public ForecastServiceImpl() {
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void createAndSendMessage(String xml) throws WeatherBrokerServiceException {
        if (StringUtils.isBlank(xml)) {
            throw new WeatherBrokerServiceException("Please enter the name of the city");
        }
        City city = xmlService.readXmlMessage(xml, City.class);
        String cityName = city.getName();
        YahooWeatherResponse response = getResponseFromYahooWeather(cityName);
        try (JMSContext context = connection.createContext()) {
            JMSProducer producer = context.createProducer().setDeliveryMode(DeliveryMode.PERSISTENT);
            Set<Forecast> forecastSet = getForecastSetFromYahooResponse(response);
            for (Forecast wf : forecastSet) {
                String message = xmlService.createXmlMessage(wf);
                producer.send(queue, message);
                log.info(String.format("Message to send: %s", message));
            }
        }
    }

    /**
     * Получить ответ от сервиса Yahoo.weather
     *
     * @param city Название города
     * @return Ответ от Yahoo Weather API
     * @throws WeatherBrokerServiceException Ошибка сгенерированная при попытке запроса с сервесу Yahoo Weather API
     */
    private YahooWeatherResponse getResponseFromYahooWeather(String city) throws WeatherBrokerServiceException {
        YahooWeatherResponse response;
        String url = "https://query.yahooapis.com/v1/public/yql?q=select%20location%2C%20item." +
                "forecast%20from%20weather.forecast%20where%20woeid%20in%20(select%20woeid%20from%20geo." +
                "places(1)%20where%20text%3D%22" + city + "%2C%20ak%22)&format=json&env=store%3A%2F%2Fdatatables." +
                "org%2Falltableswithkeys";
        try {
            response = restTemplate.getForObject(new URI(url), YahooWeatherResponse.class);
        } catch (URISyntaxException e) {
            throw new WeatherBrokerServiceException(
                    String.format("Failed to get a response from the Yahoo Weather API service in the city: %s", city),
                    e
            );
        }
        return response;
    }

    /**
     * Получить список погоды за 10 дней
     *
     * @param response Объект содержащий всю информацию по погоде в интересующем нас городе,
     *                 сформированный из ответа сервиса Yahoo Weather API
     * @return Множество объектов с конкретно интересующей нас информацией о погоде. Полученные объекты могут
     * использоваься при создания xml для jms сообщения
     */
    private Set<Forecast> getForecastSetFromYahooResponse(YahooWeatherResponse response) {
        Set<Forecast> forecastSet = new HashSet<>();
        YahooChannel[] channel = response
                .getQuery()
                .getResults()
                .getChannel();
        for (YahooChannel c : channel) {
            Forecast forecast = new Forecast();
            forecast.setDate(c.getItem().getForecast().getDate());
            forecast.setCity(c.getLocation().getCity());
            forecast.setDay(c.getItem().getForecast().getDay());
            forecast.setHighTemp(c.getItem().getForecast().getHigh());
            forecast.setLowTemp(c.getItem().getForecast().getLow());
            forecast.setDescription(c.getItem().getForecast().getText());
            forecastSet.add(forecast);
        }
        return forecastSet;
    }
}