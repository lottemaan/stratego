
package stratego.api;

import jakarta.servlet.http.*;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;
import stratego.api.models.StrategoDTO;
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

        StrategoGame.doMove(10,4,10,5);

        // Use the game to create a DTO.
        StrategoDTO output = new StrategoDTO(StrategoGame);

        // Send DTO back in response.
        return Response.status(200).entity(output).build();
    }
}