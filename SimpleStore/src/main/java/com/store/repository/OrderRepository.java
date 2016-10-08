/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.store.repository;

import com.store.model.ClientOrder;
import java.util.Date;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends CrudRepository<ClientOrder, Integer> {

    List<ClientOrder> findByCustomerCustomerIdAndDateOrderBetween(Integer customerId,Date initDate, Date endDate);
   
}
