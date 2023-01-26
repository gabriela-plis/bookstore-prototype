package org.example;

import java.util.Map;
import java.util.function.Supplier;

public class GeneralFacade<T extends DBObject> {

    //URL do baz jako zmienne
    private static final String BOOK_DB_URL = "F:\\CLionProjects\\java\\bookstore\\bookstoreAPI\\src\\main\\java\\txt-files\\books.txt";
    private static final String CUSTOMER_DB_URL = "F:\\CLionProjects\\java\\bookstore\\bookstoreAPI\\src\\main\\java\\txt-files\\customers.txt";
    private static final String EMPLOYEE_DB_URL = "F:\\CLionProjects\\java\\bookstore\\bookstoreAPI\\src\\main\\java\\txt-files\\customers.txt";

    //mapa klucz jako klasa, która rozszerza DBObject, a value to metoda Supplier zwracająca obiekt- database (Czemu supplier? lazyyy; równie dobrze na samym początku mogłyby być tworzone czyli database)
    // czyli jeśli jest customer to value to metoda zwracająca database o URL bazy customerów
    private static final Map<Class<? extends DBObject>, Supplier<Database<? extends DBObject>>> databases = Map.of(
            Book.class, () -> new Database<>(BOOK_DB_URL, new BookDBObjectConverter()),
            Customer.class, () -> new Database<>(CUSTOMER_DB_URL, new CustomerDBObjectConverter()),
            Employee.class, () -> new Database<>(EMPLOYEE_DB_URL, new EmployeeDBObjectConverter())
    );

    //wersja non-lazy
//    private static final Map<Class<? extends DBObject>, Database<? extends DBObject>> databases = Map.of(
//            Book.class, new Database<>(BOOK_DB_URL, new BookDBObjectConverter()),
//            Customer.class, new Database<>(CUSTOMER_DB_URL, new CustomerDBObjectConverter()),
//            Employee.class, new Database<>(EMPLOYEE_DB_URL, new EmployeeDBObjectConverter())
//    );

    private final Database<T> database;
    private T person;
    private Class type;

    // GeneralFacade<Customer> facade = new...
    //
    public GeneralFacade(Class<T> type) {
        //rzutowanie - wyjęcie z mapy konkretnej metody - get użycie
        this.database = (Database<T>) databases.get(type).get();
        database.load();
        this.person = null;
        this.type = type;
    }

    public boolean isLogIn(int ID, String password) {

        if (database.isContains(ID)) {
            person = database.get(ID);
            return database.isContains(ID);
        }

        return database.isContains(ID);

    }

    public void register (String firstName, String lastName, String phone, String password, String email) {

    }

    public DBObject getPerson() {
        return person;
    }

    @Override
    public String toString() {
        return "GeneralFacade{}";
    }
}

