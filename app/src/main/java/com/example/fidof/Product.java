package com.example.fidof;

public class Product {
    private String productName, productDescription, productPrice, id, imageURL;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(String productPrice) {
        this.productPrice = productPrice;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public Product(String id, String imageURL, String productDescription, String productName, String productPrice) {
        this.id = id;
        this.imageURL = imageURL;
        this.productDescription = productDescription;
        this.productName = productName;
        this.productPrice = productPrice;
    }

    public Product() {
    }

    public class product{

    }
}
