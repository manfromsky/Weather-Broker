package ru.shushpanov.weatherbroker.database.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.shushpanov.weatherbroker.database.dao.ForecastDao;
import ru.shushpanov.weatherbroker.database.entity.ForecastEntity;
import ru.shushpanov.weatherbroker.error.exeption.WeatherBrokerServiceException;
import ru.shushpanov.weatherbroker.messageservice.model.Forecast;
import ru.shushpanov.weatherbroker.messageservice.service.MessageService;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

@RequestScoped
public class DataServiceImpl implements DataService {
    private final Logger log = LoggerFactory.getLogger(DataServiceImpl.class);
    private MessageService service;
    private ForecastDao dao;

    @Inject
    public DataServiceImpl(MessageService service, ForecastDao dao) {
        this.service = service;
        this.dao = dao;
    }

    public DataServiceImpl() {
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void save(String xml) throws WeatherBrokerServiceException {
        Forecast forecast = transformXmlMessageToModel(xml);
        log.debug("Transformed object from xml: " + forecast);
        ForecastEntity entity = transformFromModelToEntity(forecast);
        dao.save(entity);
        log.debug("Saved entity: " + entity);
    }

    /**
     * Преобразование строки xml в объект, содержащий информацию о погоде
     *
     * @param xml Строка содержащая xml с данными прогноза погоды
     * @return Объект с полями заполненными данными о прогнозе
     * @throws WeatherBrokerServiceException Ошибка, возникшая при преобразовании xml в объект
     */
    private Forecast transformXmlMessageToModel(String xml) throws WeatherBrokerServiceException {
        return service.readXmlMessage(xml, Forecast.class);
    }

    /**
     * Преобразование объекта, содержащего информацию о погоде, в сущность, сопоставленную с таблицей в базе данных
     *
     * @param forecast Объект, содержащий информацию о погоде
     * @return Сущность сопоставленная с таблицей в базе данных
     * @throws WeatherBrokerServiceException Ошибка, сгенерированная при попытке преобразования строки с датой в объект
     */
    private ForecastEntity transformFromModelToEntity(Forecast forecast) throws WeatherBrokerServiceException {
        ForecastEntity entity = new ForecastEntity();
        Date date = transformFromStringToDate(forecast.getDate());
        entity.setDate(date);
        entity.setCity(forecast.getCity());
        entity.setDay(forecast.getDay());
        entity.setHighTemp(forecast.getHighTemp());
        entity.setLowTemp(forecast.getLowTemp());
        entity.setDescription(forecast.getDescription());
        return entity;
    }

    /**
     * Преобразование строки, содержащей дату, в объект
     *
     * @param date Строка содержащая дату
     * @return Дата
     * @throws WeatherBrokerServiceException Ошибка, сгенерированная при попытке преобразования строки с датой в объект
     */
    private Date transformFromStringToDate(String date) throws WeatherBrokerServiceException {
        Date result;
        String datePattern = "dd MMM yyyy";
        SimpleDateFormat format = new SimpleDateFormat(datePattern, Locale.US);
        try {
            result = format.parse(date);
        } catch (ParseException e) {
            throw new WeatherBrokerServiceException("Internal service error", e);
        }
        return result;
    }
}
