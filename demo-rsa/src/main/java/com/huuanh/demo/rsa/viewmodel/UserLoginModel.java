package com.huuanh.demo.rsa.viewmodel;

public class UserLoginModel {

  private Integer userId;
  private String email;

  public UserLoginModel(Integer userId, String email) {
    this.userId = userId;
    this.email = email;
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
}
