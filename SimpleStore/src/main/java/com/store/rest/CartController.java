/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.store.rest;

import com.store.model.ClientCart;
import com.store.model.Customer;
import com.store.repository.CartRepository;
import com.store.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 *
 * @author enrique
 */
@RestController
public class CartController {
    
    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private CustomerRepository customerRepository;


    /**
     * Retorna el carrito de compras dado un cliente
     * @param id
     * @return 
     */
    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/cart", method = RequestMethod.GET)
    public ClientCart getCartByCustomer(@RequestParam (value = "id") Integer id){
        return cartRepository.findByCustomerId(id);
    }


    /**
     *  añadir producto al carrito de compras
     * @param json parametros de id de cliente, producto y cantidad
     */
    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/addProductToCart", method = RequestMethod.POST)
    public void addProductToCart(@RequestBody Map<String, String> json){
        Integer customerId = new Integer(json.get("customerId"));
        Integer productId = new Integer(json.get("productId"));
        Integer quantity = new Integer(json.get("quantity"));

        Customer customer = customerRepository.findOne(customerId);
        ClientCart cart =  cartRepository.findByCustomerId(customerId);
        if( cart != null){

        }else{
            cart = new ClientCart(customer);

        }
    }

}
