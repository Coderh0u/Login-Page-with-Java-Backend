package com.test.postgresql.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.test.postgresql.model.AccessTokens;


public interface TokensRepo extends JpaRepository<AccessTokens, Long>{
  Optional<AccessTokens> findByToken(String token);
}
