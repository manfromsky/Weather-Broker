package ru.shushpanov.weatherbroker.database.entity;

import javax.persistence.*;
import java.util.Date;

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
    @Temporal(value = TemporalType.DATE)
    @Column(name = "date")
    private Date date;

    /**
     * Название города
     */
    @Column(name = "city")
    private String city;

    /**
     * День прогноза
     */
    @Column(name = "day")
    private String day;

    /**
     * Верхний уровень темпиратуры
     */
    @Column(name = "high_temp")
    private String highTemp;

    /**
     * Нижний уровень темпиратуры
     */
    @Column(name = "low_temp ")
    private String lowTemp;

    /**
     * Описание погоды
     */
    @Column(name = "description")
    private String description;

    public ForecastEntity() {
    }

    public ForecastEntity(Date date, String city, String day, String highTemp, String lowTemp, String description) {
        this.date = date;
        this.city = city;
        this.day = day;
        this.highTemp = highTemp;
        this.lowTemp = lowTemp;
        this.description = description;
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
}





