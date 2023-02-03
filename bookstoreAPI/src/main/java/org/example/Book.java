package org.example;

public record Book(
        int ID,
        String title,
        String author,
        int publishYear,
        boolean canBeBorrow,
        int availableAmount,
        String type
        ) {

}
