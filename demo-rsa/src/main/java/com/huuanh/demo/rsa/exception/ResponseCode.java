package com.huuanh.demo.rsa.exception;

public enum ResponseCode {
  SUCCESS("S0000", ""),
  UNKNOWN("S0001", "Server encountered a problem"),
  MAINTAIN("S0002", "The server is maintenance"),
  CODE_HAS_VALIDATION_ERROR("A0001", "A validation error occurred"),
  GEN_KEY_RSA_ERROR("A0002", "Gen public and private keys error"),
  ACCOUNT_NOT_FOUND("A0003", "Account not found"),
  LOGIC_ERROR("A0004", "Logic error occurred"),
  INVALID_AUTHENTICATION("A0005", "Illegal Authentication Occurred"),
  DATA_NOT_FOUND("A0007", "No data found"),
  SAVE_DATA_ERROR("A0008", "Error saving data"),

  ERROR_400("400 Bad Request", "Invalid parameter was specified"),
  ERROR_401("401 Unauthorized", "Access to the page failed, please log in again"),
  ERROR_403("403 Forbidden", "Access to the page failed, please log in again"),
  ERROR_404("404 Not Found", "The page was deleted or does not exist"),
  ERROR_500("500 Internal Server Error", "An error occurred, I can not use it right now"),;

  private final String value;
  private final String reason;

  ResponseCode(String value, String reason) {
    this.value = value;
    this.reason = reason;
  }

  public String value() {
    return this.value;
  }

  public String getReason() {
    return this.reason;
  }
}
