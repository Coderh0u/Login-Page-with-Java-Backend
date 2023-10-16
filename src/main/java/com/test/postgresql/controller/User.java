package com.test.postgresql.controller;

import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
  // parametarized queries to prevent sql injections
    public ResponseEntity getUserDetails(@RequestParam String username, @RequestParam String password) {
    Optional<com.test.postgresql.model.Users> user = userRepo.findByUsernameAndPassword(username, password);

    if (user.isPresent()) {
      return ResponseEntity.ok(user.get());
    } else {
      return ResponseEntity.notFound().build();
    }
  }

  // testing purposes
  @GetMapping("/all")
  public ResponseEntity getAllUsers() {
    return ResponseEntity.ok(this.userRepo.findAll());
  }

}
