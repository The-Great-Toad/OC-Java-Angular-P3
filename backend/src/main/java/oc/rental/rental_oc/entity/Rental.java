package oc.rental.rental_oc.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.util.Objects;
import java.util.StringJoiner;

@Entity
@Table(name = "rentals")
@EntityListeners(AuditingEntityListener.class)
public class Rental {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String name;

    @Column
    private Double surface;

    @Column
    private Double price;

    @Column
    private String picture;

    @Column(length = 2000)
    private String description;

    @Column(name = "owner_id", nullable = false)
    private Integer ownerId;

    @CreatedDate
    @Column(name = "created_at")
    private LocalDate createdAt;

    @LastModifiedDate
    @Column(name = "updated_at")
    private LocalDate updatedAt;


    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getSurface() {
        return surface;
    }

    public void setSurface(Double surface) {
        this.surface = surface;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Integer ownerId) {
        this.ownerId = ownerId;
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

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Rental rental)) return false;
        return Objects.equals(id, rental.id) && Objects.equals(name, rental.name) && Objects.equals(surface, rental.surface) && Objects.equals(price, rental.price) && Objects.equals(picture, rental.picture) && Objects.equals(description, rental.description) && Objects.equals(ownerId, rental.ownerId) && Objects.equals(createdAt, rental.createdAt) && Objects.equals(updatedAt, rental.updatedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, surface, price, picture, description, ownerId, createdAt, updatedAt);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Rental.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("name='" + name + "'")
                .add("surface=" + surface)
                .add("price=" + price)
                .add("picture='" + picture + "'")
                .add("description='" + description + "'")
                .add("ownerId=" + ownerId)
                .add("createdAt=" + createdAt)
                .add("updatedAt=" + updatedAt)
                .toString();
    }

    public Rental addId(Integer id) {
        this.id = id;
        return this;
    }

    public Rental addName(String name) {
        this.name = name;
        return this;
    }

    public Rental addSurface(Double surface) {
        this.surface = surface;
        return this;
    }

    public Rental addPrice(Double price) {
        this.price = price;
        return this;
    }

    public Rental addPicture(String picture) {
        this.picture = picture;
        return this;
    }

    public Rental addDescription(String description) {
        this.description = description;
        return this;
    }

    public Rental addOwnerId(Integer ownerId) {
        this.ownerId = ownerId;
        return this;
    }

    public Rental addCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public Rental addUpdatedAt(LocalDate updatedAt) {
        this.updatedAt = updatedAt;
        return this;
    }
}
