package org.example;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

import static org.example.RedirectUtils.redirect;

@WebServlet("/customerRegistration")
public class CustomerRegistrationServlet extends HttpServlet implements SessionCreator {

    private final CustomerFacade customerFacade = FacadeSingletons.getCustomerFacade();
    private int customerID;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        redirect(request, response, "CustomerRegistrationView.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        customerID = customerFacade.register(request.getParameter("firstName"), request.getParameter("lastName"), request.getParameter("phone"), request.getParameter("password"), request.getParameter("email"));

        if (customerID != -1) {
            HttpSession session = createSession(request);
            setSessionAttributes(request, session);

            redirect(request, response, "/bookstore/customerMenu");

        } else {
            request.setAttribute("feedback", "Something goes wrong, try again");
            redirect(request,response,"CustomerRegistrationView.jsp");
        }

    }

    @Override
    public void setSessionAttributes(HttpServletRequest request, HttpSession session) {
        session.setAttribute("booksFacade", new BooksFacade());
    }
}
