package com.test.postgresql.services;

public class AuthResponse {
  private String accessToken;
  private String message;

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

  public AuthResponse(String accessToken, String message) {
    this.accessToken = accessToken;
    this.message = message;
  }
}
