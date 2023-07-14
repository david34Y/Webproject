package com.example.webproject.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String productId;

    @JsonProperty("discountAvailable")
    private String discountAvialable;

    @JsonProperty("discountNote")
    private int discountNote;

    @JsonProperty("discountPrice")
    private int discountPrice;

    @JsonProperty("originalPrice")
    private int originalPrice;

    @JsonProperty("productCategory")
    private String productCategory;

    @JsonProperty("productDescription")
    private String productDescription;

    @JsonProperty("productIcon")
    private String productIcon;

    @JsonProperty("productQuantity")
    private int productQuantity;

    @JsonProperty("productTitle")
    private String productTitle;

    @JsonProperty("timestamp")
    private long timesptamp;

    @JsonProperty("uid")
    private String uid;

    // getters and setters


    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getDiscountAvialable() {
        return discountAvialable;
    }

    public void setDiscountAvialable(String discountAvialable) {
        this.discountAvialable = discountAvialable;
    }

    public int getDiscountNote() {
        return discountNote;
    }

    public void setDiscountNote(int discountNote) {
        this.discountNote = discountNote;
    }

    public int getDiscountPrice() {
        return discountPrice;
    }

    public void setDiscountPrice(int discountPrice) {
        this.discountPrice = discountPrice;
    }

    public int getOriginalPrice() {
        return originalPrice;
    }

    public void setOriginalPrice(int originalPrice) {
        this.originalPrice = originalPrice;
    }

    public String getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(String productCategory) {
        this.productCategory = productCategory;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public String getProductIcon() {
        return productIcon;
    }

    public void setProductIcon(String productIcon) {
        this.productIcon = productIcon;
    }

    public int getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(int productQuantity) {
        this.productQuantity = productQuantity;
    }

    public String getProductTitle() {
        return productTitle;
    }

    public void setProductTitle(String productTitle) {
        this.productTitle = productTitle;
    }

    public long getTimesptamp() {
        return timesptamp;
    }

    public void setTimesptamp(long timesptamp) {
        this.timesptamp = timesptamp;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
}
