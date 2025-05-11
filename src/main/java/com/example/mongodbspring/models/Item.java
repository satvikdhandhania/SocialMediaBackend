package com.example.mongodbspring.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "items") // Maps to the "items" collection in MongoDB
public class Item {

    @Id
    private String id; // MongoDB uses String for IDs by default
    private String name;
    private String description;
    private LocalDateTime createdAt = LocalDateTime.now();

    // Constructors.
    public Item() {
    }

    public Item(String name, String description) {
        this.name = name;
        this.description = description;
    }

    // Getters and Setters (or use Lombok @Data)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
