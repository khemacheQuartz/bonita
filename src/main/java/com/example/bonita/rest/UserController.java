package com.example.bonita.rest;

import com.example.bonita.domain.User;
import com.example.bonita.services.UserService;
import com.google.gson.Gson;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/user/{id}")
    public ResponseEntity<String> getUser(@PathVariable("id") Long id) {

        User user = userService.getUserById(id);
        Gson gson = new Gson();
        return ResponseEntity.ok(gson.toJson(user));
    }

    @PostMapping("/user")
    public ResponseEntity<Void> createUser(@RequestBody String userJson) {

        Gson gson = new Gson();
        User newUser = gson.fromJson(userJson, User.class);
        userService.saveUser(newUser);
        return ResponseEntity.noContent().build();

    }

    @DeleteMapping("/user/{id}")
    public ResponseEntity<Void> deleteUserById(@PathVariable("id") Long id) {

        User user = userService.getUserById(id);
        userService.removeUser(user);
        return ResponseEntity.noContent().build();
    }


}
