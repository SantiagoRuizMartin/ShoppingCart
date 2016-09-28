package com.store.control;

import com.store.dao.AvailableProductsDao;
import com.store.dao.CustomerDao;
import com.store.model.Customer;
import com.store.model.Product;
import com.store.model.frontmodel.CustomerAvailableProducts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CustomerManager {

    @Autowired
    private CustomerDao customerDao;

    @Autowired
    private AvailableProductsDao availableProductsDao;

    public Customer getCustomer(Integer id) {

        return customerDao.getCustomerById(id).get(0);
    }

    public List<CustomerAvailableProducts> getAllCustomersWithAvailableProducts() {

        List<Customer> customerList = queryForAllCustomers();
        List<CustomerAvailableProducts> customerAvailableProductsList = setAvailableProducts(customerList);
        return customerAvailableProductsList;
    }

    private List<Customer> queryForAllCustomers() {
        return customerDao.getAllCustomers();
    }

    private List<CustomerAvailableProducts> setAvailableProducts(List<Customer> customerList) {

        List<CustomerAvailableProducts> visualCustomerList = new ArrayList<>();

        for (Customer customer:customerList){
            CustomerAvailableProducts visualCustomer = new CustomerAvailableProducts();
            visualCustomer.setCustomer(customer);
            List<Product> availableProductsForCustomer = queryForAvailableProducts(customer.getCustomer_id());
            List<Integer> availableProductId = getOnlyId(availableProductsForCustomer);
            visualCustomer.setProducts(availableProductId);
            visualCustomerList.add(visualCustomer);
        }

        return visualCustomerList;
    }

    private List<Integer> getOnlyId(List<Product> availableProductsForCustomer) {

        List<Integer> idList = new ArrayList<>();

        for (Product product:availableProductsForCustomer){
            idList.add(product.getId());
        }
        return idList;
    }

    private List<Product> queryForAvailableProducts(Integer customer_id) {
        return availableProductsDao.getAvailableProductsForCustomer(customer_id);
    }
}
