package ru.shushpanov.weatherbroker.messageservice.service;

import org.junit.Assert;
import org.junit.Test;
import ru.shushpanov.weatherbroker.error.exeption.WeatherBrokerServiceException;
import ru.shushpanov.weatherbroker.messageservice.model.City;
import ru.shushpanov.weatherbroker.messageservice.model.Forecast;


public class XmlServiceImplTest {
    private String city = "Penza";
    private City cityModel = new City(city);
    private String cityXml = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>" +
            "<City name=\"Penza\"/>";
    private String forecastXml = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><forecast><date>" +
            "26 May 2018</date><city>Penza</city><day>Sat</day><highTemp>73</highTemp><lowTemp>59</lowTemp>" +
            "<description>Mostly Cloudy</description></forecast>";
    private Forecast forecast = new Forecast("26 May 2018", "Penza", "Sat", "73",
            "59", "Mostly Cloudy");
    private XmlServiceImpl service = new XmlServiceImpl();

    @Test
    public void testCreateXmlMessage() throws WeatherBrokerServiceException {
        Assert.assertEquals(service.createXmlMessage(cityModel), cityXml);
        Assert.assertEquals(service.createXmlMessage(forecast), forecastXml);
    }

    @Test
    public void testReadXmlMessage() throws WeatherBrokerServiceException {
        Assert.assertEquals(service.readXmlMessage(cityXml, City.class), cityModel);
        Assert.assertEquals(service.readXmlMessage(forecastXml, Forecast.class), forecast);
    }
}