
package stratego.api;

import jakarta.servlet.http.*;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;
import stratego.api.models.MoveDTO;
import stratego.api.models.StrategoDTO;
import stratego.domain.StrategoGame;

@Path("/play")
public class PlayStratego {
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response play(@Context HttpServletRequest request, MoveDTO doMove) {
        // Retrieve HTTP session.
        HttpSession session = request.getSession(false);       
    
        // Retrieve game.
        StrategoGame StrategoGame = (StrategoGame)session.getAttribute("stratego");

        int xFromSquare = doMove.getxFromSquare();
        int yFromSquare = doMove.getyFromSquare();
        int xToSquare = doMove.getxToSquare();
        int yToSquare = doMove.getyToSquare();

        StrategoGame.doMove(xFromSquare, yFromSquare, xToSquare, yToSquare);

        // Use the game to create a DTO.
        StrategoDTO output = new StrategoDTO(StrategoGame);

        // Send DTO back in response.
        return Response.status(200).entity(output).build();
    }
}