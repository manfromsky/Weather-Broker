package ru.shushpanov.weatherforecast.weatherservice.controller;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import ru.shushpanov.weatherbroker.error.exeption.WeatherBrokerServiceException;
import ru.shushpanov.weatherforecast.weatherservice.service.WeatherService;
import ru.shushpanov.weatherforecast.weatherservice.view.ForecastFilter;
import ru.shushpanov.weatherforecast.weatherservice.view.ForecastView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Controller
@SessionAttributes(value = "filter")
@RequestMapping(value = "/api/weatherbroker", produces = APPLICATION_JSON_VALUE)
public class WeatherController {
    private final org.slf4j.Logger log = LoggerFactory.getLogger(WeatherController.class);
//    private final WeatherService service;
//
//    @Autowired
//    public WeatherController(WeatherService service) {
//        this.service = service;
//    }

    @RequestMapping(value = {"/forecast"}, method = RequestMethod.GET)
    public ModelAndView getForecastFilter(@ModelAttribute("filter") ForecastFilter filter) {
        ModelAndView view = new ModelAndView();
        view.addObject("date", filter.date);
        view.addObject("city", filter.city);
        view.setViewName("request");
        return view;
    }

    @ModelAttribute("filter")
    public ForecastFilter createFilter() {
        return new ForecastFilter();
    }

    @RequestMapping(value = "/forecast/submit", method = RequestMethod.POST)
    public ModelAndView getForecast(@ModelAttribute("filter") ForecastFilter filter)
            throws WeatherBrokerServiceException {
        ModelAndView view = new ModelAndView();
        log.info("Filter is: {}", filter);
        view.setViewName("response");
        ForecastView forecastView = new ForecastView(new Date(), "city", "day",
                "hi", "low", "description");
        view.addObject("forecastView", forecastView);
        return view;
    }
}

