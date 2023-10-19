package com.test.postgresql.model;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Roles {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Enumerated(EnumType.STRING)
  private EnumRole role;

  public String getRoleName() {
    return role.name();
  }

  public Roles() {
  }

  public Roles(EnumRole role) {
    this.role = role;
  }

}
