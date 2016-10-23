/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.store.repository;

import com.store.model.CartDetail;
import com.store.model.ClientCart;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface CartRepository extends CrudRepository<ClientCart, Integer> {

    ClientCart findByCustomerId(Integer id);

    @Transactional
    @Modifying
    @Query("DELETE FROM CartDetail WHERE id = ?1")
    void deleteCartDetailById(Integer id);

}
