package com.huuanh.demo.rsa.model;

import java.io.Serializable;

public class ProductImagePK implements Serializable {
    private int productId;
    private int seqNo;

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getSeqNo() {
        return seqNo;
    }

    public void setSeqNo(int seqNo) {
        this.seqNo = seqNo;
    }
}
