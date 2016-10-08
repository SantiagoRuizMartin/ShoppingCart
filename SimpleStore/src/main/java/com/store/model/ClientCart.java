package com.store.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import java.io.Serializable;
import java.util.List;
import javax.persistence.*;

@Entity
public class ClientCart implements Serializable {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;
    @OneToOne
    @JsonBackReference
    private Customer customer;
    @OneToMany(mappedBy =  "clientCart", cascade = CascadeType.ALL, fetch = FetchType.EAGER) // guardar tambien los detalles
    @JsonManagedReference
    private List<CartDetail> cartDetail;

    protected ClientCart() {
    }

    public ClientCart(Customer customer) {
        this.customer = customer;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    @Override
    public String toString() {
        return "ClientCart{" + "id=" + id + ", cartDetail=" + cartDetail + '}';
    }

}
