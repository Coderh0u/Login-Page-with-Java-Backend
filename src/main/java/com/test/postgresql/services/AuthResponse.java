package com.test.postgresql.services;

public class AuthResponse {
  private String accessToken;
  private String message;
  private String username;
  private String userRole;

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getUserRole() {
    return userRole;
  }

  public void setUserRole(String userRole) {
    this.userRole = userRole;
  }

  public String getAccessToken() {
    return accessToken;
  }

  public void setAccessToken(String accessToken) {
    this.accessToken = accessToken;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public AuthResponse(String accessToken, String message, String username, String userRole) {
    this.accessToken = accessToken;
    this.message = message;
    this.username = username;
    this.userRole = userRole;
  }

  public AuthResponse(String message) {
    this.message = message;
  }
}
