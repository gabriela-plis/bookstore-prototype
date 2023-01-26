package org.example;

import java.util.ArrayList;
import java.util.List;

public class BorrowedBooks {
    private final ArrayList<String> collectionOfTitles;

    public BorrowedBooks (ArrayList<String> titlesOfBooks) {
        this.collectionOfTitles = titlesOfBooks;
    }

    public BorrowedBooks () {
        this.collectionOfTitles = new ArrayList<>();
    }

    public void add (String title) {
        collectionOfTitles.add(title);
    }

    public void addMultiple (ArrayList<String> titles) {
        collectionOfTitles.addAll(titles);
    }

    public void remove (String title) {
        collectionOfTitles.remove(title);
    }

    public void print() {
        for (String title : collectionOfTitles) {
            System.out.println(title);
        }
    }

    public boolean isContains(String title) {
        return collectionOfTitles.contains(title);
    }

    public boolean isEmpty() {
        return collectionOfTitles.isEmpty();
    }

    public List<String> getAllTitles() {
        return new ArrayList<>(collectionOfTitles);
    }
}
