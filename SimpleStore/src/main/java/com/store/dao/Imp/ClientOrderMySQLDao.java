package com.store.dao.Imp;


import com.store.dao.ClientOrderDao;
import com.store.model.ClientOrder;
import com.store.model.Customer;
import com.store.model.OrderDetail;
import com.store.model.frontmodel.ProductQuantity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.sql.Date;
import java.util.*;

@Repository
public class ClientOrderMySQLDao implements ClientOrderDao {

    @Autowired
    private ConnectionDealer connectionDealer;

    @Override
    public Integer saveOrder(Integer clientId, Double exchange, String deliveriAddres) {

        Integer orderId = 0;
        Connection con = connectionDealer.getConnection();
        try{
            Connection connection = connectionDealer.getConnection();
            String sql="{CALL SET_CUSTOMER_ORDER(?,?,?,?)}";
            CallableStatement cStmt = connection.prepareCall(sql);
            cStmt.setInt(4,orderId);
            cStmt.setInt(1,clientId);
            cStmt.setDouble(2,exchange);
            cStmt.setString(3,deliveriAddres);

            cStmt.execute();
            orderId = cStmt.getInt("orderId");
            connection.close();
            return orderId;
        }catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public List<ClientOrder> getOrders(Integer customerId, java.util.Date initDate, java.util.Date endDate) {

        List<ClientOrder> clientOrders = new ArrayList<>();

        try {
            Connection connection = connectionDealer.getConnection();
            String sql="{CALL GET_ORDERS_BY_DATE(?,?,?)}";
            CallableStatement cStmt = connection.prepareCall(sql);

            cStmt.setDate(1,parseToSqlDate(initDate));
            cStmt.setDate(2,parseToSqlDate(endDate));
            cStmt.setInt(3,customerId);
            cStmt.execute();
            ResultSet rs = cStmt.getResultSet();

            while (rs.next()){
                ClientOrder clientOrder = new ClientOrder();
                clientOrder.setOrder_id(rs.getInt(1));
                clientOrder.setCustomer_Id(rs.getInt(2));
                clientOrder.setDeliveryAddress(rs.getString(3));
                clientOrder.setExchange_rate(rs.getDouble(4));
                clientOrder.setDate(rs.getDate(5));
                clientOrders.add(clientOrder);

            }
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return clientOrders;
    }

    private Date parseToSqlDate(java.util.Date initDate) {
        return new Date(initDate.getTime());
    }

    @Override
    public List<OrderDetail> getOrderDetails(Integer orderId) {

        List<OrderDetail> orderDetailList = new ArrayList<>();

        try {
            Connection connection = connectionDealer.getConnection();
            String sql="{CALL GET_ORDER_DETAILS(?)}";
            CallableStatement cStmt = connection.prepareCall(sql);
            cStmt.setInt(1,orderId);
            cStmt.execute();
            ResultSet rs = cStmt.getResultSet();

            while (rs.next()){
                OrderDetail orderDetail = new OrderDetail();
                orderDetail.setOrder_id(rs.getInt(1));
                orderDetail.setProduct_id(rs.getInt(2));
                orderDetail.setQuantity(rs.getInt(3));
                orderDetailList.add(orderDetail);
            }
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orderDetailList;

    }

    @Override
    public void saveOrderDetail(ProductQuantity productQuantity, Integer orderId) {

        Connection con = connectionDealer.getConnection();
        try{
            Connection connection = connectionDealer.getConnection();
            String sql="{CALL SET_ORDER_DETAILS(?,?,?)}";
            CallableStatement cStmt = connection.prepareCall(sql);
            cStmt.setInt(1,productQuantity.getProductId());
            cStmt.setInt(2,productQuantity.getQuantity());
            cStmt.setInt(3,orderId);
            cStmt.execute();
            connection.close();

        }catch (SQLException e) {
            e.printStackTrace();
        }
    }
}