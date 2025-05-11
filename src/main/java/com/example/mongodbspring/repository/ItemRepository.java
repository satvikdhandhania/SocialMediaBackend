package com.example.mongodbspring.repository;

import com.example.mongodbspring.models.Item;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends MongoRepository<Item, String> { // Item is the entity, String is the ID type
    // You can add custom query methods here, e.g.:
    // List<Item> findByName(String name);
}
