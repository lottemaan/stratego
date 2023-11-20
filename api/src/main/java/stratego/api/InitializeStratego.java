package stratego.api;

import jakarta.servlet.http.*;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;
import stratego.api.models.MoveDTO;
import stratego.api.models.PlacePieceDTO;
import stratego.api.models.StrategoDTO;
import stratego.domain.InvalidMoveException;
import stratego.domain.InvalidPlacementException;
import stratego.domain.Playable;
import stratego.domain.StrategoGame;


@Path("/initialize")
public class InitializeStratego {

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response initialize(@Context HttpServletRequest request, PlacePieceDTO placePiece) throws InvalidPlacementException {
        // Retrieve HTTP session.
        HttpSession session = request.getSession(false);       

        // Retrieve game.
        Playable strategoGame = (StrategoGame) session.getAttribute("stratego");

        int xFromSquare = placePiece.getxFromSquare();
        int yFromSquare = placePiece.getyFromSquare();
        String pieceName = placePiece.getPieceName();
        int playerId = placePiece.getPlayerId();

        strategoGame.placePiece(pieceName, xFromSquare, yFromSquare, playerId);

        // Use the game to create a DTO.
        StrategoDTO output = new StrategoDTO(strategoGame);

        // Send DTO back in response.
        return Response.status(200).entity(output).build();
    }



}
