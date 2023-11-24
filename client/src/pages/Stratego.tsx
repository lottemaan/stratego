import { useMancalaGame } from "../contexts/StrategoGameContext";
import { useConfigurationContext } from  "../contexts/ConfigurationContext";
import { Initialize } from "./Initialize";
import { Play } from "./Play";
import { Start } from "./Start";

export const Mancala = () => {
    const { gameState } = useMancalaGame();
    const { playerTwoPiecesSaved } = useConfigurationContext();

    if (gameState) {
        if (playerTwoPiecesSaved == true) {
            console.log("playerTwoPiecesSaved in stratego.tsx: ", playerTwoPiecesSaved)
            return <Play />;
        } else {
            console.log("playerTwoPiecesSaved in stratego.tsx: ", playerTwoPiecesSaved)
            return <Initialize />;
        }
       
    } else {
        return <Start />;
    }
};
