package com.multithreadedstore.customer;

import com.multithreadedstore.product.Product;
import com.multithreadedstore.store.Store;

import java.util.ArrayList;
import java.util.List;

public class Customer implements Runnable{
    private final String name;
    private final List<Product> cart;
    private double customerBalance;
    private final Store store;


    public Customer(String name, double customerBalance, Store store) {
        this.name = name;
        this.customerBalance = customerBalance;
        this.store = store;
        this.cart = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void addToCart(Product product, int quantity) {
        Product newProduct = new Product(product.getName(), product.getProductPrice(), quantity);
        cart.add(newProduct);
    }
    public void makePurchase(){
        try {
            store.receiveOrders(this);
        } catch (RuntimeException e) {
            System.err.println(e.getMessage());
        }
    }

    public List<Product> getCart() {
        return cart;
    }

    public double getCustomerBalance() {
        return customerBalance;
    }

    public void setCustomerBalance(double customerBalance) {
        this.customerBalance = customerBalance;
    }

    public boolean makePayment(double amount) {
        return customerBalance >= amount;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        makePurchase();
        String message = String.format("%s is making purchase ...", name);
        System.out.println(message);
    }
}
