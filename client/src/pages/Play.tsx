import { useMancalaGame } from "../contexts/StrategoGameContext";
import GameGrid from '../components/GameGrid';
import { useState, useEffect } from "react";
import '../modal.css';
import '../styles.css';
import PreviousTurnPopup from "../components/PreviousTurnPopup";

export const Play = () => {
    const { gameState, setGameState } = useMancalaGame();

    const imageMapping: Record<string, string> = {
        "marshalThatHasTurn": "/marshal.png",

        "sergeantThatHasTurn": "/sergeant.png",
        "lieutenantThatHasTurn": "/lieutenant.png",
        "captainThatHasTurn": "/captain.png",
        "majorThatHasTurn": "/major.png",
        "colonelThatHasTurn": "/colonel.png",
        "generalThatHasTurn": "/general.png",    

        "flagThatHasTurn": "/flag.png",
        "spyThatHasTurn": "/spy.png",
        "scoutThatHasTurn": "/scout.png",
        "bombThatHasTurn": "/bomb.png",
        "minerThatHasTurn": "/miner.png",
        "pieceWithoutTurn": "/vraagteken.png",
        "noPiece": "/grass.png",
        "water": "/water.png"
    };

    const [isPlayer1PopupVisible, setPlayer1PopupVisible] = useState(true);
    const [isPlayer2PopupVisible, setPlayer2PopupVisible] = useState(false);
    const [gameIsOver, setGameIsOver] = useState(false);
    const [isEndOfGamePopupVisible, setEndOfGamePopupVisible] = useState(false);
    const [showPreviousTurnPopup, setShowPreviousTurnPopup] = useState(false);
    const [previousTurnWonPiece, setPreviousTurnWonPiece] = useState(''); // Initialize with an empty string
    const [previousTurnLostPiece, setPreviousTurnLostPiece] = useState(''); // Initialize with an empty string

    const closePreviousTurnPopup = () => {
        setShowPreviousTurnPopup(false);
    };

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
                setShowPreviousTurnPopup(true);
                setPlayer1PopupVisible(true);
                setPlayer2PopupVisible(false); // Ensure the other player's popup is hidden
            } else if (updatedGameState.players[1].hasTurn) {
                setShowPreviousTurnPopup(true);
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
            {showPreviousTurnPopup && isEndOfGamePopupVisible === false && gameState?.gameStatus.gameBegun && (gameState?.board?.previousTurnWonPiece !== null || gameState?.board?.previousTurnLostPiece !== null) && (
                <div className="modal">
                    <div className="modal-content">
                        <PreviousTurnPopup
                            previousTurnWonPiece={gameState?.board?.previousTurnWonPiece || "geen"}
                            previousTurnLostPiece={gameState?.board?.previousTurnLostPiece || " "}
                            onClose={closePreviousTurnPopup}
                        />
                    </div>
                </div>
            )}

            {isPlayer1PopupVisible && isEndOfGamePopupVisible === false && !showPreviousTurnPopup && (
                <div className="modal">
                    <div className="modal-content">
                        <h2>{gameState?.players[0].name} ben je klaar voor je beurt?</h2>
                        <button onClick={() => {
                            setPlayer1PopupVisible(false);
                            setShowPreviousTurnPopup(true);
                        }}>Ja!</button>
                    </div>
                </div>
            )}

            {isPlayer2PopupVisible && isEndOfGamePopupVisible === false && !showPreviousTurnPopup && (
                <div className="modal">
                    <div className="modal-content">
                        <h2>{gameState?.players[1].name} ben je klaar voor je beurt?</h2>
                        <button onClick={() => {
                            setPlayer2PopupVisible(false);
                            setShowPreviousTurnPopup(true);
                        }}>Ja!</button>
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

<div style={{ display: 'flex', justifyContent: 'space-between' }}>
  <div style={{ flex: 1 }}>
    {/* geslagen stukken */}
  </div>
  <div style={{ flex: 2 }}>
    <div style={{ maxWidth: '100%', padding: '0 16px', margin: '0 auto' }}>
      <div style={{ width: '100%', maxWidth: '700px', margin: '0 auto', border: '5px solid black', marginTop: '30px' }}>
        <GameGrid gameState={gameState} imageMapping={imageMapping} onImageClick={(x1, y1, x2, y2) => playGame(x1 + 1, y1 + 1, x2 + 1, y2 + 1)} />
      </div>
      <div className="centered-text">
                het is jouw beurt {gameState!.players[0]!.hasTurn ? gameState!.players[0]!.name : gameState!.players[1]!.name}!
            </div>
    </div>
  </div>
  <div style={{ flex: 1 }}>
    {/* geslagen stukken */}
  </div>
</div>


        </>
    );
};






