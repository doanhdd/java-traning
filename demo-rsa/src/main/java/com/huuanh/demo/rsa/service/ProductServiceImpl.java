package com.huuanh.demo.rsa.service;

import com.huuanh.demo.rsa.model.Product;
import com.huuanh.demo.rsa.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {

  @Autowired
  private ProductRepository productRepository;

  @Override
  public Iterable<Product> getProducts() {
    return productRepository.findAll();
  }
}
