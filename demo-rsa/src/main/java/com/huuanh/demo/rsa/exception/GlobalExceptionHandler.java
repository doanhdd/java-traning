package com.huuanh.demo.rsa.exception;

import com.huuanh.demo.rsa.model.FailureApiModel;
import org.hibernate.exception.GenericJDBCException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@ControllerAdvice
@RestController
public class GlobalExceptionHandler {
    @ExceptionHandler(value = ApiException.class)
    public @ResponseBody
    ResponseEntity<FailureApiModel> handleApiException(ApiException e){
        return new ResponseEntity<>(new FailureApiModel(e), HttpStatus.OK);
    }

    @ExceptionHandler(value = ValidationException.class)
    public @ResponseBody
    ResponseEntity<FailureApiModel> handleValidationException(ValidationException e){
        return new ResponseEntity<>(new FailureApiModel(e), HttpStatus.OK);
    }

    @ExceptionHandler(value = Exception.class)
    public @ResponseBody
    ResponseEntity<FailureApiModel> handleException(Exception e){
        return new ResponseEntity<>(new FailureApiModel(ResponseCode.UNKNOWN), HttpStatus.OK);
    }

    @ExceptionHandler(value = GenericJDBCException.class)
    public @ResponseBody
    ResponseEntity<FailureApiModel> handleGenericJDBCException(GenericJDBCException e){
        return new ResponseEntity<>(new FailureApiModel(ResponseCode.UNKNOWN), HttpStatus.OK);
    }
}
