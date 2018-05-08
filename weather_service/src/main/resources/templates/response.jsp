<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html xmlns:th="http://www.thymeleaf.org">
<head>
    <title>Book List</title>
</head>
<body>
<h1>Book List</h1>
<div>
    <table border="1">
        <tr>
            <th>Date</th>
            <th>City</th>
            <th>Day</th>
            <th>High Temperature</th>
            <th>Low Temperature</th>
            <th>Description</th>
        </tr>
        <tr th:each="forecastView : ${forecastView}">
            <td th:utext="${forecastView.date}">...</td>
            <td th:utext="${forecastView.city}">...</td>
            <td th:utext="${forecastView.day}">...</td>
            <td th:utext="${forecastView.highTemp}">...</td>
            <td th:utext="${forecastView.lowTemp}">...</td>
            <td th:utext="${forecastView.description}">...</td>
        </tr>
    </table>
</div>