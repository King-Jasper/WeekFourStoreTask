package com.multithreadedstore.product;

public class Product {
    private String name;
    private double ProductPrice;
    private int ProductQuantity;

    public Product(String name, double productPrice, int productQuantity) {
        this.name = name;
        ProductPrice = productPrice;
        ProductQuantity = productQuantity;
    }

    public String getName() {
        return name;
    }


    public double getProductPrice() {
        return ProductPrice;
    }

    public void setProductPrice(double productPrice) {
        ProductPrice = productPrice;
    }

    public int getProductQuantity() {
        return ProductQuantity;
    }

    public void setProductQuantity(int productQuantity) {
        ProductQuantity = productQuantity;
    }

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", ProductQuantity=" + ProductQuantity +
                '}';
    }
}
