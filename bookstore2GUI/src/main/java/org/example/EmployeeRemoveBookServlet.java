package org.example;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

import static org.example.RedirectUtils.redirect;

@WebServlet("/removeBook")
public class EmployeeRemoveBookServlet extends HttpServlet {

    private final BooksFacade booksFacade = FacadeSingletones.getBooksFacade();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("booksToRemove", booksFacade.getAvailableBooksToRemove());
        redirect(request, response, "EmployeeRemoveBookView.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        System.out.println(request.getParameter("bookToRemove"));
        booksFacade.remove(request.getParameter("bookToRemove"));


        request.setAttribute("booksToRemove", booksFacade.getAvailableBooksToRemove());
        request.setAttribute("feedback", "The book has been removed");
        redirect(request, response, "EmployeeRemoveBookView.jsp");
    }
}
