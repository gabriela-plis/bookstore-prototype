package org.example;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

import static org.example.RedirectUtils.redirect;

@WebServlet("/returnBook")
public class CustomerReturnBookServlet extends HttpServlet {

    private final CustomerFacade customerFacade = FacadeSingletones.getCustomerFacade();
    private final BooksFacade booksFacade = FacadeSingletones.getBooksFacade();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("borrowedBooks", customerFacade.getBorrowedBooksTitles());
        redirect(request, response, "CustomerReturnBookView.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String selectedBook = request.getParameter("book");

        if (selectedBook == null) {
            throw new IllegalArgumentException("Null object");
        }

        customerFacade.returnBook(selectedBook);
        booksFacade.updateBookAfterReturn(selectedBook);

        request.setAttribute("feedback", "You returned book successfully!");
        request.setAttribute("borrowedBooks", customerFacade.getBorrowedBooksTitles());
        redirect(request, response, "CustomerReturnBookView.jsp");

    }
}
