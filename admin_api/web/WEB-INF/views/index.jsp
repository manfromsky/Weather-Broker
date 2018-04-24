<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Погодный брокер</title>
</head>
<body>
<h1>Погодный брокер</h1>
<h3>Введите название города</h3>

<form action="/city" method="GET" modelAttribute="city">
    <input type="text" value="название города" name="city">
    <input type="submit"/>

</form>
</body>
</html>