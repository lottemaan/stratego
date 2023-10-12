import { useMancalaGame } from "../contexts/StrategoGameContext";
import { Play } from "./Play";
import { Start } from "./Start";

export const Mancala = () => {
    const { gameState } = useMancalaGame();
    return gameState ? <Play /> : <Start />;
};