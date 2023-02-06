package org.example;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;

import static org.example.RedirectUtils.redirect;

@WebServlet("/seeBorrowsBooks")
public class CustomerSeeBorrowsServlet extends HttpServlet implements SessionConsumer {

    private CustomerFacade customerFacade;
    private int customerID;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        getSessionAttributes(request);

        request.setAttribute("borrowedBooks", customerFacade.getBorrowedBooks(customerID).stream().map(Book::title).toList());
        redirect(request, response, "CustomerSeeBorrowsView.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    public void getSessionAttributes(HttpServletRequest request) {
        HttpSession session = getSession(request);
        this.customerFacade = (CustomerFacade) session.getAttribute("customerFacade");
        this.customerID = (int) session.getAttribute("customerID");
    }
}
