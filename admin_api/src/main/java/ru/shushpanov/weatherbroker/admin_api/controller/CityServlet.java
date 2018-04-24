package ru.shushpanov.weatherbroker.admin_api.controller;

import ru.shushpanov.weatherbroker.admin_api.service.SendService;

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
    private SendService service;

    @Override
    protected void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse)
            throws ServletException, IOException {
        httpServletRequest.getRequestDispatcher("/WEB-INF/views/index.jsp")
                .forward(httpServletRequest, httpServletResponse);
        String city = httpServletRequest.getParameter("city");
        service.createAndSendMessage(city);
    }
}
