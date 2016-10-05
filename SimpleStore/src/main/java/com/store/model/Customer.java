package com.store.model;

import java.util.List;
import org.springframework.data.annotation.Id;

public class Customer {

    @Id
    private Integer customer_id;
    private String name;
    private String email;
    private String passwordHash;
    private String role;
    private ClientCart clientCart;
    private List<ClientOrder> clientOrders;

    public ClientCart getClientCart() {
        return clientCart;
    }

    public void setClientCart(ClientCart clientCart) {
        this.clientCart = clientCart;
    }

    public List<ClientOrder> getClientOrders() {
        return clientOrders;
    }

    public void setClientOrders(List<ClientOrder> clientOrders) {
        this.clientOrders = clientOrders;
    }

    
    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    
    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }
    

    public Integer getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(Integer customer_id) {
        this.customer_id = customer_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
