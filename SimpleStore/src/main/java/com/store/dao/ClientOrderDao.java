package com.store.dao;


import com.store.model.ClientOrder;
import com.store.model.OrderDetail;
import com.store.model.frontmodel.ProductQuantity;

import java.util.Date;
import java.util.List;

public interface ClientOrderDao {

    Integer saveOrder (Integer clientId,Double exchange,String deliveriAddres);

    List<ClientOrder> getOrders(Integer customerId, Date initDate, Date endDate);

    List<OrderDetail> getOrderDetails(Integer orderDetails);

    void saveOrderDetail(ProductQuantity productQuantity, Integer orderId);
}
