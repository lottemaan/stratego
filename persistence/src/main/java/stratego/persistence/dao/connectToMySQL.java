package stratego.persistence.dao;

import java.sql.*;

public class connectToMySQL {

    public String stuk;
    String url = "jdbc:mysql://localhost:3306/gamedb"; 
    String username = "root"; 
    String password = "sogyo";

    public connectToMySQL() {
        try {
       
            Connection connection = DriverManager.getConnection(url, username, password);
    
            String query = "select *from gamestate"; 

            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(query); 
            rs.next();
            this.stuk = rs.getString("stuk"); 

            st.close(); 

            connection.close();

        } catch (SQLException e) {
            System.err.println("Failed to connect to the database");
            e.printStackTrace();
        }
    }
}

