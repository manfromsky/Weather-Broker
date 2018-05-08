package ru.shushpanov.weatherforecast.weatherservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.shushpanov.weatherbroker.error.exeption.WeatherBrokerServiceException;
import ru.shushpanov.weatherforecast.weatherservice.service.WeatherService;
import ru.shushpanov.weatherforecast.weatherservice.view.ForecastFilter;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Controller
@RequestMapping(value = "/api/weatherbroker", produces = APPLICATION_JSON_VALUE)
public class WeatherController {

    private final WeatherService service;

    @Autowired
    public WeatherController(WeatherService service) {
        this.service = service;
    }

    @RequestMapping(value = {"/forecast"})
    public String getForecastFilter(Model model) {
        model.addAttribute("filter", new ForecastFilter());
        return "/request";
    }

    @RequestMapping(value = "/forecast/submit", method = RequestMethod.POST)
    public String getForecast(@ModelAttribute ForecastFilter filter, Model model) throws WeatherBrokerServiceException {
        model.addAttribute("forecastView", service.getForecastByDate(filter));
        return "/response";
    }
}

