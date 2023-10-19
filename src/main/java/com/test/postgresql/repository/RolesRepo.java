package com.test.postgresql.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.test.postgresql.model.EnumRole;
import com.test.postgresql.model.Roles;

public interface RolesRepo extends JpaRepository<Roles, EnumRole> {
  Roles findByRole(EnumRole role);
}
