package com.huuanh.demo.rsa.controller;

import com.huuanh.demo.rsa.service.ProductService;
import com.huuanh.demo.rsa.viewmodel.BaseApiModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController extends BaseApiController {

  @Autowired
  private ProductService productService;

  @RequestMapping(value = PRODUCT_LIST, method = RequestMethod.GET)
  public @ResponseBody
  BaseApiModel signUp() {
    return responseData(productService.getProducts());
  }

}
