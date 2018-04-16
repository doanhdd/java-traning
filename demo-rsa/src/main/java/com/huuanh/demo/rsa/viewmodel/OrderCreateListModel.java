package com.huuanh.demo.rsa.viewmodel;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import java.util.List;

public class OrderCreateListModel implements Serializable {

  @SerializedName("products")
  private List<OrderCreateModel> products;

  public List<OrderCreateModel> getProducts() {
    return products;
  }

  public void setProducts(List<OrderCreateModel> products) {
    this.products = products;
  }
}
