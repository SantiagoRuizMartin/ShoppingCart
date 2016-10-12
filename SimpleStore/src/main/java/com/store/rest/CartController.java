/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.store.rest;

import com.google.common.collect.Lists;
import com.store.model.CartDetail;
import com.store.model.ClientCart;
import com.store.model.Customer;
import com.store.model.Product;
import com.store.repository.CartRepository;
import com.store.repository.CustomerRepository;
import com.store.repository.ProductRepository;
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

    @Autowired
    private ProductRepository productRepository;


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
        Product product = productRepository.findById(productId);
        if( cart != null){
//            CartDetail cartDetail = new CartDetail(cart,product,quantity);
        }else{
            cart = new ClientCart(customer);
            CartDetail cartDetail = new CartDetail(cart,product,quantity);
            cart.setCartDetail(Lists.newArrayList(cartDetail));
            cartRepository.save(cart);
        }
    }

}
