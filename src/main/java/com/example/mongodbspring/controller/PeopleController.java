package com.example.mongodbspring.controller; // Adjust package name

import com.example.mongodbspring.models.People;
import com.example.mongodbspring.repository.PeopleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/people")
public class PeopleController {

    @Autowired
    private PeopleRepository peopleRepository;

    @PostMapping
    public ResponseEntity<People> createItem(@RequestBody People people) {
        try {
            People savedItem = peopleRepository.save(people);
            return new ResponseEntity<>(savedItem, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping
    public List<People> getAllPeople() {
        return peopleRepository.findAll();
    }

    @GetMapping("id/{id}")
    public ResponseEntity<People> getItemById(@PathVariable String id) {
        Optional<People> peopleData = peopleRepository.findById(id);
        return peopleData.map(people -> new ResponseEntity<>(people, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping
    public ResponseEntity<Boolean> deleteByName(@RequestParam String name) {
        try {
            peopleRepository.deleteAll(peopleRepository.findByName(name)); // This method returns void
            return ResponseEntity.noContent().build(); // HTTP 204 No Content
        } catch (Exception e) {
            // Log the exception (e.g., e.printStackTrace(); or use a proper logger)
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("name")
    public List<People> getPersonByName(@RequestParam String name) {
        return peopleRepository.findByName(name);
    }
}
