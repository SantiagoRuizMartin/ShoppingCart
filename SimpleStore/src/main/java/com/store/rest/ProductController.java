package com.store.rest;

import com.store.control.StockManager;
import com.store.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class ProductController {

    @Autowired
    private StockManager stockManager;

    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/product",method = RequestMethod.GET)
    public List<Product> getProduct(@RequestParam(value = "id",required = false) Integer id,
                                    @RequestParam(value = "customerId") Integer customerId){

        if (id == null){
            return stockManager.getProductsInStock(customerId);
        }
        return stockManager.getProduct(id,customerId);
    }

    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/product/all",method = RequestMethod.GET)
    public List<Product> getAllProducts(){
        return stockManager.getAllProducts();
    }




}
