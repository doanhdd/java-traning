package com.huuanh.demo.rsa.exception;

public class ValidationMessage {
    private String field;
    private String rejectValue;
    private String message;

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getRejectValue() {
        return rejectValue;
    }

    public void setRejectValue(String rejectValue) {
        this.rejectValue = rejectValue;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}