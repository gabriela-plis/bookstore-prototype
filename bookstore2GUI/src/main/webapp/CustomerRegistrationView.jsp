<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: gab56
  Date: 24.01.2023
  Time: 19:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>bookstore</title>
</head>
<body>

    <form action="customerRegistration" method="post">
        <fieldset>
            <legend>Fill required data</legend>
            <p>
                <label for="firstName">First Name:</label>
                <input type="text" name="firstName" id="firstName" autocomplete="on" required>
            </p>
            <p>
                <label for="lastName">Second Name:</label>
                <input type="text" name="lastName" id="lastName" autocomplete="on" required>
            </p>
            <p>
                <label for="phone">Phone:</label>
                <input type="tel" name="phone" id="phone" placeholder="555-555-555" pattern="[0-9]{3}-[0-9]{3}-[0-9]{3}"
                       required>
            </p>
            <p>
                <%--@declare id="password"--%><label for="password">Password:</label>
                <input type="password" name="password" id="password" required>
            </p>
<%--            <p>--%>
<%--                &lt;%&ndash;@declare id="repeatpassword"&ndash;%&gt;<label for="repeatPassword">Repeat password:</label>--%>
<%--                <input type="password" name="repeatPassword" id="repeatPassword" pattern="${password}" required>--%>
<%--            </p>--%>
            <p>
                <label for="email">E-mail:</label>
                <input type="text" name="email" id="email" autocomplete="on" required>
            </p>
        </fieldset>
        <br>
        <button type="submit">Submit</button>
        <button type="reset">Reset</button>
        <p id="feedback">
            <c:out value="${feedback}"> </c:out>
        </p>
    </form>

<button type="button"><a href="/">Back</a></button>

</body>
</html>
