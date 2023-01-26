package org.example;

import java.util.ArrayList;
import java.util.Collections;

public class BookDBObjectConverter implements DBObjectConverter<Book>{

    @Override
    public String parseToFileLine(Book book) {
        return book.read();
    }

    @Override
    public Book parseFromFileLine(String line) {

        ArrayList<String> separatedData = new ArrayList<>();
        Collections.addAll(separatedData, line.split(","));

        return new Book(separatedData.get(0),separatedData.get(1),separatedData.get(2),separatedData.get(3), Integer.parseInt(separatedData.get(4)), Integer.parseInt(separatedData.get(5)), Integer.parseInt(separatedData.get(6)), Boolean.parseBoolean(separatedData.get(7)));

    }
}
