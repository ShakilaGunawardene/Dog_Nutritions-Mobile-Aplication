package com.example.fidof;

import java.util.ArrayList;
import java.util.List;

public class Cart {
    private static Cart instance;
    private List<Product> products;

    private Cart() {
        products = new ArrayList<>();
    }

    public static Cart getInstance() {
        if (instance == null) {
            instance = new Cart();
        }
        return instance;
    }

    public void addProduct(Product product) {
        products.add(product);
    }

    public List<Product> getProducts() {
        return products;
    }

    public double getTotalPrice() {
        double total = 0;
        for (Product product : products) {
            try {
                total += Double.parseDouble(product.getProductPrice());
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        return total;
    }
}
