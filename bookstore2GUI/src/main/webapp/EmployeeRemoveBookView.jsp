<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: gab56
  Date: 26.01.2023
  Time: 17:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>bookstore</title>
</head>
<body>
    <c:set var="booksToRemove" value="${booksToRemove}" scope="request"/>
    <c:if test="${not empty booksToRemove}">
        <fieldset>
            <form action="removeBook" method="post">
                <c:forEach var="bookToRemove" items="${booksToRemove}">
                    <p>
                        <input type="radio" name="bookToRemove" id="bookToRemove" value="${bookToRemove}">
                        <label for="bookToRemove">${bookToRemove}</label>
                    </p>
                </c:forEach>
                <button type="submit">Remove</button>
                <p id="feedback">
                    <c:out value="${feedback}"> </c:out>
                </p>
            </form>
        </fieldset>
    </c:if>
    <c:if test="${empty booksToRemove}">
        <p>No records to display</p>
    </c:if>
    <button type="button"><a href="employeeMenu">Back</a></button>
</body>
</html>
