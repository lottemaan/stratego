
package stratego.api;

import jakarta.servlet.http.*;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;
import stratego.api.models.MancalaDTO;
import stratego.api.models.ZetDTO;
import stratego.domain.StrategoGame;

@Path("/play")
public class PlayStratego {
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response play(@Context HttpServletRequest request, ZetDTO zet) {
        // Retrieve HTTP session.
        HttpSession session = request.getSession(false);       
    
        // Retrieve game.
        StrategoGame StrategoGame = (StrategoGame)session.getAttribute("stratego");


        // Use the game to create a DTO.
        MancalaDTO output = new MancalaDTO(StrategoGame);

        // Send DTO back in response.
        return Response.status(200).entity(output).build();
    }
}