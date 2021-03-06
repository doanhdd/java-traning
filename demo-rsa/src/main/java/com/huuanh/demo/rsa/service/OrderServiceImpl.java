package com.huuanh.demo.rsa.service;

import com.google.gson.Gson;
import com.huuanh.demo.rsa.common.Constants;
import com.huuanh.demo.rsa.common.RsaAlgorithm;
import com.huuanh.demo.rsa.exception.ApiException;
import com.huuanh.demo.rsa.exception.ResponseCode;
import com.huuanh.demo.rsa.exception.ValidationException;
import com.huuanh.demo.rsa.model.Order;
import com.huuanh.demo.rsa.model.OrderDetail;
import com.huuanh.demo.rsa.model.Product;
import com.huuanh.demo.rsa.model.User;
import com.huuanh.demo.rsa.repository.OrderDetailRepository;
import com.huuanh.demo.rsa.repository.OrderRepository;
import com.huuanh.demo.rsa.repository.ProductRepository;
import com.huuanh.demo.rsa.viewmodel.OrderCreateListModel;
import com.huuanh.demo.rsa.viewmodel.OrderCreateModel;
import com.huuanh.demo.rsa.viewmodel.OrderRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;

import java.security.PrivateKey;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.huuanh.demo.rsa.common.Constants.ORDER_STATUS_CONFIRMED;
import static com.huuanh.demo.rsa.common.Constants.ORDER_STATUS_NEW;

@Service
public class OrderServiceImpl implements OrderService {

  @Autowired
  private ProductRepository productRepository;

  @Autowired
  private OrderRepository orderRepository;

  @Autowired
  private OrderDetailRepository orderDetailRepository;

  @Override
  @Transactional
  public String createOrder(User user, OrderRequest request, BindingResult bindingResult) {
    if (bindingResult.hasErrors()) {
      throw new ValidationException(bindingResult.getFieldErrors());
    }
    String orderIdEncrypt;
    try {
      PrivateKey privateKey = RsaAlgorithm.getPrivateKey(Constants.PRIVATE_KEY_SERVER);
      Gson gson = new Gson();
      String data = RsaAlgorithm.decrypt(request.getDataEncrypt(), privateKey);
      //Start Test
      //  String data = "{\"products\":[{\"id\":1,\"quantity\":5}]}"
      // End test
      OrderCreateListModel orderCreateListModel = gson.fromJson(data, OrderCreateListModel.class);
      List<OrderDetail> orderDetailList = validateOrderDetail(orderCreateListModel);
      Order order = new Order();
      order.setUserId(user.getUserId());
      order.setCreatedAt(new Date());
      order.setOrderStatus(ORDER_STATUS_NEW);

      Order orderSaved = orderRepository.save(order);
      for (OrderDetail orderDetail : orderDetailList) {
        orderDetail.setOrderId(orderSaved.getOrderId());
        orderDetailRepository.save(orderDetail);
      }
      orderIdEncrypt = RsaAlgorithm.encrypt(RsaAlgorithm.signatureOrder(String.valueOf(orderSaved.getOrderId())),
              RsaAlgorithm.getPublicKey(user.getPublicKey()));
    } catch (Exception e) {
      if (e instanceof ApiException) {
        throw (ApiException) e;
      }
      throw new ApiException(ResponseCode.GEN_KEY_RSA_ERROR);
    }

    return orderIdEncrypt;
  }

  @Override
  @Transactional
  public boolean confirmOrder(User user, OrderRequest request, BindingResult bindingResult) {
    if (bindingResult.hasErrors()) {
      throw new ValidationException(bindingResult.getFieldErrors());
    }
    try {
      PrivateKey privateKey = RsaAlgorithm.getPrivateKey(Constants.PRIVATE_KEY_SERVER);
      String plainText = RsaAlgorithm.decrypt(request.getDataEncrypt(), privateKey);
      String data = RsaAlgorithm.verifyOrder(plainText);
      Integer orderId = Integer.valueOf(data);
      Order order = orderRepository.findOrderByOrderId(orderId);
      if (null == order) {
        throw new ApiException(ResponseCode.DATA_NOT_FOUND.value(),
            "Order " + String.valueOf(orderId) + " is not found!");
      }
      order.setOrderStatus(ORDER_STATUS_CONFIRMED);
      orderRepository.save(order);
    } catch (Exception e) {
      throw new ApiException(ResponseCode.GEN_KEY_RSA_ERROR);
    }
    return true;
  }

  private List<OrderDetail> validateOrderDetail(OrderCreateListModel orderCreateListModel) {
    if (null == orderCreateListModel) {
      throw new ApiException(ResponseCode.LOGIC_ERROR.value(), "Cart is empty!");
    }
    List<OrderDetail> orderDetailList = new ArrayList<>();
    for (OrderCreateModel orderCreateModel : orderCreateListModel.getProducts()) {
      Product product = productRepository.findProductByProductId(orderCreateModel.getId());
      if (null == product) {
        throw new ApiException(ResponseCode.DATA_NOT_FOUND.value(),
            "Product " + String.valueOf(orderCreateModel.getId()) + " is not found!");
      }
      if (orderCreateModel.getQuantity() == null || orderCreateModel.getQuantity() <= 0) {
        throw new ApiException(ResponseCode.DATA_NOT_FOUND.value(),
            "Product " + String.valueOf(orderCreateModel.getId())
                + " must have quantity greater than 0!");
      }
      OrderDetail orderDetail = new OrderDetail();
      orderDetail.setProductId(orderCreateModel.getId());
      orderDetail.setQuantity(orderCreateModel.getQuantity());
      orderDetail.setPrice(product.getPrice());
      orderDetailList.add(orderDetail);
    }
    return orderDetailList;
  }
}
