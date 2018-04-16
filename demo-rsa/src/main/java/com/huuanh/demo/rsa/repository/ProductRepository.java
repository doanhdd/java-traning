package com.huuanh.demo.rsa.repository;

import com.huuanh.demo.rsa.model.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, Integer> {

  Product findProductByProductId(Integer productId);

}
