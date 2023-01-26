package org.example;

import java.util.ArrayList;
import java.util.Collections;

public class StuffTestDBObjectConverter implements DBObjectConverter<StuffTestClass> {

    @Override
    public String parseToFileLine(StuffTestClass stuff) {
        return stuff.read();
    }

    @Override
    public StuffTestClass parseFromFileLine(String line) {

        ArrayList<String> separatedData = new ArrayList<>();
        Collections.addAll(separatedData, line.split(","));

        return new StuffTestClass(Integer.parseInt(separatedData.get(0)), separatedData.get(1), Double.parseDouble(separatedData.get(2)), Integer.parseInt(separatedData.get(3)));
    }
}
