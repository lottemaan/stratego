package stratego.api;

import jakarta.servlet.http.*;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;
import stratego.api.models.StrategoDTO;
import stratego.domain.InvalidPlacementException;
import stratego.domain.Playable;
import stratego.domain.StrategoGame;


@Path("/initializeRandomly")
public class InitializeRandomly {

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response initializeRandomly(@Context HttpServletRequest request) throws InvalidPlacementException {
        
         // Retrieve HTTP session.
         HttpSession session = request.getSession(false);       

         // Retrieve game.
         Playable strategoGame = (StrategoGame) session.getAttribute("stratego");
 
         strategoGame.initializeRandomly();
 
         // Use the game to create a DTO.
         StrategoDTO output = new StrategoDTO(strategoGame);
 
         // Send DTO back in response.
         return Response.status(200).entity(output).build();
    }
}
