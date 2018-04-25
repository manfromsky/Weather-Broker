package ru.shushpanov.weatherbroker.adminapi.controller;

import ru.shushpanov.weatherbroker.adminapi.service.SendService;

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
        sendService.createAndSendMessage(city);
    }
}
