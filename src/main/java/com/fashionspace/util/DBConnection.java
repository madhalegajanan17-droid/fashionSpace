package com.fashionspace.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    // Read individual vars from Render
    private static final String DB_HOST = System.getenv("DB_HOST");
    private static final String DB_PORT = System.getenv("DB_PORT");
    private static final String DB_NAME = System.getenv("DB_NAME");
    private static final String USERNAME = System.getenv("DB_USER");
    private static final String PASSWORD = System.getenv("DB_PASSWORD");
    
    // Build TiDB Cloud JDBC URL with SSL
    private static final String URL = (DB_HOST != null && DB_PORT != null && DB_NAME != null)
            ? "jdbc:mysql://" + DB_HOST + ":" + DB_PORT + "/" + DB_NAME 
              + "?sslMode=VERIFY_IDENTITY&enabledTLSProtocols=TLSv1.2,TLSv1.3"
            : "jdbc:mysql://localhost:3306/fashionspace"; // Local fallback for Eclipse
            
    // Local fallback values if env vars don't exist
    private static final String LOCAL_USERNAME = "root";
    private static final String LOCAL_PASSWORD = "Gajanan@017";

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("MySQL JDBC Driver not found. Check Maven dependency.", e);
        }
    }

    private DBConnection() {
    }

    public static Connection getConnection() throws SQLException {
        // Use Render env vars if they exist, else use local fallback
        String finalUsername = USERNAME != null ? USERNAME : LOCAL_USERNAME;
        String finalPassword = PASSWORD != null ? PASSWORD : LOCAL_PASSWORD;
        
        return DriverManager.getConnection(URL, finalUsername, finalPassword);
    }
}