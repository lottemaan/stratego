// import { useMancalaGame } from "../contexts/StrategoGameContext";
// import { Play } from "./Play";
// import { Start } from "./Start";

// const Mancala = () => {
//   const { gameState } = useMancalaGame();

//   return (
//     <>
//       {!gameState && <Start />}
//       {gameState && !gameState.board.initialized && <Initialize />}
//       {gameState && gameState.board.initialized && <Play />}
//     </>
//   );
// };

// export default Mancala;

import { useMancalaGame } from "../contexts/StrategoGameContext";
import { Initialize } from "./Initialize";
import { Play } from "./Play";
import { Start } from "./Start";

export const Mancala = () => {
    const { gameState } = useMancalaGame();

    if (gameState) {
        if (gameState.board.playerOneReady && gameState.board.playerTwoReady) {
            return <Play />;
        } else {
            return <Initialize />;
        }
    } else {
        return <Start />;
    }
};
