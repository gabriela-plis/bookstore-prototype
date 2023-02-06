package org.example;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

import static org.example.RedirectUtils.redirect;

@WebServlet("/removeBook")
public class EmployeeRemoveBookServlet extends HttpServlet {

    private final EmployeeFacade employeeFacade = FacadeSingletons.getEmployeeFacade();
    private final BooksFacade booksFacade = FacadeSingletons.getBooksFacade();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("booksToRemove", booksFacade.getAvailableBooksToRemove().stream().map(Book::title));
        redirect(request, response, "EmployeeRemoveBookView.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String selectedBook = request.getParameter("bookToRemove");

        System.out.println(selectedBook);

        employeeFacade.removeBook( booksFacade.getBookID(selectedBook) );


        request.setAttribute("booksToRemove", booksFacade.getAvailableBooksToRemove());
        request.setAttribute("feedback", "The book has been removed");
        redirect(request, response, "EmployeeRemoveBookView.jsp");
    }
}
