package org.example;

public class EmployeeFacade {

    private final EmployeeDAO employeeDAO;
    private final BooksDAO booksDAO;

    public EmployeeFacade() {
        this.employeeDAO = new EmployeeDAO();
        this.booksDAO = new BooksDAO();
    }

    public EmployeeFacade (String DB_URL, String DB_USER, String DB_PASS) {
        this.employeeDAO = new EmployeeDAO(DB_URL, DB_USER, DB_PASS);
        this.booksDAO = new BooksDAO(DB_URL, DB_USER, DB_PASS);
    }

    public boolean isLogIn (int ID, String password) {
        return employeeDAO.isLogIn(ID, password);
    }

    public void addBook (String title, String author, int publish_year, boolean can_be_borrow, int available_amount, int typeID) {
        booksDAO.addBook(title, author,  publish_year, can_be_borrow, available_amount, typeID);
    }

    public void removeBook (int bookID) {
        booksDAO.removeBook(bookID);
    }

}
