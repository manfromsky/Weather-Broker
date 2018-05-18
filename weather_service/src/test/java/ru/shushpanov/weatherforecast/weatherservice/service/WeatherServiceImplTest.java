package ru.shushpanov.weatherforecast.weatherservice.service;

import org.easymock.EasyMockRule;
import org.easymock.Mock;
import org.easymock.TestSubject;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import ru.shushpanov.weatherbroker.database.entity.ForecastEntity;
import ru.shushpanov.weatherbroker.error.exeption.WeatherBrokerServiceException;
import ru.shushpanov.weatherforecast.weatherservice.dao.WeatherDaoImpl;
import ru.shushpanov.weatherforecast.weatherservice.view.ForecastFilter;
import ru.shushpanov.weatherforecast.weatherservice.view.ForecastView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.verify;

public class WeatherServiceImplTest {
    private SimpleDateFormat format = new SimpleDateFormat("dd MMM yyyy", Locale.US);
    private Date date = format.parse("21 May 2018");
    private String city = "Penza";
    private ForecastFilter nullFilter = null;
    private ForecastFilter filterWithNullDate = new ForecastFilter(null, city);
    private ForecastFilter filter = new ForecastFilter(date, city);
    private ForecastEntity entity = new ForecastEntity(date, city, "Mon", "74", "54",
            "Scattered Thunderstorms");
    private ForecastView view = new ForecastView(date, city, "Mon", "74", "54",
            "Scattered Thunderstorms");

    @Rule
    public EasyMockRule em = new EasyMockRule(this);

    @Mock
    private WeatherDaoImpl dao;

    @TestSubject
    private WeatherServiceImpl service = new WeatherServiceImpl(dao);

    public WeatherServiceImplTest() throws ParseException {
    }

    /**
     * Проверяем вызов метода WeatherDaoImpl.getByCityAndDate. Сравниваем результат работы метода
     * WeatherServiceImpl.getForecastByFilter c эталоном
     *
     * @throws WeatherBrokerServiceException Исключение, сгенерированное некорректными данными,
     *                                       пришедшими от пользователя
     */
    @Test
    public void testGetForecastByFilter() throws WeatherBrokerServiceException {
        expect(dao.getByCityAndDate(filter)).andStubReturn(entity);
        replay(dao);
        Assert.assertEquals(service.getForecastByFilter(filter), view);
        verify(dao);
    }

    /**
     * Проверяем сгенерируется ли исключении при полученнии методом WeatherServiceImpl.getForecastByFilter()
     * аргумента равного null
     *
     * @throws WeatherBrokerServiceException Исключение, сгенерированное некорректными данными,
     *                                       пришедшими от пользователя
     */
    @Test(expected = WeatherBrokerServiceException.class)
    public void testGetForecastByNullFilter() throws WeatherBrokerServiceException {
        service.getForecastByFilter(nullFilter);
    }
}