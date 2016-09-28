package com.store.model.frontmodel;

import com.store.model.Customer;

import java.util.List;


public class CustomerAvailableProducts {
    private Customer customer;
    private List<Integer> products;

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setProducts(List<Integer> products) {
        this.products = products;
    }

    public List<Integer> getProducts() {
        return products;
    }
}
