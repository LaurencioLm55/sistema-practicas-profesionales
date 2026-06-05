package com.sistemapracticasprofesional.dataaccess;

import com.sistemapracticasprofesional.logic.exception.DaoException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String ENV_URL = "URL";
    private static final String ENV_USER = "USER";
    private static final String ENV_PASSWORD = "PASSWORD";
    
    private static Connection connection = null;
    
    public DatabaseConnection(){}
    
    public static Connection getConnection(){
        
        try {
            if (connection == null || connection.isClosed()) {
                String url = System.getenv(ENV_URL);
                String user = System.getenv(ENV_USER);
                String password = System.getenv(ENV_PASSWORD);

                if (url == null || user == null || password == null) {
                    throw new DaoException(
                        "Variables de entorno faltantes. Define: " +
                        ENV_URL + ", " + ENV_USER + ", " + ENV_PASSWORD
                    );
                }

                connection = DriverManager.getConnection(url, user, password);
            }
            return connection;
        } catch (SQLException e) {
            throw new DaoException("No se pudo conectar a la base de datos.", e);
        }
    }
        
    public static void closeConnection() {
        if (connection != null) {
            try {
                if (!connection.isClosed()) {
                    connection.close();
                }
            } catch (SQLException e) {
                System.err.println("Error closing connection: " + e.getMessage());
            }
        }
    }
    
}

