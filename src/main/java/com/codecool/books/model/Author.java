package com.codecool.books.model;

import lombok.Data;

import java.sql.Date;

public @Data
class Author {
    // null means not saved
    private Integer id;
    private String firstName;
    private String lastName;
    private Date birthDate;

    public Author(String firstName, String lastName, Date birthDate) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
    }

    @Override
    public String toString() {
        return String.format("Author %d: %s %s %s",
                id, firstName, lastName, birthDate);
    }
}
