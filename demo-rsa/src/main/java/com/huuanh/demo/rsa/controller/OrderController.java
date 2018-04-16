package com.huuanh.demo.rsa.controller;

import com.huuanh.demo.rsa.service.OrderService;
import com.huuanh.demo.rsa.viewmodel.BaseApiModel;
import com.huuanh.demo.rsa.viewmodel.OrderRequest;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController extends BaseApiController {

  @Autowired
  private OrderService orderService;

  @RequestMapping(value = ORDER_CREATE,
      method = RequestMethod.POST,
      consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public @ResponseBody
  BaseApiModel createOrder(@Valid OrderRequest request, BindingResult bindingResult) {
    return responseData(orderService.createOrder(getUser(), request, bindingResult));
  }

  @RequestMapping(value = ORDER_CONFIRM,
      method = RequestMethod.POST,
      consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public @ResponseBody
  BaseApiModel confirmOrder(@Valid OrderRequest request, BindingResult bindingResult) {
    return responseData(orderService.confirmOrder(getUser(), request, bindingResult));
  }

}
