package stratego.api;

import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import stratego.persistence.RankingItem;
import stratego.persistence.dao.ConnectToMySQL;

@Path("/ranking")
public class RankingStratego {
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String start() {

        // Retrieve information via persistence from database
        ConnectToMySQL connectToMySQL = new ConnectToMySQL();
        List<RankingItem> rankingItems = connectToMySQL.getRankingItems();

        // Convert the list of ranking items to JSON
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            String json = objectMapper.writeValueAsString(rankingItems);
            return json;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return "Failed to generate JSON data.";
        }
    }

}

