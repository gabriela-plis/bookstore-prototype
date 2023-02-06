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

        @Override
        public String toString() {
                StringBuilder sb = new StringBuilder();
                return sb.append(ID).append(",").append(title).append(",").append(author).append(",").append(publishYear).append(",").append(canBeBorrow).append(",").append(availableAmount).append(",").append(type).toString();
        }
}
