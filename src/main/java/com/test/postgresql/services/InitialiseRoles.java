package com.test.postgresql.services;

import org.springframework.stereotype.Service;

import com.test.postgresql.model.EnumRole;
import com.test.postgresql.model.Roles;
import com.test.postgresql.repository.RolesRepo;

import jakarta.annotation.PostConstruct;

@Service
public class InitialiseRoles {

  private final RolesRepo rolesRepo;

  public InitialiseRoles(RolesRepo rolesRepo) {
    this.rolesRepo = rolesRepo;
  }

  @PostConstruct
  public void init() {
    for (EnumRole role : EnumRole.values()) {

      if (rolesRepo.findByRole(role) == null) {
        Roles newRole = new Roles(role);
        rolesRepo.save(newRole);
      }
    }
  }
}
