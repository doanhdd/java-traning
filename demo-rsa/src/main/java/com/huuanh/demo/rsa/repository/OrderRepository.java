package com.huuanh.demo.rsa.repository;

import com.huuanh.demo.rsa.model.Order;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<Order, Integer> {

  Order findOrderByOrderId(Integer orderId);

}
