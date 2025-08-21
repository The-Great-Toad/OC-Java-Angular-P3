package oc.rental.rental_oc.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import oc.rental.rental_oc.constant.ValidationMessages;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.StringJoiner;

@Entity
@Table(name = "rentals")
@EntityListeners(AuditingEntityListener.class)
public class Rental {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    @Min(value = 0, message = ValidationMessages.POSITIVE_NUMBER_REQUIRED)
    private Double surface;

    @Column(nullable = false, precision = 10, scale = 2)
    @Min(value = 0, message = ValidationMessages.POSITIVE_NUMBER_REQUIRED)
    private BigDecimal price;

    @Column
    private String picture;

    @Column(length = 2000)
    private String description;

    @Column(name = "owner_id", nullable = false)
    private Integer ownerId;

    @CreatedDate
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;


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

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
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

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
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

    public Rental addName(String name) {
        setName(name);
        return this;
    }

    public Rental addSurface(Double surface) {
        setSurface(surface);
        return this;
    }

    public Rental addPrice(BigDecimal price) {
        setPrice(price);
        return this;
    }

    public Rental addPicture(String picture) {
        setPicture(picture);
        return this;
    }

    public Rental addDescription(String description) {
        setDescription(description);
        return this;
    }

    public Rental addOwnerId(Integer ownerId) {
        setOwnerId(ownerId);
        return this;
    }
}
