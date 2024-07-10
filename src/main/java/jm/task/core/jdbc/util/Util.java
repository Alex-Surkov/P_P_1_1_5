package jm.task.core.jdbc.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;


public class Util {


    public static Connection getConnection() throws IOException {
        String dbURL = "jdbc:mysql://127.0.0.1:3306/schema_name";
        String dbUsername = "Sur";
        String dbPassword = "kjjLVUdf34F52346*(% ";
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(dbURL, dbUsername, dbPassword);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;

    }
}
