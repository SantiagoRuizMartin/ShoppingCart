/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.store.rest;

import com.store.model.ClientCart;
import com.store.model.Customer;
import com.store.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author enrique
 */
@RestController
public class CartController {
    
    @Autowired
    private CartRepository cartRepository;
    
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
}
