package jm.task.core.jdbc.util;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class Util {
    private static final SessionFactory sessionFactory;

    static {
        sessionFactory = new Configuration().configure().buildSessionFactory();
    }

    public static Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    public static Connection getConnection() {
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
