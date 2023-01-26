package org.example;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

import static org.example.FacadeSingletones.getCustomerFacade;
import static org.example.RedirectUtils.redirect;

@WebServlet("/customerLogIn")
public class CustomerLogInServlet extends HttpServlet {

    private final CustomerFacade customerFacade = FacadeSingletones.getCustomerFacade();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect("CustomerLogInView.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (customerFacade.isLogIn(Integer.parseInt(request.getParameter("ID")), request.getParameter("password"))) {
            response.sendRedirect("/bookstore/customerMenu");

        } else {
            request.setAttribute("feedback", "Wrong ID or password");
            redirect(request, response, "CustomerLogInView.jsp");
        }
    }

}