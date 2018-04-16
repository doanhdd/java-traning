package com.huuanh.demo.rsa.viewmodel;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

public class OrderCreateModel implements Serializable {

  @SerializedName("id")
  private Integer id;

  @SerializedName("quantity")
  private Integer quantity;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public Integer getQuantity() {
    return quantity;
  }

  public void setQuantity(Integer quantity) {
    this.quantity = quantity;
  }
}
