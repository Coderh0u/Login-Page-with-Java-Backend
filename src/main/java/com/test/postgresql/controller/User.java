package com.test.postgresql.controller;

import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.test.postgresql.model.Users;
import com.test.postgresql.repository.UsersRepo;

@RestController
@RequestMapping
public class User {
  private final UsersRepo userRepo;

  // inject User repository here
  public User(UsersRepo userRepo) {
    this.userRepo = userRepo;
  }

  @GetMapping("/login")
  public ResponseEntity getUserDetails(@RequestParam String username, @RequestParam String password) {
    Optional<com.test.postgresql.model.Users> user = userRepo.findByUsernameAndPassword(username, password);

    if (user.isPresent()) {
      return ResponseEntity.ok(user.get());
    } else {
      return ResponseEntity.notFound().build();
    }
  }

  @PutMapping("/register")
  public ResponseEntity addUser(@RequestParam String username, @RequestParam String password) {
    // check for repeated username
    Optional<com.test.postgresql.model.Users> repeatedUser = userRepo.findByUsername(username);
    if (repeatedUser.isPresent()) {
      return ResponseEntity.status(409).body("username already in use");
    }

    Users newUser = new Users();
    newUser.setUsername(username);
    newUser.setPassword(password);
    userRepo.save(newUser);

    return ResponseEntity.ok("successfully added user");
  }

  // testing purposes
  @GetMapping("/all")
  public ResponseEntity getAllUsers() {
    return ResponseEntity.ok(this.userRepo.findAll());
  }
}
