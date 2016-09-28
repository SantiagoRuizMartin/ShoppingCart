package com.store.dao;

import com.store.model.Customer;

import java.util.List;


public interface CustomerDao {

    List<Customer> getAllCustomers();

    List<Customer> getCustomerById(Integer id);
}
