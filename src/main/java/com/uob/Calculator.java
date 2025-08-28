package com.uob;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class Calculator {
    // Hardcoded credentials (VULNERABILITY)
    private String dbUser = "admin";
    private String dbPassword = "password123";

    public int divide(int a, int b) {
        try {
            return a / b; // BUG: division by zero risk if b == 0
        } catch (Exception e) { 
            // Empty catch block (CODE SMELL)
        }
        return 0;
    }

    // SQL Injection vulnerability
    public void deleteUser(String userId) {
        try {
            Connection conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/mydb", dbUser, dbPassword);
            Statement stmt = conn.createStatement();
            // VULNERABILITY: directly concatenating user input into SQL
            String sql = "DELETE FROM users WHERE id = '" + userId + "'";
            stmt.executeUpdate(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

