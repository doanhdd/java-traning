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
@Table(name = "products")
public class Product implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "product_id", columnDefinition = "INT(11)", nullable = false)
  private int productId;

  @Column(name = "name", columnDefinition = "VARCHAR(255)", nullable = false)
  private String name;

  @Column(name = "price", columnDefinition = "FLOAT", nullable = false)
  private Float price;

  @Column(name = "weight", columnDefinition = "FLOAT")
  private Float weight;

  @Column(name = "cart_desc", columnDefinition = "VARCHAR(255)")
  private String cartDesc;

  @Column(name = "short_desc", columnDefinition = "VARCHAR(1000)")
  private String shortDesc;

  @Column(name = "long_desc", columnDefinition = "TEXT")
  private String longDesc;

  @Column(name = "product_thumb", columnDefinition = "VARCHAR(255)")
  private String productThumb;

  @Column(name = "product_image", columnDefinition = "VARCHAR(255)")
  private String productImage;

  @Column(name = "product_category_id", columnDefinition = "INT(11)")
  private String productCategoryId;

  @Column(name = "created_at", columnDefinition = "DATETIME")
  @Temporal(TemporalType.TIMESTAMP)
  @CreatedDate
  private Date createdAt;

  @Column(name = "modified_at", columnDefinition = "DATETIME")
  @Temporal(TemporalType.TIMESTAMP)
  @LastModifiedDate
  private Date modifiedAt;

  @OneToOne(mappedBy = "product", targetEntity = OrderDetail.class, fetch = FetchType.LAZY)
  private Set<OrderDetail> orderDetails;

  public Set<OrderDetail> getOrderDetails() {
    return orderDetails;
  }

  public void setOrderDetails(Set<OrderDetail> orderDetails) {
    this.orderDetails = orderDetails;
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

  public Float getWeight() {
    return weight;
  }

  public void setWeight(Float weight) {
    this.weight = weight;
  }

  public String getCartDesc() {
    return cartDesc;
  }

  public void setCartDesc(String cartDesc) {
    this.cartDesc = cartDesc;
  }

  public String getShortDesc() {
    return shortDesc;
  }

  public void setShortDesc(String shortDesc) {
    this.shortDesc = shortDesc;
  }

  public String getLongDesc() {
    return longDesc;
  }

  public void setLongDesc(String longDesc) {
    this.longDesc = longDesc;
  }

  public String getProductThumb() {
    return productThumb;
  }

  public void setProductThumb(String productThumb) {
    this.productThumb = productThumb;
  }

  public String getProductImage() {
    return productImage;
  }

  public void setProductImage(String productImage) {
    this.productImage = productImage;
  }

  public String getProductCategoryId() {
    return productCategoryId;
  }

  public void setProductCategoryId(String productCategoryId) {
    this.productCategoryId = productCategoryId;
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
}
