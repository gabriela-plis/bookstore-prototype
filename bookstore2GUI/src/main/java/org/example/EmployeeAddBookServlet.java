package org.example;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

import static org.example.RedirectUtils.redirect;

@WebServlet("/addBook")
public class EmployeeAddBookServlet extends HttpServlet implements SessionConsumer {

    private EmployeeFacade employeeFacade;
    private BooksFacade booksFacade;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        getSessionAttributes(request);

        request.setAttribute( "bookTypes", booksFacade.getBookTypes().stream().map(BookType::title).toList());
        redirect(request, response, "EmployeeAddBookView.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        employeeFacade.addBook(request.getParameter("title"), request.getParameter("author"), Integer.parseInt(request.getParameter("publishYear")), Boolean.parseBoolean(request.getParameter("canBeBorrow")), Integer.parseInt(request.getParameter("amount")), booksFacade.getBookTypeID(request.getParameter("bookType")));

        request.setAttribute( "bookTypes", booksFacade.getBookTypes().stream().map(BookType::title).toList());
        request.setAttribute("feedback", "The book has been added");
        redirect(request, response, "EmployeeAddBookView.jsp");
    }

    @Override
    public void getSessionAttributes(HttpServletRequest request) {
        HttpSession session = getSession(request);
        employeeFacade = (EmployeeFacade) session.getAttribute("employeeFacade");
        booksFacade = (BooksFacade) session.getAttribute("booksFacade");
    }
}
