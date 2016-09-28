package com.store.rest;

import com.store.control.CustomerManager;
import com.store.model.Customer;
import com.store.model.frontmodel.CustomerAvailableProducts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CustomerController {

    @Autowired
    private CustomerManager customerManager;

    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/customer", method = RequestMethod.GET)
    public Customer getCustomer(@RequestParam (value = "id") Integer id){
        return customerManager.getCustomer(id);
    }

    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/customer/all", method = RequestMethod.GET)
    public List<CustomerAvailableProducts> getCustomerAvailableProducts(){
        return customerManager.getAllCustomersWithAvailableProducts();
    }

}
