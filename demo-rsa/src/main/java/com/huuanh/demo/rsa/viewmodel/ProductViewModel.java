package com.huuanh.demo.rsa.viewmodel;

import com.huuanh.demo.rsa.model.Product;

import java.util.Date;

public class ProductViewModel {
    private int productId;
    private String name;
    private float price;
    private String cartDesc;
    private String shortDesc;
    private String longDesc;
    private Date createdAt;
    private String[] productImages;

    public ProductViewModel(Product product) {
        this.productId = product.getProductId();
        this.name = product.getName();
        this.price = product.getPrice();
        this.cartDesc = product.getCartDesc();
        this.shortDesc = product.getShortDesc();
        this.longDesc = product.getLongDesc();
        this.createdAt = product.getCreatedAt();
        this.productImages = new String[product.getProductImages().size()];
        for (int i = 0; i < product.getProductImages().size(); i++) {
            this.productImages[i] = product.getProductImages().get(i).getImageUrl();
        }
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

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
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

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public String[] getProductImages() {
        return productImages;
    }

    public void setProductImages(String[] productImages) {
        this.productImages = productImages;
    }
}
