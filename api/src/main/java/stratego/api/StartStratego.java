package stratego.api;

import jakarta.servlet.http.*;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;
import stratego.api.models.StrategoDTO;
import stratego.api.models.PlayerInputDTO;
import stratego.domain.Playable;
import stratego.domain.StrategoGame;

@Path("/start")
public class StartStratego {
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response start(@Context HttpServletRequest request, PlayerInputDTO players) {
        // Create HTTP session.
        HttpSession session = request.getSession(true);

		String playerOne = players.getPlayer1();
		String playerTwo = players.getPlayer2();

        // Initialize game.
        Playable stratego = new StrategoGame(playerOne, playerTwo);

        // Attach game to session.
        session.setAttribute("stratego", stratego);

        var output = new StrategoDTO(stratego);


        return Response.status(200).entity(output).build();
    }
}




//@Path("{id}")
// @GET
// @Consumes(MediaType.APPLICATION_JSON)
// @Produces(MediaType.APPLICATION_JSON)
// public Response getNameOfPlayerById(@Context HttpServletRequest request, @PathParam("id")String id){
//
//     MancalaGame mancalagame = new MancalaGame();
//
//     if(!id.equals("1") && !id.equals("2")){
//         return Response.status(400).build();
//     }
//
//     if(id.equals("1")){
//         return Response.status(200).entity(mancalagame.getNameOfPlayerOne()).build();
//     }
//
//     return Response.status(200).entity(mancalagame.getNameOfPlayerTwo()).build();
//
// }}
//
//
//return id.equals("1") ? Response.status(200).entity(mancalagame).getPlayerOne() : Response.status(200).entity(mancalagame).getPlayerTwo();
//
//


