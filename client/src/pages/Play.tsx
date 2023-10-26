import { useMancalaGame } from "../contexts/StrategoGameContext";
import GameGrid from '../components/GameGrid';
import { useState, useEffect } from "react";
import '../modal.css';

export const Play = () => {
    const { gameState, setGameState } = useMancalaGame();

    const imageMapping: Record<string, string> = {
        "marshalThatHasTurn": "/marshal.png",
        "pieceWithoutTurn": "/tower.png",
        "noPiece": "/grass.png",
        "flagThatHasTurn": "/flag.png",
        "spyThatHasTurn": "/spy.png",
        "scoutThatHasTurn": "/scout.png",
        "bombThatHasTurn": "/bomb.png",
        "minerThatHasTurn": "/miner.png"
    };

    const [isPlayer1PopupVisible, setPlayer1PopupVisible] = useState(true);
    const [isPlayer2PopupVisible, setPlayer2PopupVisible] = useState(false);
    const [gameIsOver, setGameIsOver] = useState(false);
    const [isEndOfGamePopupVisible, setEndOfGamePopupVisible] = useState(false);


    

    async function playGame(xFromSquare: number, yFromSquare: number, xToSquare: number, yToSquare: number) {
        // Check which player has the turn
        if (gameState?.players[1].hasTurn) {
            // Adjust coordinates for player 2's perspective
            xFromSquare = 11 - xFromSquare;
            yFromSquare = 11 - yFromSquare;
            xToSquare = 11 - xToSquare;
            yToSquare = 11 - yToSquare;
        }
    
        const response = await fetch("stratego/api/play", {
            method: "POST",
            headers: {
                Accept: "application/json",
                "Content-Type": "application/json",
            },
            body: JSON.stringify({
                xFromSquare: xFromSquare,
                yFromSquare: yFromSquare,
                xToSquare: xToSquare,
                yToSquare: yToSquare,
            }),
        });
    
        if (response.ok) {
            const updatedGameState = await response.json();
            setGameState(updatedGameState); // Update the game state only if the response is successful
    
            // Update the popups based on the updated game state
            if (updatedGameState.players[0].hasTurn) {
                setPlayer1PopupVisible(true);
                setPlayer2PopupVisible(false); // Ensure the other player's popup is hidden
            } else if (updatedGameState.players[1].hasTurn) {
                setPlayer2PopupVisible(true);
                setPlayer1PopupVisible(false); // Ensure the other player's popup is hidden
            }
        } else {
            return {
                statusCode: response.status,
                statusText: response.statusText
            };
        }
    }

    useEffect(() => {
        if (gameState && gameState?.gameStatus.endOfGame && !gameState.players[0].hasTurn && !gameState.players[1].hasTurn) {
            setGameIsOver(true);
            setEndOfGamePopupVisible(true);
        } else {
            setGameIsOver(false);
        }
    }, [gameState]);

    return (
        <>
            {isPlayer1PopupVisible && isEndOfGamePopupVisible == false && (
                <div className="modal">
                    <div className="modal-content">
                        <h2>{gameState?.players[0].name} ben je klaar voor je beurt?</h2>
                        <button onClick={() => setPlayer1PopupVisible(false)}>Ja!</button>
                    </div>
                </div>
            )}

            {isPlayer2PopupVisible && isEndOfGamePopupVisible == false && (
                <div className="modal">
                    <div className="modal-content">
                        <h2>{gameState?.players[1].name} ben je klaar voor je beurt?</h2>
                        <button onClick={() => setPlayer2PopupVisible(false)}>Ja!</button>
                    </div>
                </div>
            )}

            {isEndOfGamePopupVisible && (
                <div className="modal">
                    <div className="modal-content">
                        <h2>Game Over! The winner is: {gameState?.gameStatus.winnerName}</h2>
                        <button onClick={() => setEndOfGamePopupVisible(false)}>OK</button>
                    </div>
                </div>
            )}

            <div style={{ maxWidth: '100%', padding: '0 16px', margin: '0 auto' }}>
                <div style={{ width: '100%', maxWidth: '700px', margin: '0 auto' }}>
                    <GameGrid gameState={gameState} imageMapping={imageMapping} onImageClick={(x1, y1, x2, y2) => playGame(x1+1, y1+1, x2+1, y2+1)} />
                </div>
            </div>



            <div>
                Player 1: {gameState?.players[0].name}  Player 2: {gameState?.players[1].name}
            </div>
        </>
    );
};