import { useMancalaGame } from "../contexts/StrategoGameContext";
import { GameState, isGameState } from "../types";

export const Play = () => {
    const { gameState, setGameState } = useMancalaGame();

    async function playGame(xCoordinaatVanVak: number, yCoordinaatVanVak: number, xCoordinaatNaarVak: number, yCoordinaatNaarVak: number) {
        const response = await fetch("stratego/api/play", {
            method: "POST",
            headers: {
                Accept: "application/json",
                "Content-Type": "application/json",
            },
            body: JSON.stringify({
                xCoordinaatVanVak: xCoordinaatVanVak,
                yCoordinaatVanVak: yCoordinaatVanVak,
                xCoordinaatNaarVak: xCoordinaatNaarVak,
                yCoordinaatNaarVak: yCoordinaatNaarVak
            }),
        });
    
        if (response.ok) {
            const gameState = await response.json();
            
            setGameState(gameState);
        } else {
            return {
                statusCode: response.status,
                statusText: response.statusText
            };
        }    
    }
    
    // const onSubmit = async () => {
    //     const result = await playGame(3);
        

    //     if (isGameState(result)) {
    //         setGameState(result);
    //     } else{console.log("error");}
    // }

    return <><h1>MANCALA</h1><div>
        Player 1: {gameState?.players[0].name}<br />
        Player 2: {gameState?.players[1].name}<br />
        </div>
        <button
            type="button"
            onClick={() => playGame(3,2,4,5)}
        >
            Play
        </button>
        </>


};