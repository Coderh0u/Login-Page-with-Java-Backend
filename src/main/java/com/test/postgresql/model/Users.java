package com.test.postgresql.model;

import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

// how to tell spring that this is an entity from db
@Entity
public class Users {
  @Id // mark as primary key here
  @GeneratedValue(strategy = GenerationType.AUTO)
  private UUID uuid; // make primary key as uuid

  // other table column names
  private String username;

  private String password;

  public Users() {
  }

  public Users(UUID uuid, String username, String password) {
    this.uuid = uuid;
    this.username = username;
    this.password = password;
  }

  public UUID getUuid() {
    return uuid;
  }

  public String getUsername() {
    return username;
  }

  public String getPassword() {
    return password;
  }

  public void setUuid(UUID uuid) {
    this.uuid = uuid;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public void setPassword(String password) {
    this.password = password;
  }
}
