package com.huuanh.demo.rsa.model;

import com.huuanh.demo.rsa.exception.ResponseCode;
import lombok.Data;

@Data
public class BaseApiModel {
    private String status;
    private String code;
    private String message;

    public BaseApiModel() {
        status = "success";
        code = ResponseCode.SUCCESS.value();
    }

    public BaseApiModel(String status, String message) {
        this.status = status;
        this.message = message;
    }

    public BaseApiModel(String status, String code, String message) {
        this.status = status;
        this.code = code;
        this.message = message;
    }

}
