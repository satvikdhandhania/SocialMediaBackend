package com.example.mongodbspring.models;

import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


public class User {
    // MongoDB uses String for IDs by default
    @Id
    private String Id;
    private String name;

    private String email;

    private String password;
    private List<String> friends;
    private LocalDateTime createdAt = LocalDateTime.now();

    public User(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.friends = new ArrayList<>();
    }

    /*
        public User(String email, String password) {
            this.email = email;
            this.password = password;
        }
    */
    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public List<String> getFriends() {
        return friends;
    }

    public void setFriends(List<String> friends) {
        this.friends = friends;
    }
}
