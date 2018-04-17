package com.huuanh.demo.rsa.viewmodel;

public class UserLoginModel {

  private Integer userId;
  private String email;
  private String publicKey;
  private String token;

  public UserLoginModel(Integer userId, String email, String publicKey, String token) {
    this.userId = userId;
    this.email = email;
    this.publicKey = publicKey;
    this.token = token;
  }

  public Integer getUserId() {
    return userId;
  }

  public void setUserId(Integer userId) {
    this.userId = userId;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPublicKey() {
    return publicKey;
  }

  public void setPublicKey(String publicKey) {
    this.publicKey = publicKey;
  }

  public String getToken() {
    return token;
  }

  public void setToken(String token) {
    this.token = token;
  }
}
