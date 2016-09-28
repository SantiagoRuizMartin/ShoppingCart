package com.store.dao.Imp;

import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Component
public class ConnectionDealer {

    public Connection getConnection(){

        try {
            Class.forName("com.mysql.jdbc.Driver");
            //Here we specify the host to be connected into the DB
            Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1/simplestoredb?" +
                    "user=root");
            return conn;
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }


}
