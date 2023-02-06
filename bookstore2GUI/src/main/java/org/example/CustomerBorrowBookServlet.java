package org.example;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static org.example.RedirectUtils.redirect;

@WebServlet("/borrowBook")
public class CustomerBorrowBookServlet extends HttpServlet implements SessionConsumer {

    private CustomerFacade customerFacade;
    private BooksFacade booksFacade;
    private int customerID;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        getSessionAttributes(request);

        request.setAttribute("books", booksFacade.getAvailableBooksToBorrow(customerID).stream().map(Book::title).toList());
        redirect(request, response, "CustomerBorrowBookView.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String selectedBook = request.getParameter("book");

        if (selectedBook == null) {
            throw new IllegalArgumentException();
        }

        customerFacade.borrowBook(customerID, booksFacade.getBookID(selectedBook));

        request.setAttribute("books", booksFacade.getAvailableBooksToBorrow(customerID).stream().map(Book::title).toList());
        request.setAttribute("feedback", "You borrowed book successfully!");

        redirect(request,response,"CustomerBorrowBookView.jsp");
    }

    @Override
    public void getSessionAttributes(HttpServletRequest request) {
        HttpSession session = getSession(request);

        this.customerFacade = (CustomerFacade) session.getAttribute("customerFacade");
        this.booksFacade = (BooksFacade) session.getAttribute("booksFacade");
        this.customerID = (int) session.getAttribute("customerID");
    }
}
