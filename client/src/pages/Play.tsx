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



    const closePreviousTurnPopup = () => {
        setShowPreviousTurnPopup(false);
    };

    const capturedPiecesFromBackend = [
        ['scout', 1],
        ['marshal', 2],
        // ... other pairs
      ];

    // Create an empty Map
    const capturedPiecesMap = new Map();

    // Iterate over the array and add each pair to the map
    capturedPiecesFromBackend.forEach(([pieceType, count]) => {
    capturedPiecesMap.set(pieceType, count);
    });

      

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


            <div style={{ backgroundColor: 'lightgrey', padding: '5px', textAlign: 'center', borderBottom: '3px solid black', borderTop: '3px solid black' }}>
                <h1 style={{ fontSize: '3em', fontWeight: 'bold' }}>S  &emsp; &emsp; T &emsp; &emsp;  R  &emsp; &emsp; A &emsp; &emsp;  T &emsp; &emsp;  E &emsp; &emsp;  G  &emsp; &emsp; O</h1>
            </div>

            <div style={{ display: 'flex', backgroundColor: "rgba(0, 128, 0, 0.2)" }}>
                <div style={{ flex: 1, padding: '0', maxWidth: '25%', display: 'flex', flexDirection: 'column', backgroundColor: "rgba(0, 128, 0, 0.5)", borderRight: '3px solid black' }}>
                    <div style={{ backgroundColor: 'darkgreen', padding: '5px', textAlign: 'center', borderBottom: '3px solid black', fontSize: '2em', fontWeight: 'bold' }}>
                        <h1 style={{ color: 'white' }}>legenda</h1>
                    </div>
                    <ul style={{ marginTop: '20px', marginLeft: '5px' }}>
                        {[
                            { imgSrc: '/flag.png', text: 'VLAG (1)' },
                            { imgSrc: '/marshal.png', text: 'MAARSCHALK #1 (1) - zwakte: spion' },
                            { imgSrc: '/general.png', text: 'GENERAAL #2 (1)' },
                            { imgSrc: '/colonel.png', text: 'KOLONEL #3 (2)' },
                            { imgSrc: '/major.png', text: 'MAJOOR #4 (3)' },
                            { imgSrc: '/captain.png', text: 'KAPITEIN #5 (4)' },
                            { imgSrc: '/lieutenant.png', text: 'LUITENANT #6 (4)' },
                            { imgSrc: '/sergeant.png', text: 'SERGEANT #7 (4)' },
                            { imgSrc: '/miner.png', text: 'MINEUR #8 (5) - kan bom ontmantelen' },
                            { imgSrc: '/scout.png', text: 'VERKENNER #9 (8) - kan meerdere stappen zetten' },
                            { imgSrc: '/spy.png', text: 'SPION #10 (1) - kan maarschalk verslaan' },
                            { imgSrc: '/bomb.png', text: 'BOM (6) - zwakte: mineur' },
                        ].map((item, index) => (
                            <li key={index} style={{ display: 'flex', alignItems: 'center', marginBottom: '10px' }}>
                                <img src={item.imgSrc} alt={item.text} style={{ width: '50px', height: '50px' }} />
                                <span style={{ marginLeft: '10px', fontWeight: 'bold' }}>{item.text}</span>
                            </li>
                        ))}
                    </ul>
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
                <div style={{ flex: 1, maxWidth: '25%', display: 'flex', flexDirection: 'column', backgroundColor: "rgba(0, 128, 0, 0.5)", borderLeft: '3px solid black' }}>
                    <div style={{ backgroundColor: 'darkgreen', padding: '5px', textAlign: 'center', borderBottom: '3px solid black', fontSize: '2em', fontWeight: 'bold' }}>
                        <h1 style={{ color: 'white' }}>geslagen stukken</h1>
                    </div>
                    <ul style={{ marginTop: '20px', marginLeft: '5px' }}>
                    {Array.from(capturedPiecesMap.entries()).map(([pieceType, count]) => (
                            <li key={pieceType} style={{ display: 'flex', alignItems: 'center', marginBottom: '10px' }}>
                                <img src={`/${pieceType}.png`} alt={pieceType} style={{ width: '50px', height: '50px' }} />
                                <span style={{ marginLeft: '10px', fontWeight: 'bold' }}>{pieceType} ({count})</span>
                            </li>
                        ))}
                    </ul>
                </div>
            </div>



        </>
    );
};






