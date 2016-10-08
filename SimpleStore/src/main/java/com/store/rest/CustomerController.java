package com.store.rest;

import com.store.model.Customer;
import com.store.repository.CustomerRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class CustomerController {

    
    @Autowired
    private CustomerRepository customerRepository;
    

    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/customer", method = RequestMethod.GET)
    public Customer getCustomerById(@RequestParam (value = "id") Integer id){
        return customerRepository.findOne(id);
    }
    
    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/customer/all", method = RequestMethod.GET)
    public List<Customer> getAllCustomers(){
        return (List<Customer>) customerRepository.findAll();
    }

   
}
