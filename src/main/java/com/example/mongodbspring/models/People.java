package com.example.mongodbspring.models;

import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;

public class People {

    @Id
    private String id; // MongoDB uses String for IDs by default
    private String name;
    private String location;
    private LocalDateTime createdAt = LocalDateTime.now();

    public People(String name, String location) {
        this.name = name;
        this.location = location;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

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

}
