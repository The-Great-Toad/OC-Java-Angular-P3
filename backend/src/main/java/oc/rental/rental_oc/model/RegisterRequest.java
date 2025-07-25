package oc.rental.rental_oc.model;

public record RegisterRequest(
        String name,
        String email,
        String password
) {}
