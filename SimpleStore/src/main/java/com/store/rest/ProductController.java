package com.store.rest;

import com.store.model.Product;
import com.store.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class ProductController {

    
    @Autowired
    private ProductRepository productRepository;


    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/product/all",method = RequestMethod.GET)
    public List<Product> getAllProducts(){
        return (List<Product>) productRepository.findAll();
    }

}
