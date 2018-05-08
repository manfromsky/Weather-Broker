<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html xmlns:th="http://www.thymeleaf.org">
</head>
<body>
<div class="container">
    <div class="row">
        <div class="col-md-12">
            <h1 class="page-header">Weather Broker</h1>
            <form action="#" th:action="@{registration/submit}"
                  th:object="${filter}" method="POST">
                <div class="form-group">
                    <label>Date</label>
                    <input type="date" class="form-control" th:field="*{date}"/>
                </div>
                <div class="form-group">
                    <label>City</label>
                    <input type="text" class="form-control" th:field="*{city}"/>
                </div>

                <button type="submit" class="btn btn-default">Ok</button>
            </form>
        </div>
    </div>
</div>
</body>
</html>