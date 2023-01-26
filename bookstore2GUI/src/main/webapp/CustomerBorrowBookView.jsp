<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: gab56
  Date: 25.01.2023
  Time: 16:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>bookstore</title>
</head>
<body>
<fieldset>
    <legend>Books to borrow:</legend>
    <c:set var="books" value="${books}" scope="request"/>

    <c:if test="${not empty books}">
        <form action=""
        <c:forEach var="book" items="${books}">
            <p>
                <input type="radio" name="book" id="book" value="book">
                <label for="book">${book}</label>
            </p>
        </c:forEach>
    </c:if>


    <p>
        <input type="radio" name="animal" id="cat" value="cat">
        <label for="cat">cat</label>
    </p>
</fieldset>
</body>
</html>
