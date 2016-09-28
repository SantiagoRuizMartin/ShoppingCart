package com.store.dao.Imp;


import com.store.dao.ExchangeRateDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class ExchangeRateMySqlDao implements ExchangeRateDao{

    @Autowired
    private ConnectionDealer connectionDealer;

    @Override
    public void saveExchangeRate(Double exchangeRate) {

        try{
            Connection connection = connectionDealer.getConnection();
            String sql="{CALL SET_RATE(?)}";
            CallableStatement cStmt = connection.prepareCall(sql);
            cStmt.setDouble(1,exchangeRate);
            cStmt.execute();
            connection.close();
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Double getActualExchangeRate() {

        try {
            Connection connection = connectionDealer.getConnection();
            String sql="{CALL GET_RATE()}";
            CallableStatement cStmt = connection.prepareCall(sql);
            cStmt.execute();

            ResultSet rs = cStmt.getResultSet();
            rs.next();
            double rate = rs.getDouble(1);
            connection.close();
            return rate;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0.0;
    }
}
