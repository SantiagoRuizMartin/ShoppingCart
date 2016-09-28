package com.store.model.frontmodel;


import com.store.model.OrderDetail;
import com.store.model.Product;

public class ProductDetail {

    private OrderDetail orderDetail;
    private Product product;

    public OrderDetail getOrderDetail() {
        return orderDetail;
    }

    public void setOrderDetail(OrderDetail orderDetail) {
        this.orderDetail = orderDetail;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
