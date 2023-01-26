<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: gab56
  Date: 26.01.2023
  Time: 14:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>bookstore</title>
</head>
<body>
    <c:set var="borrowedBooks" value="${borrowedBooks}" scope="request"/>
    <c:if test="${not empty borrowedBooks}">
        <fieldset>
            <form action="returnBook" method="post">
                <c:forEach var="borrowedBook" items="${borrowedBooks}">
                    <p>
                        <input type="radio" name="book" id="book" value="${borrowedBook}">
                        <label for="book">${borrowedBook}</label>
                    </p>
                </c:forEach>
                <button type="submit">Return</button>
                <p id="feedback">
                    <c:out value="${feedback}"> </c:out>
                </p>
            </form>
        </fieldset>
    </c:if>
    <c:if test="${empty borrowedBooks}">
        <p>No records to display</p>
    </c:if>
    <button type="button"><a href="customerMenu">Back</a></button>
</body>
</html>
