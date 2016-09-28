package com.store.dao.Imp;



import com.store.dao.Imp.ConnectionDealer;
import com.store.dao.ProductDao;
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
public class ProductMySQLDao implements ProductDao {

    @Autowired
    private ConnectionDealer connectionDealer;

    @Override
    public Product getProduct(Integer id) {
        List<Product> productList = new ArrayList<>();
        try{
            Connection connection = connectionDealer.getConnection();
            String sql="{CALL GET_PRODUCT(?)}";
            CallableStatement cStmt = connection.prepareCall(sql);
            cStmt.setInt(1,id);
            cStmt.execute();
            ResultSet rs = cStmt.getResultSet();

            while (rs.next()){
                Product product = new Product();
                product.setId(rs.getInt(1));
                product.setName(rs.getString(2));
                product.setPrice(rs.getDouble(3));
                product.setDescription(rs.getString(4));
                productList.add(product);
            }
            connection.close();

        }catch (SQLException e) {
            e.printStackTrace();
        }
        return productList.get(0);
    }

    @Override
    public List<Product> getAllProducts() {

        List<Product> productList = new ArrayList<>();
        try{
            Connection connection = connectionDealer.getConnection();
            String sql="{CALL GET_PRODUCTS()}";
            CallableStatement cStmt = connection.prepareCall(sql);

            cStmt.execute();
            ResultSet rs = cStmt.getResultSet();

            while (rs.next()){
                Product product = new Product();
                product.setId(rs.getInt(1));
                product.setName(rs.getString(2));
                product.setPrice(rs.getDouble(3));
                product.setDescription(rs.getString(4));
                productList.add(product);
            }
            connection.close();

        }catch (SQLException e) {
            e.printStackTrace();
        }

        return productList;
    }
}
