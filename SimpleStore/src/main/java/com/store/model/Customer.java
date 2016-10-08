package com.store.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(uniqueConstraints=
@UniqueConstraint(columnNames = {"email"}))
public class Customer implements Serializable {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer customerId;
    @NotNull
    private String name;
    private String email;
    @NotNull
    private String passwordHash;
    @NotNull
    private String UserRole;
    @OneToOne(mappedBy = "customer")
    private ClientCart clientCart;
    @OneToMany(mappedBy = "customer")
    private List<ClientOrder> clientOrders;

    public Customer() {
    }

    public Customer(String name, String email, String passwordHash, String userRole) {
        this.name = name;
        this.email = email;
        this.passwordHash = passwordHash;
        this.UserRole = userRole;
    }
    
    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
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

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public String getUserRole() {
        return UserRole;
    }

    public void setUserRole(String UserRole) {
        this.UserRole = UserRole;
    }

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
 
 
}
