package ru.shushpanov.weatherbroker.messageservice.service.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Set;

/**
 * Модель для преобразования погоды за 10 дней в xml для передачи сообщения
 */
@XmlRootElement(name = "weather")
public class WriteWeather {

    @XmlElementWrapper(name = "writeForecasts")
    @XmlElement(name = "forecast")
    private Set<WriteForecast> writeForecasts;

    private Set<WriteForecast> getForecastSet() {
        return writeForecasts;
    }

    public void setForecastSet(Set<WriteForecast> writeForecasts) {
        this.writeForecasts = writeForecasts;
    }
}
