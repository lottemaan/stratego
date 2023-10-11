package stratego.api.mysql;

import java.io.*;
import java.sql.*;

public class connectToMySQL {
    public static void main(String[] args) throws Exception
    {
        String url
            = "jdbc:mysql://localhost:3306/gamedb"; 
        String username = "root"; // MySQL credentials
        String password = "sogyo";

        try {
            // Establish a connection to the database
            Connection connection = DriverManager.getConnection(url, username, password);
            System.out.println("Connected to the database");
            // You can perform database operations here
            String query = "select *from gamestate"; // query to be run
            // Don't forget to close the connection when you're done
            
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(query); // Execute query
            rs.next();
            String name = rs.getString("stuk"); // Retrieve name from db
 
            System.out.println(name); // Print result on console

            st.close(); // close statement

            connection.close();
            System.out.println("Connection Closed....");
        } catch (SQLException e) {
            System.err.println("Failed to connect to the database");
            e.printStackTrace();
        }
    }
}

