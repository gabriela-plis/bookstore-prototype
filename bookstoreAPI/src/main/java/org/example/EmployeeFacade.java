package org.example;

public class EmployeeFacade {

    private static final String EMPLOYEE_DB_URL = Paths.EMPLOYEES.getPath();
    Database<Employee> database;

    Employee person;

    public EmployeeFacade() {
        this.database = new Database<>(EMPLOYEE_DB_URL, new EmployeeDBObjectConverter());
        this.database.load();
        this.person = null;
    }

    public boolean isLogIn(int ID, String password) {

        if (database.isContains(ID) && database.get(ID).getSensitiveData().getPassword().equals(password)) {
            person = database.get(ID);
            return true;
        }

        return false;
    }

}
