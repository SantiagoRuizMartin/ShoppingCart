package com.store.dao.Imp;


import com.store.dao.CustomerDao;
import com.store.dao.Imp.ConnectionDealer;
import com.store.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CustomerMySqlDao implements CustomerDao {


    @Autowired
    private ConnectionDealer connectionDealer;

    @Override
    public List<Customer> getAllCustomers() {

        List<Customer> customerList = new ArrayList<>();

        try {
            Connection connection = connectionDealer.getConnection();
            String sql="{CALL GET_CUSTOMERS()}";
            CallableStatement cStmt = connection.prepareCall(sql);
            cStmt.execute();

            ResultSet rs = cStmt.getResultSet();


            while (rs.next()){
                Customer customer = new Customer();

                customer.setCustomer_id(rs.getInt(1));
                customer.setEmail(rs.getString(3));
                customer.setName(rs.getString(2));
                customerList.add(customer);
            }
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customerList;
    }

    @Override
    public List<Customer> getCustomerById(Integer id) {
        List<Customer> customerByIdList = new ArrayList<>();
        try{
            Connection connection = connectionDealer.getConnection();
            String sql="{CALL GET_CUSTOMER_BY_ID(?)}";
            CallableStatement cStmt = connection.prepareCall(sql);
            cStmt.setInt(1,id);
            cStmt.execute();
            ResultSet rs = cStmt.getResultSet();

            while (rs.next()){
                Customer customer = new Customer();
                customer.setCustomer_id(rs.getInt(1));
                customer.setEmail(rs.getString(3));
                customer.setName(rs.getString(2));
                customerByIdList.add(customer);
            }
            connection.close();
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return customerByIdList;
    }

}
