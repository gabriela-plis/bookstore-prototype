package org.example;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;
import static org.example.RedirectUtils.redirect;

@WebServlet("/customerLogIn")
public class CustomerLogInServlet extends HttpServlet implements SessionCreator {

    private CustomerFacade customerFacade;
    private int customerID;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        redirect(request,response,"CustomerLogInView.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        customerFacade = new CustomerFacade();
        Customer customer = customerFacade.getCustomer(Integer.parseInt(request.getParameter("ID")), request.getParameter("password"));

        if (customer != null) {
            customerID = customer.ID();

            //creating a new session
            HttpSession session = createSession(request);
            setSessionAttributes(request, session);

//            UserSingletons.setCustomer(customer);
            response.sendRedirect("/bookstore/customerMenu");

        } else {
            request.setAttribute("feedback", "Wrong ID or password");
            redirect(request,response,"CustomerLogInView.jsp");
        }
    }

    @Override
    public void setSessionAttributes(HttpServletRequest request, HttpSession session) {
        session.setAttribute("customerID", customerID);
        session.setAttribute("customerFacade", customerFacade);
        session.setAttribute("booksFacade", new BooksFacade());
    }
}
