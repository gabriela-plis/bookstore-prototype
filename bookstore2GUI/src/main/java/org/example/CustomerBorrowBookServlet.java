package org.example;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.example.RedirectUtils.redirect;

@WebServlet("/borrowBook")
public class CustomerBorrowBookServlet extends HttpServlet {

    private final CustomerFacade customerFacade = FacadeSingletones.getCustomerFacade();
    private final BooksFacade booksFacade = FacadeSingletones.getBooksFacade();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("books", booksFacade.getAvailableBooksToBorrow(customerFacade));
        redirect(request, response, "CustomerBorrowBookView.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String selectedBook = request.getParameter("book");
        System.out.println(selectedBook);

        if (selectedBook == null) {
            throw new IllegalArgumentException("Null object");
        }

        booksFacade.updateBookAfterBorrow(selectedBook);
        customerFacade.borrowBook(selectedBook);

        request.setAttribute("books", booksFacade.getAvailableBooksToBorrow(customerFacade));
        request.setAttribute("feedback", "You borrowed book successfully!");
        redirect(request,response,"CustomerBorrowBookView.jsp");
    }
}