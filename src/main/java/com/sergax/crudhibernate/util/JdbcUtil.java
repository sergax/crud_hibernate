package com.sergax.crudhibernate.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JdbcUtil {
    static final String DATABASE_URL_PostSQL = "jdbc:postgresql://localhost:5432/user_db";
    static final String USER_PostSQL = "info_user";
    static final String PASSWORD_PostSQL = "user";

    public static Connection getConnection() {
        Connection connection = null;

        try {
            connection = DriverManager.getConnection(
                    (DATABASE_URL_PostSQL),
                    (USER_PostSQL),
                    (PASSWORD_PostSQL));
        } catch (SQLException e) {
            System.out.println("SQL Error");
        }
        return connection;
    }
}
