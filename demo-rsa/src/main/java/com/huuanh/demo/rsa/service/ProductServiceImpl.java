package com.huuanh.demo.rsa.service;

import com.huuanh.demo.rsa.model.Product;
import com.huuanh.demo.rsa.repository.ProductRepository;
import com.huuanh.demo.rsa.viewmodel.ProductViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

  @Autowired
  private ProductRepository productRepository;

  @Override
  public List<ProductViewModel> getProducts() {
    Iterable<Product> products = productRepository.findAll();
    List<ProductViewModel> viewModels = new ArrayList<>();
    for (Product product : products) {
      viewModels.add(new ProductViewModel(product));
    }
    return viewModels;
  }
}
