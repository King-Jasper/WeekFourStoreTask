package com.multithreadedstore.store;

import com.multithreadedstore.customer.Customer;
import com.multithreadedstore.product.Product;

import java.util.List;

public class Store {
    private final String name;
    private final List<Product> storeProducts;
    private double storeBalance;

    public Store(String name, List<Product> storeProducts, double storeBalance) {
        this.name = name;
        this.storeProducts = storeProducts;
        this.storeBalance = storeBalance;
    }

    public List<Product> getStoreProducts() {
        return storeProducts;
    }

    public double getStoreBalance() {
        return storeBalance;
    }

    public synchronized void receiveOrders(Customer customer) {
        try {
            Thread.sleep(2000);
            System.out.println("Receiving order from " + customer.getName());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        double totalCost = 0.0;
        List<Product> cart = customer.getCart();
        for (Product item : cart) {
            totalCost += item.getProductPrice() * item.getProductQuantity();
        }
        boolean canAfford = customer.makePayment(totalCost);
        if (canAfford) {
            processOrders(cart);
            customer.setCustomerBalance(customer.getCustomerBalance() - totalCost);
            storeBalance += totalCost;
            System.out.println(customer.getName() + " is done purchasing...");
        } else {
            throw new RuntimeException("Sorry you don't have enough funds to buy these products.");
        }
    }
    public synchronized void processOrders(List<Product> cart) {
        try {
            Thread.sleep(500);
            System.out.println("Processing cart... ");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for (Product item : cart) {
            for (Product storeProduct : storeProducts) {
                if (item.getName().equals(storeProduct.getName())) {
                    storeProduct.setProductQuantity(storeProduct.getProductQuantity() - item.getProductQuantity());
                    break;
                }
            }
        }
        System.out.println("Processing order done...");
    }
}
