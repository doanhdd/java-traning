package com.huuanh.demo.rsa.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@IdClass(ProductImagePK.class)
@Table(name = "product_images")
public class ProductImage implements Serializable {

    @Id
    @Column(name = "product_id", columnDefinition = "INT(11)", nullable = false)
    private int productId;

    @Id
    @Column(name = "seq_no", columnDefinition = "INT(11)", nullable = false)
    private int seqNo;

    @Column(name = "image_url", columnDefinition = "VARCHAR(255)", nullable = false)
    private String imageUrl;

    @ManyToOne()
    @JoinColumn(name = "product_id", insertable = false, updatable = false)
    private Product product;

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

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
