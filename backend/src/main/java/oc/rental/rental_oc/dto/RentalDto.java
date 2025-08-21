package oc.rental.rental_oc.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.util.StringJoiner;

public class RentalDto {

    private Integer id;

    private String name;

    private Double surface;

    private BigDecimal price;

    private String picture;

    private String description;

    @JsonProperty(value = "owner_id")
    private Integer ownerId;

    @JsonProperty(value = "created_at")
    private String createdAt;

    @JsonProperty(value = "updated_at")
    private String updatedAt;


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

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", RentalDto.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("name='" + name + "'")
                .add("surface=" + surface)
                .add("price=" + price)
                .add("picture='" + picture + "'")
                .add("description='" + description + "'")
                .add("ownerId=" + ownerId)
                .add("createdAt='" + createdAt + "'")
                .add("updatedAt='" + updatedAt + "'")
                .toString();
    }

    public RentalDto addId(Integer id) {
        setId(id);
        return this;
    }

    public RentalDto addName(String name) {
        setName(name);
        return this;
    }

    public RentalDto addSurface(Double surface) {
        setSurface(surface);
        return this;
    }

    public RentalDto addPrice(BigDecimal price) {
        setPrice(price);
        return this;
    }

    public RentalDto addPicture(String picture) {
        setPicture(picture);
        return this;
    }

    public RentalDto addDescription(String description) {
        setDescription(description);
        return this;
    }

    public RentalDto addOwnerId(Integer ownerId) {
        setOwnerId(ownerId);
        return this;
    }

    public RentalDto addCreatedAt(String createdAt) {
        setCreatedAt(createdAt);
        return this;
    }

    public RentalDto addUpdatedAt(String updatedAt) {
        setUpdatedAt(updatedAt);
        return this;
    }
}
