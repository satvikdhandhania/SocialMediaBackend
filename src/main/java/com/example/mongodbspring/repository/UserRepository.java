package com.example.mongodbspring.repository;

import com.example.mongodbspring.models.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<User, String> { // Item is the entity, String is the ID type
    // You can add custom query methods here, e.g.:
    @Query("{ 'email': { $regex: '^?0', $options: 'i' } }")
    User findByEmail(String email);
}
