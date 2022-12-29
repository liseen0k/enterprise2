package org.example.Services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connector {
    private static final String URL = "jdbc:postgresql://localhost:5432/gameStat";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "liseen0k";

    private static Connection connection;

    public static Connection getConnectionInstance(){
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        if(connection == null){
            try {
                connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return connection;
    }
}
