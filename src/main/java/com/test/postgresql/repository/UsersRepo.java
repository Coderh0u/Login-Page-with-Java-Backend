package com.test.postgresql.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import com.test.postgresql.model.Users;


public interface UsersRepo extends JpaRepository<Users, UUID>{
  Optional<Users> findByUsernameAndHashPwd(String username, String hashPwd);
  Optional<Users> findByUsername(String username);
}
