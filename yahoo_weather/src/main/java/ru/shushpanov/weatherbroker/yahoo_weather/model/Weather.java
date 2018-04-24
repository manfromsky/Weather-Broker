package ru.shushpanov.weatherbroker.yahoo_weather.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Set;

/**
 * Модель для преобразования погоды за 10 дней в xml для передачи сообщения
 */
@XmlRootElement(name = "weather")
public class Weather {

    @XmlElementWrapper(name = "forecasts")
    @XmlElement(name = "forecast")
    private Set<Forecast> forecasts;

    private Set<Forecast> getForecastSet() {
        return forecasts;
    }

    public void setForecastSet(Set<Forecast> forecasts) {
        this.forecasts = forecasts;
    }
}
