package com.huuanh.demo.rsa.viewmodel;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class UserRegistrationRequest {

  @NotEmpty
  @javax.validation.constraints.Email
  @Size(max = 255)
  private String email;

  @NotEmpty
  @Size(min = 6, max = 32)
  private String password;

  @NotEmpty
  @Size(max = 255)
  private String passwordConfirm;

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getPasswordConfirm() {
    return passwordConfirm;
  }

  public void setPasswordConfirm(String passwordConfirm) {
    this.passwordConfirm = passwordConfirm;
  }
}
