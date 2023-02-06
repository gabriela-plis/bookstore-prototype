package org.example;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

import static org.example.RedirectUtils.redirect;

@WebServlet("/customerRegistration")
public class CustomerRegistrationServlet extends HttpServlet {

    private final CustomerFacade customerFacade = FacadeSingletons.getCustomerFacade();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        redirect(request, response, "CustomerRegistrationView.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        customerFacade.register(request.getParameter("firstName"), request.getParameter("lastName"), request.getParameter("phone"), request.getParameter("password"), request.getParameter("email"));
        redirect(request, response, "/bookstore/customerMenu");
    }
}
