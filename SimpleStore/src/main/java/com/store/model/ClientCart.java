package com.store.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class ClientCart implements Serializable {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer cartId;
    @OneToOne
    private Customer customer;
    @OneToMany(mappedBy =  "clientCart")
    private List<CartDetail> cartDetail;

    protected ClientCart() {
    }

    public ClientCart(Customer customer) {
        this.customer = customer;
    }
    
    public Integer getCartId() {
        return cartId;
    }

    public void setCartId(Integer cartId) {
        this.cartId = cartId;
    }


    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<CartDetail> getCartDetail() {
        return cartDetail;
    }

    public void setCartDetail(List<CartDetail> cartDetail) {
        this.cartDetail = cartDetail;
    }
    
    
}
