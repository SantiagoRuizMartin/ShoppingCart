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
            // asegurarse que el usuario tenga permisos sobre todos los objetos de la base de datos
            // y tambien de mysql.proc (aceeso a nombres de procedimientos almacenados)
            Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1/simplestoredba",
                    "userstore","u$3r$t0r3");
            return conn;
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }


}
