package com.huuanh.demo.rsa.exception;

import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

public class ApiException extends RuntimeException {

  private String errorCode;

  private List<ErrorList> errorList = new ArrayList<>();

  public ApiException(String errorCode) {
    this.errorCode = errorCode;
  }

  public ApiException(String errorCode, String message) {
    super(message);
    this.errorCode = errorCode;
  }

  public ApiException(ResponseCode responseCode) {
    super(responseCode.getReason());
    this.errorCode = responseCode.value();
  }

  public ApiException(List<ErrorList> errorList) {
    if (!CollectionUtils.isEmpty(errorList)) {
      this.errorCode = errorList.get(0).getId();
      this.errorList = errorList;
    }
  }

  public String getErrorCode() {
    return errorCode;
  }

  public List<ErrorList> getErrorList() {
    return errorList;
  }
}
