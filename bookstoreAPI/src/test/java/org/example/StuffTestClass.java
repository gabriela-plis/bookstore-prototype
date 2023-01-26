package org.example;

import java.util.Objects;

public class StuffTestClass implements DBObject, Readable {

    private int ID;
    private final String name;
    private double price;
    private int amount;

    public StuffTestClass (int ID, String name, double price, int amount) {
        this.ID = ID;
        this.name = name;
        this.price = price;
        this.amount = amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StuffTestClass that = (StuffTestClass) o;
        return ID == that.ID && Double.compare(that.price, price) == 0 && amount == that.amount && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ID, name, price, amount);
    }

    @Override
    public String read() {
        String connector = ",";
        return ID + connector + name + connector + price + connector + amount;
    }

    @Override
    public int getID() {
        return ID;
    }
}
