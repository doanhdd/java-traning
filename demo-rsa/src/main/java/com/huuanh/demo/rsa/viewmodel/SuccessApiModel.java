package com.huuanh.demo.rsa.viewmodel;

public class SuccessApiModel extends BaseApiModel {

  private Object data;

  public SuccessApiModel(Object data) {
    super();
    this.data = data;
  }

  public Object getData() {
    return data;
  }

  public void setData(Object data) {
    this.data = data;
  }
}
