package com.huuanh.demo.rsa.service;

import com.huuanh.demo.rsa.model.User;
import com.huuanh.demo.rsa.viewmodel.UserLoginModel;
import com.huuanh.demo.rsa.viewmodel.UserLoginRequest;
import com.huuanh.demo.rsa.viewmodel.UserRegistrationRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.validation.BindingResult;

public interface UserService {

  boolean signUp(UserRegistrationRequest request, BindingResult bindingResult,
      HttpServletResponse response);

  UserLoginModel login(UserLoginRequest request, BindingResult bindingResult,
      HttpServletResponse response);

  User findByEmail(String email);

}
