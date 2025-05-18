package com.example.mongodbspring.controller; // Adjust package name

import com.example.mongodbspring.models.User;
import com.example.mongodbspring.repository.UserRepository;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequestMapping("/v1")
@CrossOrigin(origins = "http://localhost:3000", allowedHeaders = "*", allowCredentials = "true")
public class UserController {

    @Autowired
    private UserRepository userRepository;
    private HashMap<String, Cookie> activeSessions = new HashMap<>();

    @CrossOrigin(origins = "http://localhost:3000", allowedHeaders = "*")
    @PostMapping("/auth/register")
    public ResponseEntity<User> register(@RequestBody User user) {
        System.out.println("Registering User");
        try {// check for valid email
            if (user.getEmail().isEmpty()) {
                throw new BadRequestException("Empty Email!");
            }
            if (user.getName().isEmpty()) {
                throw new BadRequestException("Empty Name!");
            }
            if (user.getPassword().isEmpty()) {
                throw new BadRequestException("Empty Password!");
            }
            User foundUser = userRepository.findByEmail(user.getEmail());
            if (foundUser != null) {
                throw new BadRequestException("User Already Exists!");
            }
            User savedUser = userRepository.save(user);
            return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
        } catch (BadRequestException e) {
            System.out.println(e.toString());
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            System.out.println(e.toString());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PostMapping("/auth/login")
    public ResponseEntity<String> login(@RequestBody User user, HttpServletResponse response) {
        try {// check for valid email
            if (user.getEmail().isEmpty()) {
                throw new BadRequestException("Empty Email!");
            }
            if (user.getPassword().isEmpty()) {
                throw new BadRequestException("Empty Password!");
            }
            System.out.println("USer" + user);
            User potentialUser = userRepository.findByEmail(user.getEmail());

            System.out.println("PTUSer" + potentialUser);
            if (potentialUser == null) {
                throw new BadRequestException("User not found!");
            }
            if (!potentialUser.getPassword().equals(user.getPassword())) {
                throw new BadRequestException("Incorrect Password!");
            }

            Cookie sessionCookie = new Cookie("SESSION_ID", user.getEmail());
            sessionCookie.setPath("/"); // Cookie is accessible to all paths
            sessionCookie.setMaxAge(24 * 60 * 60); // Cookie expires in 1 day (in seconds)
            // sessionCookie.setHttpOnly(true); // Protects against XSS attacks (recommended)
            // sessionCookie.setSecure(true); // Cookie only sent over HTTPS (recommended for production)

            response.addCookie(sessionCookie);
            activeSessions.put(user.getEmail(), sessionCookie); // Store the session ID as active

            return ResponseEntity.ok("Login Successful! ");
        } catch (BadRequestException e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/profile")
    public ResponseEntity<User> getProfile(@CookieValue(name = "SESSION_ID", required = false) String sessionId) {
        try {
            if (activeSessions.containsKey(sessionId)) {
                User user = userRepository.findByEmail(sessionId);
                //user.setPassword("");
                return new ResponseEntity<>(user, HttpStatus.OK);
            } else {
                throw new BadRequestException("No such session exists!");
            }
        } catch (BadRequestException e) {
            System.out.println(e.toString());
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
/*
    @GetMapping
    public List<Item> getAllItems() {
        return itemRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Item> getItemById(@PathVariable String id) {
        Optional<Item> itemData = itemRepository.findById(id);
        return itemData.map(item -> new ResponseEntity<>(item, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

 */
}
