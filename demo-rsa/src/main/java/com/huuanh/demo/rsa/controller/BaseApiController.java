package com.huuanh.demo.rsa.controller;

import com.huuanh.demo.rsa.exception.ApiException;
import com.huuanh.demo.rsa.exception.ResponseCode;
import com.huuanh.demo.rsa.model.User;
import com.huuanh.demo.rsa.viewmodel.BaseApiModel;
import com.huuanh.demo.rsa.viewmodel.SuccessApiModel;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class BaseApiController {

  private static final String API_PATH = "/api/v1";
  public static final String SIGN_IN_URL = API_PATH + "/login";
  public static final String SIGN_UP_URL = API_PATH + "/registration";
  public static final String PRODUCT_LIST = API_PATH + "/products";
  public static final String ORDER_CREATE = API_PATH + "/orders/create";
  public static final String ORDER_CONFIRM = API_PATH + "/orders/confirm";

  protected BaseApiModel responseData(Object data) {
    return new SuccessApiModel(data);
  }

  protected User getUser() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    User user = (User) authentication.getCredentials();
    if (null == user) {
      throw new ApiException(ResponseCode.INVALID_AUTHENTICATION);
    }
    return user;
  }
}
