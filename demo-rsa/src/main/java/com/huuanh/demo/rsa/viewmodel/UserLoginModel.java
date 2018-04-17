package com.huuanh.demo.rsa.viewmodel;

public class UserLoginModel {

  private Integer userId;
  private String email;
  private String privateKey;
  private String token;

  public UserLoginModel(Integer userId, String email, String privateKey, String token) {
    this.userId = userId;
    this.email = email;
    this.privateKey = privateKey;
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

  public String getPrivateKey() {
    return privateKey;
  }

  public void setPrivateKey(String privateKey) {
    this.privateKey = privateKey;
  }

  public String getToken() {
    return token;
  }

  public void setToken(String token) {
    this.token = token;
  }
}
