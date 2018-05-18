package ru.shushpanov.weatherbroker.yahoo_weather.service;

import org.apache.activemq.artemis.jms.client.ActiveMQConnectionFactory;
import org.apache.activemq.artemis.jms.client.ActiveMQXAJMSContext;
import org.easymock.EasyMockRule;
import org.easymock.Mock;
import org.easymock.MockType;
import org.easymock.TestSubject;
import org.junit.Rule;
import org.junit.Test;
import org.springframework.web.client.RestTemplate;
import ru.shushpanov.weatherbroker.error.exeption.WeatherBrokerServiceException;
import ru.shushpanov.weatherbroker.messageservice.model.City;
import ru.shushpanov.weatherbroker.messageservice.model.Forecast;
import ru.shushpanov.weatherbroker.messageservice.service.XmlServiceImpl;
import ru.shushpanov.weatherbroker.yahoo_weather.view.Query;
import ru.shushpanov.weatherbroker.yahoo_weather.view.YahooChannel;
import ru.shushpanov.weatherbroker.yahoo_weather.view.YahooForecast;
import ru.shushpanov.weatherbroker.yahoo_weather.view.YahooItem;
import ru.shushpanov.weatherbroker.yahoo_weather.view.YahooLocation;
import ru.shushpanov.weatherbroker.yahoo_weather.view.YahooResult;
import ru.shushpanov.weatherbroker.yahoo_weather.view.YahooWeatherResponse;

import javax.jms.DeliveryMode;
import javax.jms.JMSProducer;
import javax.jms.Queue;
import java.net.URI;
import java.net.URISyntaxException;

import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.verify;

public class ForecastServiceImplTest {
    private String message = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><forecast>" +
            "<date>26 May 2018</date><city>Penza</city><day>Sat</day><highTemp>73</highTemp><lowTemp>59</lowTemp>" +
            "<description>Mostly Cloudy</description></forecast>";
    private String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><City name=\"Penza\"/>";
    private String cityName = "Penza";
    private String nullCityName = null;
    private String emptyCityName = "";
    private City city = new City(cityName);
    private String forecastXml = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><forecast><date>" +
            "26 May 2018</date><city>Penza</city><day>Sat</day><highTemp>73</highTemp><lowTemp>59</lowTemp>" +
            "<description>Mostly Cloudy</description></forecast>";
    private Forecast forecast = new Forecast("18 May 2018", "Penza", "Fri", "78",
            "57", "Scattered Thunderstorms");
    String url = "https://query.yahooapis.com/v1/public/yql?q=select%20location%2C%20item." +
            "forecast%20from%20weather.forecast%20where%20woeid%20in%20(select%20woeid%20from%20geo." +
            "places(1)%20where%20text%3D%22" + cityName + "%2C%20ak%22)&format=json&env=store%3A%2F%2Fdatatables." +
            "org%2Falltableswithkeys";
    private YahooForecast yahooForecast = new YahooForecast("47", "18 May 2018", "Fri", "78",
            "57", "Scattered Thunderstorms");
    private YahooItem item = new YahooItem(yahooForecast);
    private YahooLocation location = new YahooLocation("Penza", "Russia", " Penza Oblast");
    private YahooChannel[] channel = {new YahooChannel(location, item)};
    private YahooResult result = new YahooResult(channel);
    private Query query = new Query(10, "2018-05-18T07:06:57Z", "ru-RU", result);
    private YahooWeatherResponse response = new YahooWeatherResponse(query);

    @Rule
    public EasyMockRule em = new EasyMockRule(this);

    @Mock
    private XmlServiceImpl service;

    @Mock
    private RestTemplate template;

    @Mock
    private Queue queue;

    @Mock
    private ActiveMQConnectionFactory connectionFactory;

    @Mock(MockType.NICE)
    private ActiveMQXAJMSContext context;

    @Mock
    private JMSProducer producer;

    @TestSubject
    private ForecastServiceImpl forecastService = new ForecastServiceImpl(service);

    @Test
    public void testCreateAndSendMessage() throws WeatherBrokerServiceException, URISyntaxException {
        expect(service.readXmlMessage(xml, City.class)).andStubReturn(city);
        expect(service.createXmlMessage(forecast)).andStubReturn(forecastXml);
        expect(connectionFactory.createContext()).andStubReturn(context);
        expect(context.createProducer()).andStubReturn(producer);
        expect(producer.setDeliveryMode(DeliveryMode.PERSISTENT)).andStubReturn(producer);
        expect(producer.send(queue, message)).andStubReturn(producer);
        expect(template.getForObject(new URI(url), YahooWeatherResponse.class)).andStubReturn(response);
        replay(service);
        replay(queue);
        replay(template);
        replay(connectionFactory);
        replay(context);
        replay(producer);
        forecastService.createAndSendMessage(xml);
        verify(service);
        verify(template);
        verify(producer);
        verify(context);
        verify(connectionFactory);
    }

    @Test(expected = WeatherBrokerServiceException.class)
    public void testCreateAndSendMessageWithNullCityName() throws WeatherBrokerServiceException {
        forecastService.createAndSendMessage(nullCityName);
    }

    @Test(expected = WeatherBrokerServiceException.class)
    public void testCreateAndSendMessageWithEmptyCityName() throws WeatherBrokerServiceException {
        forecastService.createAndSendMessage(emptyCityName);
    }
}