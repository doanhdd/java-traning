package com.huuanh.demo.rsa.exception;

import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.FieldError;

public class ValidationException extends ApiException {

  private List<FieldError> fieldErrors = new ArrayList<>();

  public ValidationException(List<FieldError> fieldErrors) {
    super(ResponseCode.CODE_HAS_VALIDATION_ERROR.value());
    this.setFieldErrors(fieldErrors);
  }

  public List<FieldError> getFieldErrors() {
    return fieldErrors;
  }

  public void setFieldErrors(List<FieldError> fieldErrors) {
    this.fieldErrors = fieldErrors;
  }
}