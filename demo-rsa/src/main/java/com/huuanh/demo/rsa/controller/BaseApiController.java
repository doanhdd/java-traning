package com.huuanh.demo.rsa.controller;

import com.huuanh.demo.rsa.exception.ApiException;
import com.huuanh.demo.rsa.exception.ResponseCode;
import com.huuanh.demo.rsa.model.CustomUserDetail;
import com.huuanh.demo.rsa.model.User;
import com.huuanh.demo.rsa.viewmodel.BaseApiModel;
import com.huuanh.demo.rsa.viewmodel.SuccessApiModel;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class BaseApiController {

  public static final String API_PATH = "/api/v1";
  public static final String SIGN_IN_URL = API_PATH + "/login";
  public static final String SIGN_UP_URL = API_PATH + "/registration";

  protected BaseApiModel responseData(Object data) {
    return new SuccessApiModel(data);
  }

  protected User getUser() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    CustomUserDetail customUserDetail = (CustomUserDetail) authentication.getPrincipal();
    if (null == customUserDetail) {
      throw new ApiException(ResponseCode.INVALID_AUTHENTICATION);
    }
    return customUserDetail.asUser();
  }
}
