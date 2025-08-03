package oc.rental.rental_oc.dto;

import java.time.LocalDate;
import java.util.StringJoiner;

public class UserDto {

    private Integer id;
    private String name;
    private String email;
    private LocalDate createdAt;
    private LocalDate updatedAt;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDate getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDate updatedAt) {
        this.updatedAt = updatedAt;
    }

    public UserDto addId(Integer id) {
        this.id = id;
        return this;
    }

    public UserDto addName(String name) {
        this.name = name;
        return this;
    }

    public UserDto addEmail(String email) {
        this.email = email;
        return this;
    }

    public UserDto addCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public UserDto addUpdatedAt(LocalDate updatedAt) {
        this.updatedAt = updatedAt;
        return this;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", UserDto.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("name='" + name + "'")
                .add("email='" + email + "'")
                .add("createdAt='" + createdAt + "'")
                .add("updatedAt='" + updatedAt + "'")
                .toString();
    }
}