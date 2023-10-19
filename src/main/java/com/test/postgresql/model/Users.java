package com.test.postgresql.model;

import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

// how to tell spring that this is an entity from db
@Entity
public class Users {
  @Id // mark as primary key here
  @GeneratedValue(strategy = GenerationType.AUTO)
  private UUID uuid; // make primary key as uuid

  // other table column names
  private String username;
  private String hashPwd;
  @ManyToOne
  @JoinColumn(name = "role")
  private Roles role;
  // private String roleString;

  public Users() {
  }

  public Users(UUID uuid, String username, String hashPwd) {
    this.uuid = uuid;
    this.username = username;
    this.hashPwd = hashPwd;
  }

  public String getHashPwd() {
    return hashPwd;
  }

  public void setHashPwd(String hashPwd) {
    this.hashPwd = hashPwd;
  }

  public UUID getUuid() {
    return uuid;
  }

  public String getUsername() {
    return username;
  }

  public void setUuid(UUID uuid) {
    this.uuid = uuid;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public Roles getRole() {
    return role;
  }

  public void setRole(Roles role) {
    this.role = role;
  }

  public String getRoleString() {
    return role.getRoleName();
  }

}
