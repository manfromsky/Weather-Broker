package ru.shushpanov.weatherbroker.database.service;

import org.easymock.EasyMockRule;
import org.easymock.Mock;
import org.easymock.MockType;
import org.easymock.TestSubject;
import org.junit.Rule;
import org.junit.Test;
import ru.shushpanov.weatherbroker.database.dao.ForecastDaoImpl;
import ru.shushpanov.weatherbroker.error.exeption.WeatherBrokerServiceException;
import ru.shushpanov.weatherbroker.messageservice.model.Forecast;
import ru.shushpanov.weatherbroker.messageservice.service.XmlServiceImpl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.verify;

public class DataServiceImplTest {

    private Forecast forecast = new Forecast("21 May 2018", "Penza", "Mon", "74",
            "54",
            "Scattered Thunderstorms");
    private Forecast forecastWithWrongDate = new Forecast("jakщфоашщ11", "Penza", "Mon",
            "74", "54",
            "Scattered Thunderstorms");
    private SimpleDateFormat format = new SimpleDateFormat("dd MMM yyyy", Locale.US);
    private Date date = format.parse(forecast.getDate());
    private String city = "Penza";
    private String xml = "test";
    private String nullXml = null;
    private String emptyXml = "";

    @Rule
    public EasyMockRule em = new EasyMockRule(this);

    @Mock
    private XmlServiceImpl service;

    @Mock(MockType.NICE)
    private ForecastDaoImpl dao;

    @TestSubject
    private DataServiceImpl dataService = new DataServiceImpl(service, dao);

    public DataServiceImplTest() throws ParseException {
    }

    /**
     * Проверяем вызов методов: XmlServiceImpl.readXmlMessage(String xml, Class<T> modelClass),
     * ForecastDaoImpl.isForecastDuplicate(Date date, String city)
     *
     * @throws WeatherBrokerServiceException Исключение, сгенерированное при полученни методом пустой или равной null
     *                                       строки
     */
    @Test
    public void testSave() throws WeatherBrokerServiceException {
        expect(service.readXmlMessage(xml, Forecast.class)).andStubReturn(forecast);
        expect(dao.isForecastDuplicate(date, city)).andStubReturn(true);
        replay(service);
        replay(dao);
        dataService.save(xml);
        verify(service);
        verify(dao);
    }

    /**
     * Проверяем, сгенерируется ли исключение при получении методом строки равной null
     *
     * @throws WeatherBrokerServiceException Исключение, сгенерированное при полученни методом пустой или равной null
     *                                       строки
     */
    @Test(expected = WeatherBrokerServiceException.class)
    public void testSaveWithNullXml() throws WeatherBrokerServiceException {
        dataService.save(nullXml);
    }

    /**
     * Проверяем сгенерируется ли исключение при получении методом пустой строки
     *
     * @throws WeatherBrokerServiceException Исключение, сгенерированное при полученни методом пустой или равной null
     *                                       строки
     */
    @Test(expected = WeatherBrokerServiceException.class)
    public void testSaveWithEmptyXml() throws WeatherBrokerServiceException {
        dataService.save(emptyXml);
    }

    /**
     * Проверяем сгенерируется ли исключение при возвращении методом
     * XmlServiceImpl.readXmlMessage(String xml, Class<T> modelClass) объекта класса
     * {@link ru.shushpanov.weatherbroker.messageservice.model.Forecast} c полем Forecast.date равным неккоректному
     * значению
     *
     * @throws WeatherBrokerServiceException Ошибка, сгенерированная при попытке преобразования строки с датой в объект
     */
    @Test(expected = WeatherBrokerServiceException.class)
    public void testSaveWithWrongDate() throws WeatherBrokerServiceException {
        expect(service.readXmlMessage(xml, Forecast.class)).andStubReturn(forecastWithWrongDate);
        replay(service);
        dataService.save(xml);
    }
}