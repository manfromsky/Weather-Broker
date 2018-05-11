<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<head>
    <title align="center"> Forecast</title>
</head>
<body>
<h1 align="center">Forecast</h1>
<h3 align="center">Date</h3>
<div align="center"><c:out value="${forecastView.date}"/></div>
<h3 align="center">City</h3>
<div align="center"><c:out value="${forecastView.city}"/></div>
<h3 align="center">Day</h3>
<div align="center"><c:out value="${forecastView.day}"/></div>
<h3 align="center">Temperature</h3>
<div align="center"><c:out value="${forecastView.lowTemp}"/> - <c:out value="${forecastView.highTemp}"/></div>
<h3 align="center">Description</h3>
<div align="center"><c:out value="${forecastView.description}"/></div>
