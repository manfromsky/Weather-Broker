package ru.shushpanov.weatherbroker.adminapi.controller;

import org.apache.commons.lang3.StringUtils;
import ru.shushpanov.weatherbroker.adminapi.service.SendService;
import ru.shushpanov.weatherbroker.error.exeption.WeatherBrokerServiceException;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Сервлет для получения названия города
 */
@WebServlet(urlPatterns = "/city")
public class CityServlet extends HttpServlet {
    @Inject
    private SendService sendService;

    @Override
    protected void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse)
            throws ServletException, IOException {
        httpServletRequest.getRequestDispatcher("/WEB-INF/views/index.jsp")
                .forward(httpServletRequest, httpServletResponse);
        String city = httpServletRequest.getParameter("city");
        if (StringUtils.isNotBlank(city)) {
            try {
                sendService.send(city);
            } catch (WeatherBrokerServiceException e) {
                throw new ServletException(e.getMessage());
            }
        }
    }
}

