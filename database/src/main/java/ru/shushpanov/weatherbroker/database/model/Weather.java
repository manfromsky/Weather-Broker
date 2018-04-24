package ru.shushpanov.weatherbroker.database.model;

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
    public Set<Forecast> forecasts;

    public Set<Forecast> getForecasts() {
        return forecasts;
    }

    public void setForecasts(Set<Forecast> forecasts) {
        this.forecasts = forecasts;
    }
}
