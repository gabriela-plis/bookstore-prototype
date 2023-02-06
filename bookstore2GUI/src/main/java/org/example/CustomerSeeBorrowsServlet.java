package org.example;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

import static org.example.RedirectUtils.redirect;

@WebServlet("/seeBorrowsBooks")
public class CustomerSeeBorrowsServlet extends HttpServlet {

    private final CustomerFacade customerFacade = FacadeSingletons.getCustomerFacade();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("borrowedBooks", customerFacade.getBorrowedBooks(UserSingletons.getCustomer().ID()).stream().map(Book::title).toList());
        redirect(request, response, "CustomerSeeBorrowsView.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
