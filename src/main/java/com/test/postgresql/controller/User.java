package com.test.postgresql.controller;

import java.util.Optional;
import java.sql.Timestamp;
import java.util.List;
import java.util.HashMap;

import javax.crypto.SecretKey;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.test.postgresql.model.EnumRole;
import com.test.postgresql.model.Roles;
import com.test.postgresql.model.Users;
import com.test.postgresql.model.AccessTokens;
import com.test.postgresql.repository.RolesRepo;
import com.test.postgresql.repository.UsersRepo;
import com.test.postgresql.repository.TokensRepo;
import com.test.postgresql.services.AuthResponse;

import io.jsonwebtoken.Jwts;

@RestController
@RequestMapping
@CrossOrigin(origins = "*")
public class User {
  private final UsersRepo userRepo;
  private final RolesRepo rolesRepo;
  private final TokensRepo tokensRepo;

  @Autowired
  private SecretKey secretKey;

  public User(UsersRepo userRepo, RolesRepo rolesRepo, TokensRepo tokensRepo) {
    this.userRepo = userRepo;
    this.rolesRepo = rolesRepo;
    this.tokensRepo = tokensRepo;
  }

  @GetMapping("/login")
  public ResponseEntity<?> getUserDetails(@RequestParam String username, @RequestParam String password,
      @RequestParam String token) {
    Optional<com.test.postgresql.model.Users> user = userRepo.findByUsername(username);
    if (user.isPresent()) {
      com.test.postgresql.model.Users foundUser = user.get();
      String storedHash = foundUser.getHashPwd();
      Boolean passwordMatch = BCrypt.checkpw(password, storedHash);
      if (passwordMatch) {
        String userRole = foundUser.getRoleString();

        // check if logged out
        Optional<AccessTokens> blackListedToken = tokensRepo.findByToken(token);
        if (blackListedToken.isPresent()) {
          AuthResponse authResponse = new AuthResponse("Forbidden user");
          return ResponseEntity.status(401).body(authResponse);
        }

        // introduce new JWT here
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        String timeString = timestamp.toString();
        HashMap<String, Object> jsonData = new HashMap<>();
        jsonData.put("username", username);
        jsonData.put("userRole", userRole);
        jsonData.put("createdAt", timeString);
        String accessToken = Jwts.builder().claims(jsonData).signWith(secretKey).compact();

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

  // logout
  @PutMapping("/logout")
  public ResponseEntity<?> logout(@RequestParam String token) {
    AccessTokens newBlackListToken = new AccessTokens();
    newBlackListToken.setToken(token);
    tokensRepo.save(newBlackListToken);

    AuthResponse authResponse = new AuthResponse("log out successful");
    return ResponseEntity.ok(authResponse);
  }

  // testing purposes
  @GetMapping("/allUsers")
  public ResponseEntity<?> getAllUsers() {
    Roles selectedRole = rolesRepo.findByRole(EnumRole.valueOf("USER"));
    List<Users> users = userRepo.findByRole(selectedRole);
    return ResponseEntity.ok(users);
  }
}
