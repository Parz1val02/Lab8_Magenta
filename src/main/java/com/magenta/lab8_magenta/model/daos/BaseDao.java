package com.magenta.lab8_magenta.model.daos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class BaseDao {

    public Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e1) {
            e1.printStackTrace();
        }

        String user = "root";
        String pass = "root";
        String url = "jdbc:mysql://localhost:3306/magenta?serverTimeZone=America/Lima";

        return DriverManager.getConnection(url, user, pass);
    }


}
