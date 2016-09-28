package com.store.model.frontmodel;


import com.store.model.OrderDetail;
import com.store.model.Product;

public class VisualOrderDetail {


    private Product product;
    private OrderDetail orderDetail;


    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public OrderDetail getOrderDetail() {
        return orderDetail;
    }

    public void setOrderDetail(OrderDetail orderDetail) {
        this.orderDetail = orderDetail;
    }
}
