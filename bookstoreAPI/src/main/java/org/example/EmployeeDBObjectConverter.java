package org.example;

import java.util.ArrayList;
import java.util.Collections;

public class EmployeeDBObjectConverter implements DBObjectConverter<Employee> {

    @Override
    public String parseToFileLine(Employee employee) {
        return employee.read();
    }

    @Override
    public Employee parseFromFileLine(String line) {

        ArrayList<String> separatedData = new ArrayList<>();
        Collections.addAll(separatedData, line.split(","));

        return new Employee(Integer.parseInt(separatedData.get(0)),separatedData.get(1),separatedData.get(2),separatedData.get(3), separatedData.get(4));

    }
}
