package com.example.Blogging_platform2.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCUtil {

    private static final String URL = "jdbc:postgresql://localhost:5432/blogging_platform";
    private static final String USER = "postgres";
    private static final String PASSWORD = "Annet@25437";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
