package ru.shushpanov.weatherbroker.database.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.Objects;

/**
 * Прогноз погоды
 */
@Entity
@Table(name = "forecast")
public class ForecastEntity {

    /**
     * Идентификатор проноза
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    /**
     * Служебное поле hibernate
     */
    @Version
    private Integer version;

    /**
     * Дата прогноза
     */
    @NotNull
    @Temporal(value = TemporalType.DATE)
    @Column(name = "forecast_date")
    private Date date;

    /**
     * Название города
     */
    @NotNull
    @Column(name = "read_city")
    private String city;

    /**
     * День прогноза
     */
    @NotNull
    @Column(name = "forecast_day", length = 3)
    private String day;

    /**
     * Верхняя граница темпиратуры
     */
    @NotNull
    @Column(name = "high_temp", length = 3)
    private String highTemp;

    /**
     * Нижняя граница температуры
     */
    @NotNull
    @Column(name = "low_temp ", length = 3)
    private String lowTemp;

    /**
     * Описание погоды
     */
    @NotNull
    @Column(name = "description")
    private String description;

    public ForecastEntity(@NotNull Date date, @NotNull String city, @NotNull String day, @NotNull String highTemp,
                          @NotNull String lowTemp, @NotNull String description) {
        this.date = date;
        this.city = city;
        this.day = day;
        this.highTemp = highTemp;
        this.lowTemp = lowTemp;
        this.description = description;
    }

    public ForecastEntity() {
    }

    public Long getId() {
        return id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getHighTemp() {
        return highTemp;
    }

    public void setHighTemp(String highTemp) {
        this.highTemp = highTemp;
    }

    public String getLowTemp() {
        return lowTemp;
    }

    public void setLowTemp(String lowTemp) {
        this.lowTemp = lowTemp;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public String toString() {
        return "ForecastEntity{" +
                "id=" + id +
                ", version=" + version +
                ", date=" + date +
                ", city='" + city + '\'' +
                ", day='" + day + '\'' +
                ", highTemp='" + highTemp + '\'' +
                ", lowTemp='" + lowTemp + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ForecastEntity entity = (ForecastEntity) o;
        return Objects.equals(date, entity.date) &&
                Objects.equals(city, entity.city) &&
                Objects.equals(day, entity.day) &&
                Objects.equals(highTemp, entity.highTemp) &&
                Objects.equals(lowTemp, entity.lowTemp) &&
                Objects.equals(description, entity.description);
    }

    @Override
    public int hashCode() {

        return Objects.hash(date, city, day, highTemp, lowTemp, description);
    }
}





