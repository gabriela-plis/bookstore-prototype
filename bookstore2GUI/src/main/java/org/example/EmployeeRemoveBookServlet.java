package org.example;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

import static org.example.RedirectUtils.redirect;

@WebServlet("/removeBook")
public class EmployeeRemoveBookServlet extends HttpServlet implements SessionConsumer {

    private EmployeeFacade employeeFacade;
    private BooksFacade booksFacade;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        getSessionAttributes(request);

        request.setAttribute("booksToRemove", booksFacade.getAvailableBooksToRemove().stream().map(Book::title).toList());
        redirect(request, response, "EmployeeRemoveBookView.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String selectedBook = request.getParameter("bookToRemove");

        System.out.println(selectedBook);

        employeeFacade.removeBook( booksFacade.getBookID(selectedBook) );


        request.setAttribute("booksToRemove", booksFacade.getAvailableBooksToRemove().stream().map(Book::title).toList());
        request.setAttribute("feedback", "The book has been removed");
        redirect(request, response, "EmployeeRemoveBookView.jsp");
    }

    @Override
    public void getSessionAttributes(HttpServletRequest request) {
        HttpSession session = getSession(request);
        employeeFacade = (EmployeeFacade) session.getAttribute("employeeFacade");
        booksFacade = (BooksFacade) session.getAttribute("booksFacade");
    }
}
