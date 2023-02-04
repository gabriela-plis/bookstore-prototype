package org.example;

public record Customer(
        int ID,
        String firstName,
        String lastName,
        String phone,
        String email
    ) {
}
