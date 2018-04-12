package com.huuanh.demo.rsa.controller;


import com.huuanh.demo.rsa.service.UserService;
import com.huuanh.demo.rsa.viewmodel.BaseApiModel;
import com.huuanh.demo.rsa.viewmodel.UserLoginRequest;
import com.huuanh.demo.rsa.viewmodel.UserRegistrationRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController extends BaseApiController {

  @Autowired
  private UserService userService;

  @RequestMapping(value = SIGN_UP_URL,
      method = RequestMethod.POST,
      consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public @ResponseBody
  BaseApiModel signUp(@Valid UserRegistrationRequest userRegistrationRequest,
      BindingResult bindingResult, HttpServletResponse response) {
    return responseData(userService.signUp(userRegistrationRequest, bindingResult, response));
  }

  @RequestMapping(value = SIGN_IN_URL,
      method = RequestMethod.POST,
      consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public @ResponseBody
  BaseApiModel login(@Validated UserLoginRequest request,
      BindingResult bindingResult,
      HttpServletResponse response) {
    return responseData(userService.login(request, bindingResult, response));
  }

}