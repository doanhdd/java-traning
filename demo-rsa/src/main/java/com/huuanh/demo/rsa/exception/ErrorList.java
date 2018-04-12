package com.huuanh.demo.rsa.exception;

public class ErrorList {
  private String id;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public String toString() {
    return "id: " + id + ": " + message;
  }

  private String message;
}
