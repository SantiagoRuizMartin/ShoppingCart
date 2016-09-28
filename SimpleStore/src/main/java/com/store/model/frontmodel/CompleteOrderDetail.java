package com.store.model.frontmodel;

import com.store.model.ClientOrder;

import java.util.List;

public class CompleteOrderDetail {


    private com.store.model.ClientOrder clientOrder;
    private List<ProductDetail> productDetailList;

    public ClientOrder getClientOrder() {
        return clientOrder;
    }

    public void setClientOrder(ClientOrder clientOrder) {
        this.clientOrder = clientOrder;
    }

    public List<ProductDetail> getProductDetailList() {
        return productDetailList;
    }

    public void setProductDetailList(List<ProductDetail> productDetailList) {
        this.productDetailList = productDetailList;
    }
}
