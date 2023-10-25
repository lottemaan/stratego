package stratego.persistence.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class InsertInMySQL {

    private Connection connection;
    
    // Database connection parameters
    private final String url = "jdbc:mysql://localhost:3306/gamedb";
    private final String username = "root";
    private final String password = "sogyo";

    public InsertInMySQL() {
        // Initialize the database connection in the constructor
        connect();
    }

    public Connection connect() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            System.out.println("Failed to connect to the database.");
        }
        return connection;
    }

    public Connection getConnection() {
        return connection;
    }

    public void closeConnection() {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Failed to close the database connection.");
        }
    }
}
