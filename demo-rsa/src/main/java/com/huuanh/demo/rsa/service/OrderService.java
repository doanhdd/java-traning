package com.huuanh.demo.rsa.service;

import com.huuanh.demo.rsa.model.User;
import com.huuanh.demo.rsa.viewmodel.OrderRequest;
import org.springframework.validation.BindingResult;

public interface OrderService {

  String createOrder(User user, OrderRequest request,
      BindingResult bindingResult);

  boolean confirmOrder(User user, OrderRequest request,
      BindingResult bindingResult);

}
