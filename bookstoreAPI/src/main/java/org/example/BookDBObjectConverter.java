package org.example;

import java.util.ArrayList;
import java.util.Collections;

public class BookDBObjectConverter implements DBObjectConverter<BookDeprec>{

    @Override
    public String parseToFileLine(BookDeprec book) {
        return book.read();
    }

    @Override
    public BookDeprec parseFromFileLine(String line) {

        ArrayList<String> separatedData = new ArrayList<>();
        Collections.addAll(separatedData, line.split(","));

        return new BookDeprec(separatedData.get(0),separatedData.get(1),separatedData.get(2),separatedData.get(3), Integer.parseInt(separatedData.get(4)), Integer.parseInt(separatedData.get(5)), Integer.parseInt(separatedData.get(6)), Boolean.parseBoolean(separatedData.get(7)));

    }
}
