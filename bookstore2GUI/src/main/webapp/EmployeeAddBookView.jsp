<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: gab56
  Date: 26.01.2023
  Time: 16:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>bookstore</title>
</head>
<body>
        <form action="addBook" method="post">
        <fieldset>
            <legend>Fill book's data</legend>
            <p>
                <%--@declare id="title"--%><label for="title">Title:</label>
                <input type="text" name="title" id="title" required>
            </p>
            <p>
                <%--@declare id="author"--%><label for="author">Author:</label>
                <input type="text" name="author" id="author" required>
            </p>
            <p>
                <%--@declare id="series"--%><label for="series">Series:</label>
                <input type="text" name="series" id="series" required>
            </p>
            <p>
                <%--@declare id="publishyear"--%><label for="publishYear">Publish year:</label>
                <input type="number" name="publishYear" id="publishYear" min="1900" max="2023" required>
            </p>
            <p>
<%--                &lt;%&ndash;@declare id="canbeborrow"&ndash;%&gt;<label for="canBeBorrow">Can be borrow:</label>--%>
                Can be borrow:
                <input type="radio" name="canBeBorrow" id="canBeBorrow" value="true">
                <label for="canBeBorrow">True</label>
                <input type="radio" name="canBeBorrow" id="canBeBorrow" value="false">
                <label for="canBeBorrow">False</label>

            </p>
            <p>
                <%--@declare id="amount"--%><label for="amount">Amount:</label>
                <input type="number" name="amount" id="amount" required>
            </p>
        </fieldset>
        <br>
        <button type="submit">Submit</button>
        <button type="reset">Reset</button>
        <p id="feedback">
            <c:out value="${feedback}"> </c:out>
        </p>
    </form>

        <button type="button"><a href="employeeMenu">Back</a></button>

</body>
</html>
