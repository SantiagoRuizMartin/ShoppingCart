/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.store.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;


@Entity
public class CartDetail implements Serializable {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;
    @ManyToOne
    @JsonBackReference
    private ClientCart clientCart;
    @OneToOne
    private Product product;
    private int unit;

    protected CartDetail() {
    }

    public CartDetail(ClientCart clientCart, Product product, int unit) {
        this.clientCart = clientCart;
        this.product = product;
        this.unit = unit;
    }
    
    public ClientCart getClientCart() {
        return clientCart;
    }

    public void setClientCart(ClientCart clientCart) {
        this.clientCart = clientCart;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getUnit() {
        return unit;
    }

    public void setUnit(int unit) {
        this.unit = unit;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "CartDetail{" + "product=" + product + ", unit=" + unit + '}';
    }
    
    
}
