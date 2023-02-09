package org.example;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;

import static org.example.RedirectUtils.redirect;

@WebServlet("/returnBook")
public class CustomerReturnBookServlet extends HttpServlet implements SessionConsumer {

    private final CustomerFacade customerFacade = FacadeSingletons.getCustomerFacade();
    private final BooksFacade booksFacade = FacadeSingletons.getBooksFacade();
    private int customerID;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        getSessionAttributes(request);

        request.setAttribute("borrowedBooks", booksFacade.getAvailableBooksToReturn(customerID).stream().map(Book::title).toList() );
        redirect(request, response, "CustomerReturnBookView.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String selectedBook = request.getParameter("book");

        if (selectedBook == null) {
            throw new IllegalArgumentException("Null object");
        }


        customerFacade.returnBook(customerID, booksFacade.getBookID(selectedBook));

        request.setAttribute("feedback", "You returned book successfully!");
        request.setAttribute("borrowedBooks", booksFacade.getAvailableBooksToReturn(customerID).stream().map(Book::title).toList());

        redirect(request,response,"CustomerReturnBookView.jsp");
    }

    @Override
    public void getSessionAttributes(HttpServletRequest request) {
        HttpSession session = getSession(request);
        this.customerID = (int) session.getAttribute("customerID");
    }
}
