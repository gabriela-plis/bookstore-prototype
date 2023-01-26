package org.example;

import java.io.File;

public enum Paths {
    BOOKS("F:\\CLionProjects\\java\\bookstore\\bookstoreAPI\\src\\main\\resources\\books.txt"),
    CUSTOMERS("F:\\CLionProjects\\java\\bookstore\\bookstoreAPI\\src\\main\\resources\\customers.txt"),
    EMPLOYEES("F:\\CLionProjects\\java\\bookstore\\bookstoreAPI\\src\\main\\resources\\employees.txt");

    private final String path;

    private Paths (String path) {
        this.path = path;
    }

    public String getPath () {
        return path;
    }
}
