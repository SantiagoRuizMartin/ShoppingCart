package com.store.control;


import com.store.dao.ClientOrderDao;
import com.store.dao.ProductDao;
import com.store.model.OrderDetail;
import com.store.model.frontmodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class OrderManager {

    @Autowired
    private ClientOrderDao clientOrderDao;

    @Autowired
    private ProductDao productDao;

    public List<VisualOrderDetail> getVisualOrder(Integer id) {
        return null;
    }

    public Integer createOrder(ClientOrder clientOrder) {
        Integer orderId = saveOrderInDB(clientOrder.getCustomerId(), clientOrder.getExchange_rate(), clientOrder.getDeliveryAddress());
        createOrderDetails(clientOrder.getOrderDetail(), orderId);
        return orderId;
    }

    private Integer saveOrderInDB(Integer customerId, Double exchange_rate, String deliveryAddress) {
        return clientOrderDao.saveOrder(customerId, exchange_rate, deliveryAddress);
    }

    public void createOrderDetails(List<ProductQuantity> orderDetail, Integer orderId) {
        for (ProductQuantity productQuantity : orderDetail) {
            saveOrderDetailInDB(productQuantity, orderId);
        }
    }

    private void saveOrderDetailInDB(ProductQuantity productQuantity, Integer orderId) {
        clientOrderDao.saveOrderDetail(productQuantity, orderId);
    }

    public List<CompleteOrderDetail> getCompleteOrderDetailListByDate(Integer customerId, Date initDate, Date endDate) {
        List<CompleteOrderDetail> completeOrderDetailList = new ArrayList<>();
        List<com.store.model.ClientOrder> orderList = clientOrderDao.getOrders(customerId, initDate, endDate);
        CompleteOrderDetail completeOrderDetail;
        for (com.store.model.ClientOrder clientOrder : orderList) {
            completeOrderDetail = new CompleteOrderDetail();
            completeOrderDetail.setClientOrder(clientOrder);
            completeOrderDetail.setProductDetailList(getProductDetailList(clientOrder.getOrder_id()));
            completeOrderDetailList.add(completeOrderDetail);
        }

        return completeOrderDetailList;
    }

    private List<ProductDetail> getProductDetailList(Integer order_id) {

        List<ProductDetail> productDetails = new ArrayList<>();
        List<OrderDetail> orderDetailList = clientOrderDao.getOrderDetails(order_id);
        for (OrderDetail orderDetail : orderDetailList) {
            ProductDetail productDetail = new ProductDetail();
            productDetail.setProduct(productDao.getProduct(orderDetail.getProduct_id()));
            productDetail.setOrderDetail(orderDetail);
            productDetails.add(productDetail);
        }
        return productDetails;
    }
}
