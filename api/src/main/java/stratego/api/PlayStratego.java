
package stratego.api;

import jakarta.servlet.http.*;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;
import stratego.api.models.MoveDTO;
import stratego.api.models.StrategoDTO;
import stratego.domain.InvalidMoveException;
import stratego.domain.Playable;
import stratego.domain.StrategoGame;
import stratego.persistence.dao.WinnerDAO;

@Path("/play")
public class PlayStratego {

    private WinnerDAO winnerDAO = new WinnerDAO();

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response play(@Context HttpServletRequest request, MoveDTO doMove) throws InvalidMoveException {
        // Retrieve HTTP session.
        HttpSession session = request.getSession(false);       

        // Retrieve game.
        Playable strategoGame = (StrategoGame) session.getAttribute("stratego");

        int xFromSquare = doMove.getxFromSquare();
        int yFromSquare = doMove.getyFromSquare();
        int xToSquare = doMove.getxToSquare();
        int yToSquare = doMove.getyToSquare();

        strategoGame.doMove(xFromSquare, yFromSquare, xToSquare, yToSquare);

        // Use the game to create a DTO.
        StrategoDTO output = new StrategoDTO(strategoGame);

        // Log the winner in the database.
        String winnerName = strategoGame.getWinnerName();
        if (winnerName != null) {
            winnerDAO.logWinner(winnerName);
        }

        // Send DTO back in response.
        return Response.status(200).entity(output).build();
    }
}