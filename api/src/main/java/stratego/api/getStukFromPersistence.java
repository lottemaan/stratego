
package stratego.api;


import jakarta.ws.rs.*;
import stratego.persistence.dao.connectToMySQL;

@Path("/stuk")
public class getStukFromPersistence {
    @GET
    public String start() {

        // Retrieve information via persistence from database
        connectToMySQL connectToMySQL = new connectToMySQL();
        String dbOutput = connectToMySQL.stuk;

        return dbOutput;
    }
}

