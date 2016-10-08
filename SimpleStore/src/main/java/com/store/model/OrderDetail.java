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
public class OrderDetail implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @ManyToOne
    @JsonBackReference
    private ClientOrder clientOrder;
    @OneToOne
    private Product product;
    private Integer quantity;

    protected OrderDetail() {
    }

    public OrderDetail(ClientOrder clientOrder, Product product, Integer quantity) {
        this.clientOrder = clientOrder;
        this.product = product;
        this.quantity = quantity;
    }
    
    

    public ClientOrder getClientOrder() {
        return clientOrder;
    }

    public void setClientOrder(ClientOrder clientOrder) {
        this.clientOrder = clientOrder;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "OrderDetail{" + "product=" + product + ", quantity=" + quantity + '}';
    }
 
}
