/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.store.model;

/**
 *
 * @author Estudiantes
 */
class CartDetail {
    
    private ClientCart clientCart;
    private Product product;
    private int unit;

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
    
    
}
