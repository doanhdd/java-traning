package com.huuanh.demo.rsa.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

@Entity
@Table(name = "orders")
public class Order implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "order_id", columnDefinition = "INT(11)", nullable = false)
  private int orderId;

  @Column(name = "user_id", columnDefinition = "INT(11)", nullable = false)
  private int userId;

  /**
   * 0: NEW 1: CONFIRMED 2: IN PROGRESS 3: COMPLETE
   */
  @Column(name = "order_status", columnDefinition = "TINYINT(4)", nullable = false)
  private int orderStatus;

  @Column(name = "order_amount", columnDefinition = "FLOAT")
  private Float orderAmount;

  @Column(name = "ship_name", columnDefinition = "VARCHAR(255)")
  private String shipName;

  @Column(name = "ship_address", columnDefinition = "VARCHAR(255)")
  private String shipAddress;

  @Column(name = "ship_address2", columnDefinition = "VARCHAR(255)")
  private String shipAddress2;

  @Column(name = "city", columnDefinition = "VARCHAR(50)")
  private String city;

  @Column(name = "state", columnDefinition = "VARCHAR(50)")
  private String state;

  @Column(name = "zip", columnDefinition = "VARCHAR(50)")
  private String zip;

  @Column(name = "country", columnDefinition = "VARCHAR(50)")
  private String country;

  @Column(name = "phone", columnDefinition = "VARCHAR(20)")
  private String phone;

  @Column(name = "fax", columnDefinition = "VARCHAR(20)")
  private String fax;

  @Column(name = "order_shipping", columnDefinition = "FLOAT")
  private Float orderShipping;

  @Column(name = "order_tax", columnDefinition = "FLOAT")
  private Float orderTax;

  @Column(name = "order_email", columnDefinition = "VARCHAR(100)")
  private String orderEmail;

  @Column(name = "created_at", columnDefinition = "DATETIME")
  @Temporal(TemporalType.TIMESTAMP)
  @CreatedDate
  private Date createdAt;

  @Column(name = "modified_at", columnDefinition = "DATETIME")
  @Temporal(TemporalType.TIMESTAMP)
  @LastModifiedDate
  private Date modifiedAt;

  @OneToOne(mappedBy = "order", targetEntity = OrderDetail.class, fetch = FetchType.LAZY)
  private Set<OrderDetail> orderDetails;

  public Set<OrderDetail> getOrderDetails() {
    return orderDetails;
  }

  public void setOrderDetails(Set<OrderDetail> orderDetails) {
    this.orderDetails = orderDetails;
  }

  public int getOrderId() {
    return orderId;
  }

  public void setOrderId(int orderId) {
    this.orderId = orderId;
  }

  public int getUserId() {
    return userId;
  }

  public void setUserId(int userId) {
    this.userId = userId;
  }

  public Float getOrderAmount() {
    return orderAmount;
  }

  public void setOrderAmount(Float orderAmount) {
    this.orderAmount = orderAmount;
  }

  public String getShipName() {
    return shipName;
  }

  public void setShipName(String shipName) {
    this.shipName = shipName;
  }

  public String getShipAddress() {
    return shipAddress;
  }

  public void setShipAddress(String shipAddress) {
    this.shipAddress = shipAddress;
  }

  public String getShipAddress2() {
    return shipAddress2;
  }

  public void setShipAddress2(String shipAddress2) {
    this.shipAddress2 = shipAddress2;
  }

  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public String getState() {
    return state;
  }

  public void setState(String state) {
    this.state = state;
  }

  public String getZip() {
    return zip;
  }

  public void setZip(String zip) {
    this.zip = zip;
  }

  public String getCountry() {
    return country;
  }

  public void setCountry(String country) {
    this.country = country;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public String getFax() {
    return fax;
  }

  public void setFax(String fax) {
    this.fax = fax;
  }

  public Float getOrderShipping() {
    return orderShipping;
  }

  public void setOrderShipping(Float orderShipping) {
    this.orderShipping = orderShipping;
  }

  public Float getOrderTax() {
    return orderTax;
  }

  public void setOrderTax(Float orderTax) {
    this.orderTax = orderTax;
  }

  public String getOrderEmail() {
    return orderEmail;
  }

  public void setOrderEmail(String orderEmail) {
    this.orderEmail = orderEmail;
  }

  public Date getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(Date createdAt) {
    this.createdAt = createdAt;
  }

  public Date getModifiedAt() {
    return modifiedAt;
  }

  public void setModifiedAt(Date modifiedAt) {
    this.modifiedAt = modifiedAt;
  }

  public int getOrderStatus() {
    return orderStatus;
  }

  public void setOrderStatus(int orderStatus) {
    this.orderStatus = orderStatus;
  }
}
