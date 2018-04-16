package com.huuanh.demo.rsa.viewmodel;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

public class OrderConfirmModel implements Serializable {

  @SerializedName("order_id")
  private int orderId;

  public int getOrderId() {
    return orderId;
  }

  public void setOrderId(int orderId) {
    this.orderId = orderId;
  }
}
