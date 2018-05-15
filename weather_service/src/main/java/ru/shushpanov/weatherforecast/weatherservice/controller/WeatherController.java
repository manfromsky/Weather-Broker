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

import javax.persistence.NoResultException;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

/**
 * Контроллер обрабатывает данные, введеные клиентом, для получения прогноза погоды по дате и названию города.
 * Согласно полученным данным, клиенту выводиться прогноз погоды согласно введенным им дате и названию города
 */
@Controller
@SessionAttributes(value = "filter")
@RequestMapping(value = "/api/weatherbroker", produces = APPLICATION_JSON_VALUE)
public class WeatherController {
    private final org.slf4j.Logger log = LoggerFactory.getLogger(WeatherController.class);
    private final WeatherService service;

    @Autowired
    public WeatherController(WeatherService service) {
        this.service = service;
    }

    /**
     * Ввод информации кдиентом, для последующего запроса на получения прогноза погоды
     *
     * @param filter Объект, содержащий информацию необходимую для поиска прогноза в базе данных
     * @return Форма для ввода информации клиентом
     */
    @RequestMapping(value = {"/forecast"}, method = RequestMethod.GET)
    public ModelAndView getForecastFilter(@ModelAttribute("filter") ForecastFilter filter) {
        ModelAndView view = new ModelAndView();
        view.addObject("filter", filter);
        view.addObject("date", filter.date);
        view.addObject("city", filter.city);
        view.setViewName("request");
        return view;
    }

    @ModelAttribute("filter")
    public ForecastFilter createFilter() {
        return new ForecastFilter();
    }

    /**
     * Отображении информации о запрашиваемом прогнозе
     *
     * @param filter Объект, заполненный информацией необходимой для поиска прогноза в базе данных
     * @return Форма для вывода информации о запрашиваемом прогнозе
     * @throws WeatherBrokerServiceException Исключение, сгенерированное неправильным воодом информации,
     *                                       необходимой для запроса к базе данных
     */
    @RequestMapping(value = "/forecast/submit", method = RequestMethod.POST)
    public ModelAndView getForecast(@ModelAttribute("filter") ForecastFilter filter)
            throws WeatherBrokerServiceException {
        ModelAndView view = new ModelAndView();
        log.info("Filter is: {}", filter);
        view.setViewName("response");
        ForecastView forecastView;
        try {
            forecastView = service.getForecastByFilter(filter);
        } catch (NoResultException e) {
            return new ModelAndView("redirect:/api/weatherbroker/error");
        }
        view.addObject("forecastView", forecastView);
        return view;
    }

    /**
     * Отображение информации об ошибке приложения
     *
     * @return Форма, информирующая об ошибке работы сервиса
     */
    @RequestMapping(value = "/error")
    public ModelAndView error() {
        ModelAndView view = new ModelAndView();
        view.setViewName("error");
        return view;
    }
}

