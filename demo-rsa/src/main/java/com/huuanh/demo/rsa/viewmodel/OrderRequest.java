package com.huuanh.demo.rsa.viewmodel;

import javax.validation.constraints.NotEmpty;

public class OrderRequest {

  @NotEmpty
  private String dataEncrypt;

  public String getDataEncrypt() {
    return dataEncrypt;
  }

  public void setDataEncrypt(String dataEncrypt) {
    this.dataEncrypt = dataEncrypt;
  }
}
