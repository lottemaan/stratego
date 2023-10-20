import { useMancalaGame } from "../contexts/StrategoGameContext";
import GameGrid from '../components/GameGrid';
import { useState, useEffect } from "react";
import '../modal.css';

export const Play = () => {
    const { gameState, setGameState } = useMancalaGame();

    const imageMapping: Record<string, string> = {
        "marshalThatHasTurn": "/tower.png",
        "noPiece": "/grass.png",
        "flagThatHasTurn": "/flag.png"
    };

    const [isPlayer1PopupVisible, setPlayer1PopupVisible] = useState(false);
    const [isPlayer2PopupVisible, setPlayer2PopupVisible] = useState(false);
    const [isEndOfTurnPlayer1PopupVisible, setEndOfTurnPlayer1PopupVisible] = useState(false);
    const [isEndOfTurnPlayer2PopupVisible, setEndOfTurnPlayer2PopupVisible] = useState(false);

    useEffect(() => {
        if (gameState && gameState.players[0].hasTurn === true) {
            setPlayer1PopupVisible(true);
        } else {
            setPlayer1PopupVisible(false);
        }
    }, [gameState]);

    useEffect(() => { 
        if (gameState && gameState.players[1].hasTurn === true) {
            setPlayer2PopupVisible(true);
        } else {
            setPlayer2PopupVisible(false);
        }
    }, [gameState]);

    useEffect(() => {
        if (gameState && gameState.players[0].hasTurn === false) {
            setEndOfTurnPlayer1PopupVisible(true);
        } else {
            setEndOfTurnPlayer1PopupVisible(false);
        }
    }, [gameState]);

    useEffect(() => {
        if (gameState && gameState.players[1].hasTurn === false) {
            setEndOfTurnPlayer2PopupVisible(true);
        } else {
            setEndOfTurnPlayer2PopupVisible(false);
        }
    }, [gameState]);


    async function playGame(xFromSquare: number, yFromSquare: number, xToSquare: number, yToSquare: number) {
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
                yToSquare: yToSquare
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

    return (
        <>
            <div>
                Player 1: {gameState?.players[0].name} Player 2: {gameState?.players[1].name}
            </div>

            {isPlayer1PopupVisible && (
                <div className="modal">
                    <div className="modal-content">
                        <h2>Speler 1 ben je klaar voor je beurt?</h2>
                        <button onClick={() => setPlayer1PopupVisible(false)}>Ja!</button>
                    </div>
                </div>
            )}

            {isPlayer2PopupVisible && (
                <div className="modal">
                    <div className="modal-content">
                        <h2>Speler 2 ben je klaar voor je beurt?</h2>
                        <button onClick={() => setPlayer2PopupVisible(false)}>Ja!</button>
                    </div>
                </div>
            )}

            {isEndOfTurnPlayer1PopupVisible && (
                <div className="modal">
                    <div className="modal-content">
                        <h2>Speler 1, geef je beurt door aan speler 2!</h2>
                        <button onClick={() => setEndOfTurnPlayer1PopupVisible(false)}>Oké</button>
                    </div>
                </div>
            )}

            {isEndOfTurnPlayer2PopupVisible && (
                <div className="modal">
                    <div className="modal-content">
                        <h2>Speler 2, geef je beurt door aan speler 1!</h2>
                        <button onClick={() => setEndOfTurnPlayer2PopupVisible(false)}>Oké</button>
                    </div>
                </div>
            )}

            <div style={{ maxWidth: '100%', padding: '0 16px', margin: '0 auto' }}>
                <div style={{ width: '100%', maxWidth: '700px', margin: '0 auto' }}>
                    <GameGrid gameState={gameState} imageMapping={imageMapping} onImageClick={(x1, y1, x2, y2) => playGame(x1+1, y1+1, x2+1, y2+1)} />
                </div>
            </div>
        </>
    );
};