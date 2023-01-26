package org.example;

import java.util.Objects;

public class Book implements DBObject, Readable {
    private final String title;
    private final String author;
    private final String series;
    private final String publishYear;
    private int availableAmount;
    private int borrowedAmount;
    private int ID;
    private boolean canBeBorrow;


    public Book(String title, String author, String series, String publishYear, int availableAmount, boolean canBeBorrow, int ID) {
        this.title = title;
        this.author = author;
        this.series = series;
        this.publishYear = publishYear;
        this.availableAmount = availableAmount;
        this.borrowedAmount = 0;
        this.ID = ID;
    }

    public Book(String title, String author, String series, String publishYear, int availableAmount, int borrowedAmount, int ID, boolean canBeBorrow) {
        this.title = title;
        this.author = author;
        this.series = series;
        this.publishYear = publishYear;
        this.availableAmount = availableAmount;
        this.borrowedAmount = borrowedAmount;
        this.ID = ID;
        this.canBeBorrow = canBeBorrow;
    }

    public String read() {
        String connector = ",";
        return title + connector + author + connector + series + connector + publishYear + connector + availableAmount + connector + borrowedAmount + connector + ID + connector + canBeBorrow;
    }

    public void incrementAvailableAmount () {
        availableAmount++;
    }

    public void decrementAvailableAmount () {
        availableAmount--;
    }

    public void incrementBorrowedAmount () {
        borrowedAmount++;
    }

    public void decrementBorrowedAmount () {
        borrowedAmount--;
    }

    public int getAvailableAmount() {
        return availableAmount;
    }


    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getSeries() {
        return series;
    }

    public String getPublishYear() {
        return publishYear;
    }

    public int getBorrowedAmount() {
        return borrowedAmount;
    }

    public boolean isCanBeBorrow() {
        return canBeBorrow;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getID() {
        return ID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return availableAmount == book.availableAmount && ID == book.ID && Objects.equals(title, book.title) && Objects.equals(author, book.author) && Objects.equals(series, book.series) && Objects.equals(publishYear, book.publishYear);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, author, series, publishYear, availableAmount, ID);
    }
}
