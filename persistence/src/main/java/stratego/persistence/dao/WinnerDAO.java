package stratego.persistence.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class WinnerDAO {

    public void logWinner(String winnerName) {
        Connection connection = null;
    
        try {
            insertInMySQL dbConnector = new insertInMySQL();
            connection = dbConnector.connect();
    
            String checkPlayerSql = "SELECT idPlayer, nrOfGamesWon FROM ranking WHERE playerName = ?";
            PreparedStatement checkPlayerStatement = connection.prepareStatement(checkPlayerSql);
            checkPlayerStatement.setString(1, winnerName);
    
            ResultSet result = checkPlayerStatement.executeQuery();
    
            if (result.next()) {
                // Player exists, update the nrOfGamesWon
                int idPlayer = result.getInt("idPlayer");
                int nrOfGamesWon = result.getInt("nrOfGamesWon") + 1;
    
                String updatePlayerSql = "UPDATE ranking SET nrOfGamesWon = ? WHERE idPlayer = ?";
                PreparedStatement updatePlayerStatement = connection.prepareStatement(updatePlayerSql);
                updatePlayerStatement.setInt(1, nrOfGamesWon);
                updatePlayerStatement.setInt(2, idPlayer);
    
                updatePlayerStatement.executeUpdate();
            } else {
                // Player doesn't exist, insert a new player
                // Calculate the new idPlayer
                int idPlayer = 1; // Initialize with a default value
                String getMaxIdPlayerSql = "SELECT MAX(idPlayer) FROM ranking";

                try (Statement getMaxIdPlayerStatement = connection.createStatement();
                    ResultSet maxIdPlayerResult = getMaxIdPlayerStatement.executeQuery(getMaxIdPlayerSql)) {

                    if (maxIdPlayerResult.next()) {
                        idPlayer = maxIdPlayerResult.getInt(1) + 1;
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                    System.out.println("Failed to retrieve the max idPlayer.");
                }

                String insertPlayerSql = "INSERT INTO ranking (idPlayer, playerName, nrOfGamesWon) VALUES (?, ?, 1)";
                try (PreparedStatement insertPlayerStatement = connection.prepareStatement(insertPlayerSql, Statement.RETURN_GENERATED_KEYS)) {
                    insertPlayerStatement.setInt(1, idPlayer);  // Use the calculated idPlayer
                    insertPlayerStatement.setString(2, winnerName);

                    int rowsAffected = insertPlayerStatement.executeUpdate();

                    if (rowsAffected > 0) {
                        ResultSet generatedKeys = insertPlayerStatement.getGeneratedKeys();
                        if (generatedKeys.next()) {
                            int newIdPlayer = generatedKeys.getInt(1);
                            System.out.println("New player inserted with idPlayer: " + newIdPlayer);
                        }
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                    System.out.println("Data insertion failed!");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Data insertion failed!");
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}