package com.huuanh.demo.rsa.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "orders")
public class OrderDetail implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "detail_id", columnDefinition = "INT(11)", nullable = false)
  private int detailId;

  @Column(name = "order_id", columnDefinition = "INT(11)", nullable = false)
  private int orderId;

  @Column(name = "product_id", columnDefinition = "INT(11)", nullable = false)
  private int productId;

  @Column(name = "name", columnDefinition = "VARCHAR(255)")
  private String name;

  @Column(name = "price", columnDefinition = "FLOAT")
  private Float price;

  @Column(name = "quantity", columnDefinition = "INT(11)")
  private Integer quantity;

  @ManyToOne
  @JoinColumn(columnDefinition = "product_id", updatable = false, insertable = false)
  private Product product;

  @ManyToOne
  @JoinColumn(columnDefinition = "order_id", updatable = false, insertable = false)
  private Order order;

  public Product getProduct() {
    return product;
  }

  public void setProduct(Product product) {
    this.product = product;
  }

  public Order getOrder() {
    return order;
  }

  public void setOrder(Order order) {
    this.order = order;
  }

  public int getDetailId() {
    return detailId;
  }

  public void setDetailId(int detailId) {
    this.detailId = detailId;
  }

  public int getOrderId() {
    return orderId;
  }

  public void setOrderId(int orderId) {
    this.orderId = orderId;
  }

  public int getProductId() {
    return productId;
  }

  public void setProductId(int productId) {
    this.productId = productId;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Float getPrice() {
    return price;
  }

  public void setPrice(Float price) {
    this.price = price;
  }

  public Integer getQuantity() {
    return quantity;
  }

  public void setQuantity(Integer quantity) {
    this.quantity = quantity;
  }
}
