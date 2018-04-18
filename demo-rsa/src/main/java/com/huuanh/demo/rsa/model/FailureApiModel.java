package com.huuanh.demo.rsa.model;

import com.huuanh.demo.rsa.exception.*;

import java.util.ArrayList;
import java.util.List;

public class FailureApiModel extends BaseApiModel {

    private List<ErrorList> errorList = new ArrayList<>();

    private List<ValidationMessage> validationMessageList = new ArrayList<>();

    public FailureApiModel(String message) {
        super("failure", message);
    }

    public FailureApiModel(ResponseCode code) {
        super("failure", code.value(), code.getReason());
    }

    public FailureApiModel(String code, String message) {
        super("failure", code, message);
    }

    public FailureApiModel(ApiException exception) {
        this(exception.getErrorCode(), exception.getMessage());

        errorList = exception.getErrorList();
    }

    public FailureApiModel(ValidationException exception) {
        this(exception.getErrorCode(), exception.getMessage());

        exception.getFieldErrors().forEach(fieldError -> {
            ValidationMessage vm = new ValidationMessage();
            vm.setField(fieldError.getField());
            vm.setMessage(fieldError.getDefaultMessage());
            vm.setRejectValue(String.valueOf(fieldError.getRejectedValue()));

            getValidationMessageList().add(vm);
        });
    }

    public List<ErrorList> getErrorList() {
        return errorList;
    }

    public List<ValidationMessage> getValidationMessageList() {
        return validationMessageList;
    }

}
