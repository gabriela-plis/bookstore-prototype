package org.example;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

import static org.example.RedirectUtils.redirect;

@WebServlet("/employeeLogIn")
public class EmployeeLogInServlet extends HttpServlet {

    private final EmployeeFacade employeeFacade = FacadeSingletons.getEmployeeFacade();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        redirect(request, response, "EmployeeLogInView.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (employeeFacade.isLogIn(Integer.parseInt(request.getParameter("ID")), request.getParameter("password"))) {
            response.sendRedirect("/bookstore/employeeMenu");
        } else {
            request.setAttribute("feedback", "Wrong ID or password");
            redirect(request, response, "EmployeeLogInView.jsp");
        }
    }
}
