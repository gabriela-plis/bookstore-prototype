<%--
  Created by IntelliJ IDEA.
  User: gab56
  Date: 22.01.2023
  Time: 20:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Hello servlet</title>
</head>
<body>

    <form action="customerLogIn" method="post">
        <fieldset>
            <p>
                <%--@declare id="id"--%><label for="ID">ID: </label>
                <input type="number" name="ID" id="ID" required>
            </p>
            <p>
                <%--@declare id="password"--%><label for="password">Password: </label>
                <input type="password" name="password" id="password" required>
            </p>
            <button type="submit">Log In</button>
            <button type="reset">Reset</button>
            <p id="feedback">
                <c:out value="${feedback}"> </c:out>
            </p>
        </fieldset>
            <button type="button"><a href="/">Back</a></button>
    </form>

</body>
</html>
