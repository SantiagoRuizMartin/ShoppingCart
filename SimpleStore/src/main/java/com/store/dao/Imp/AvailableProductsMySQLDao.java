package com.store.dao.Imp;


import com.store.dao.AvailableProductsDao;
import com.store.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class AvailableProductsMySQLDao implements AvailableProductsDao {

    @Autowired
    private ConnectionDealer connectionDealer;

    @Override
    public List<Product> getAvailableProductsForCustomer(Integer customerId) {

        List<Product> availableProducts = new ArrayList<>();
        Connection con = connectionDealer.getConnection();
        String sql = "{CALL GET_PRODUCTS_BY_CUSTOMER(?)}";
        CallableStatement cStmt;

        try {
            cStmt = con.prepareCall(sql);
            cStmt.setInt(1,customerId);
            cStmt.execute();
            ResultSet rs = cStmt.getResultSet();

            while (rs.next()){
                Product product = new Product();
                product.setId(rs.getInt(1));
                product.setName(rs.getString(2));
                product.setPrice(rs.getDouble(3));
                product.setDescription(rs.getString(4));
                availableProducts.add(product);
            }
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return availableProducts;
    }

    @Override
    public void deleteProductsFor(Integer customerId) {

        Connection con = connectionDealer.getConnection();
        String sql = "{CALL DELETE_ALL_THE_AVAILABLE_PRODUCTS_BY_CUSTOMER_ID(?)}";
        CallableStatement cStmt;

        try {
            cStmt = con.prepareCall(sql);
            cStmt.setInt(1,customerId);
            cStmt.execute();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addProductForCustomer(Integer customer, Integer productId) {
        Connection con = connectionDealer.getConnection();
        String sql = "{CALL SET_AVAILABLE_PRODUCT(?,?)}";
        CallableStatement cStmt;
        try {
            cStmt = con.prepareCall(sql);
            cStmt.setInt(1,customer);
            cStmt.setInt(2,productId);
            cStmt.execute();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
