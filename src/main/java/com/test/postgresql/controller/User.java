package com.test.postgresql.controller;

import java.util.Optional;

import javax.crypto.SecretKey;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
// import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.test.postgresql.model.EnumRole;
import com.test.postgresql.model.Roles;
import com.test.postgresql.model.Users;
import com.test.postgresql.repository.RolesRepo;
import com.test.postgresql.repository.UsersRepo;
import com.test.postgresql.services.AuthResponse;

import io.jsonwebtoken.Jwts;

@RestController
@RequestMapping
@CrossOrigin(origins = "*")
public class User {
  private final UsersRepo userRepo;
  private final RolesRepo rolesRepo;
  @Autowired
  private SecretKey secretKey;

  // inject User repository here
  public User(UsersRepo userRepo, RolesRepo rolesRepo) {
    this.userRepo = userRepo;
    this.rolesRepo = rolesRepo;
  }

  @GetMapping("/login")
  public ResponseEntity<?> getUserDetails(@RequestParam String username, @RequestParam String password) {
    Optional<com.test.postgresql.model.Users> user = userRepo.findByUsername(username);
    if (user.isPresent()) {
      com.test.postgresql.model.Users foundUser = user.get();
      String storedHash = foundUser.getHashPwd();
      Boolean passwordMatch = BCrypt.checkpw(password, storedHash);
      if (passwordMatch) {
        String userRole = foundUser.getRoleString();
        // introduce JWTs here
        String accessToken = Jwts.builder().content(username, userRole).signWith(secretKey).compact();
        // new library of JJWT may not be compatible with npm version of JWT,
        // nevertheless, included it here for possibility of future use
        // so I'm passing in the username and role as a string
        AuthResponse authResponse = new AuthResponse(accessToken, "login successful", username, userRole);
        return ResponseEntity.ok(authResponse);
      } else {
        AuthResponse authResponse = new AuthResponse("wrong password");
        return ResponseEntity.status(401).body(authResponse);
      }
    } else {
      AuthResponse authResponse = new AuthResponse("user not found");
      return ResponseEntity.status(404).body(authResponse);
    }
  }

  @PutMapping("/register")
  public ResponseEntity<?> addUser(@RequestParam String username, @RequestParam String password,
      @RequestParam String role) {
    // check for repeated username
    Optional<com.test.postgresql.model.Users> repeatedUser = userRepo.findByUsername(username);
    if (repeatedUser.isPresent()) {
      AuthResponse authResponse = new AuthResponse("username already in use");
      return ResponseEntity.status(409).body(authResponse);
    }
    try {
      String salt = BCrypt.gensalt(12);
      String hashPwd = BCrypt.hashpw(password, salt);
      Roles selectedRole = rolesRepo.findByRole(EnumRole.valueOf(role));

      Users newUser = new Users();
      newUser.setUsername(username);
      newUser.setHashPwd(hashPwd);
      newUser.setRole(selectedRole);
      userRepo.save(newUser);
      AuthResponse authResponse = new AuthResponse("successfully added user");
      return ResponseEntity.ok(authResponse);
    } catch (Exception e) {
      AuthResponse authResponse = new AuthResponse("registeration failed");
      return ResponseEntity.status(500).body(authResponse);
    }
  }

  // testing purposes
  @GetMapping("/all")
  public ResponseEntity<?> getAllUsers() {
    return ResponseEntity.ok(this.userRepo.findAll());
  }
}
