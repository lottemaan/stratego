package stratego.persistence.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import stratego.persistence.RankingItem;

public class ConnectToMySQL {

    String url = "jdbc:mysql://localhost:3306/gamedb";
    String username = "root";
    String password = "sogyo";

    public List<RankingItem> getRankingItems() {
        List<RankingItem> rankingItems = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            String query = "SELECT playerName, nrOfGamesWon FROM ranking";
            try (Statement st = connection.createStatement(); ResultSet rs = st.executeQuery(query)) {
                while (rs.next()) {
                    String playerName = rs.getString("playerName");
                    int nrOfGamesWon = rs.getInt("nrOfGamesWon");
                    RankingItem rankingItem = new RankingItem(playerName, nrOfGamesWon);
                    rankingItems.add(rankingItem);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Failed to retrieve ranking items from the database");
        }
        return rankingItems;
    }
}

