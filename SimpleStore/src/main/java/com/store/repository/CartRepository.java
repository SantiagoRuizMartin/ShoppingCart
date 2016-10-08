/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.store.repository;

import com.store.model.ClientCart;
import org.springframework.data.repository.CrudRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends CrudRepository<ClientCart, Integer> {

    public ClientCart findByCustomerId(Integer customerId);
   
}
