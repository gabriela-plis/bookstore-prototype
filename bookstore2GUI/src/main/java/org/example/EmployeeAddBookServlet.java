package org.example;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

import static org.example.RedirectUtils.redirect;

@WebServlet("/addBook")
public class EmployeeAddBookServlet extends HttpServlet {

    private final BooksFacade booksFacade = FacadeSingletones.getBooksFacade();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        redirect(request, response, "EmployeeAddBookView.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        booksFacade.add(request.getParameter("title"), request.getParameter("author"), request.getParameter("series"), request.getParameter("publishYear"), Boolean.parseBoolean(request.getParameter("canBeBorrow")), Integer.parseInt(request.getParameter("amount")));

        request.setAttribute("feedback", "The book has been added");
        redirect(request, response, "EmployeeAddBookView.jsp");
    }
}
