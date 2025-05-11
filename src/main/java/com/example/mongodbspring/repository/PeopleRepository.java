package com.example.mongodbspring.repository;

import com.example.mongodbspring.models.People;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PeopleRepository extends MongoRepository<People, String> { // Item is the entity, String is the ID type
    // You can add custom query methods here, e.g.:
    @Query("{ 'name': { $regex: '^?0', $options: 'i' } }")
    List<People> findByName(String name);
}
