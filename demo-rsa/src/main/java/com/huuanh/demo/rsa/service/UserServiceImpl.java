package com.huuanh.demo.rsa.service;

import com.huuanh.demo.rsa.common.RsaAlgorithm;
import com.huuanh.demo.rsa.common.SecurityConstants;
import com.huuanh.demo.rsa.exception.ApiException;
import com.huuanh.demo.rsa.exception.ResponseCode;
import com.huuanh.demo.rsa.exception.ValidationException;
import com.huuanh.demo.rsa.model.User;
import com.huuanh.demo.rsa.repository.UserRepository;
import com.huuanh.demo.rsa.security.TokenHelper;
import com.huuanh.demo.rsa.viewmodel.UserLoginModel;
import com.huuanh.demo.rsa.viewmodel.UserLoginRequest;
import com.huuanh.demo.rsa.viewmodel.UserRegistrationRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;

import javax.servlet.http.HttpServletResponse;
import java.util.Date;

@Service
public class UserServiceImpl implements UserService {

  @Autowired
  private BCryptPasswordEncoder bCryptPasswordEncoder;

  @Autowired
  private UserRepository userRepository;

  @Override
  @Transactional
  public UserLoginModel signUp(UserRegistrationRequest request, BindingResult bindingResult,
      HttpServletResponse response) {
    if (bindingResult.hasErrors()) {
      throw new ValidationException(bindingResult.getFieldErrors());
    }
    if (userRepository.findByEmail(request.getEmail()) != null) {
      throw new ApiException(ResponseCode.SAVE_DATA_ERROR.value(), "The email already exists.");
    }
    String[] privateAndPublicKeys;
    try {
      privateAndPublicKeys = RsaAlgorithm.genPrivateAndPublicKeys();
    } catch (Exception e) {
      e.printStackTrace();
      throw new ApiException(ResponseCode.GEN_KEY_RSA_ERROR);
    }
    User user = new User();
    user.setEmail(request.getEmail());
    user.setPassword(bCryptPasswordEncoder.encode(request.getPassword()));
    user.setRole(SecurityConstants.ROLE_USER);
    user.setCreatedAt(new Date());
    user.setPrivateKey(privateAndPublicKeys[0]);
    userRepository.save(user);

    String token = TokenHelper.addAuthentication(response, user.getEmail());

    return new UserLoginModel(user.getUserId(), user.getEmail(), privateAndPublicKeys[1], token);
  }

  @Override
  public UserLoginModel login(UserLoginRequest request, BindingResult bindingResult,
      HttpServletResponse response) {

    if (bindingResult.hasErrors()) {
      throw new ValidationException(bindingResult.getFieldErrors());
    }

    User user = userRepository.findByEmail(request.getEmail());
    if (null == user) {
      throw new ApiException(ResponseCode.INVALID_AUTHENTICATION.value(), "User not found!");
    }
    if (!bCryptPasswordEncoder.matches(request.getPassword(), user.getPassword())) {
      throw new ApiException(ResponseCode.INVALID_AUTHENTICATION.value(), "Password is incorrect!");
    }

    String[] privateAndPublicKeys;
    try {
      privateAndPublicKeys = RsaAlgorithm.genPrivateAndPublicKeys();
    } catch (Exception e) {
      e.printStackTrace();
      throw new ApiException(ResponseCode.GEN_KEY_RSA_ERROR);
    }
    user.setPrivateKey(privateAndPublicKeys[0]);
    userRepository.save(user);

    String token = TokenHelper.addAuthentication(response, user.getEmail());

    return new UserLoginModel(user.getUserId(), user.getEmail(), privateAndPublicKeys[1], token);
  }

  @Override
  public User findByEmail(String email) {
    User user = userRepository.findByEmail(email);
    if (null == user) {
      throw new ApiException(ResponseCode.INVALID_AUTHENTICATION.value(), "User not found!");
    }
    return user;
  }
}
