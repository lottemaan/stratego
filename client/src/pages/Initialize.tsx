import { useMancalaGame } from "../contexts/StrategoGameContext";
import InitializationGrid from '../components/InitializationGrid';
import { useState, useEffect } from "react";
import '../modal.css';
import '../styles.css';

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



const legendItems = [
    { namePiece: 'Flag', imgSrc: '/flag.png', text: 'VLAG (1)' },
    { namePiece: 'Marshal', imgSrc: '/marshal.png', text: 'MAARSCHALK #1 (1) - zwakte: spion' },
    { namePiece: 'General', imgSrc: '/general.png', text: 'GENERAAL #2 (1)' },
    { namePiece: 'Colonel', imgSrc: '/colonel.png', text: 'KOLONEL #3 (2)' },
    { namePiece: 'Major', imgSrc: '/major.png', text: 'MAJOOR #4 (3)' },
    { namePiece: 'Captain', imgSrc: '/captain.png', text: 'KAPITEIN #5 (4)' },
    { namePiece: 'Lieutenant', imgSrc: '/lieutenant.png', text: 'LUITENANT #6 (4)' },
    { namePiece: 'Sergeant', imgSrc: '/sergeant.png', text: 'SERGEANT #7 (4)' },
    { namePiece: 'Miner', imgSrc: '/miner.png', text: 'MINEUR #8 (5) - kan bom ontmantelen' },
    { namePiece: 'Scout', imgSrc: '/scout.png', text: 'VERKENNER #9 (8) - kan meerdere stappen zetten' },
    { namePiece: 'Spy', imgSrc: '/spy.png', text: 'SPION #10 (1) - kan maarschalk verslaan' },
    { namePiece: 'Bomb', imgSrc: '/bomb.png', text: 'BOM (6) - zwakte: mineur' },
];

export const Initialize = () => {
    const { gameState, setGameState } = useMancalaGame();
    const [currentPlayer, setCurrentPlayer] = useState(1);
    const [selectedPiece, setSelectedPiece] = useState<string | null>(null);

    const renderLegend = () => (
        <ul style={{ marginTop: '20px', marginLeft: '5px' }}>
            {legendItems.map((item, index) => (
                <li key={index} style={{ display: 'flex', alignItems: 'center', marginBottom: '10px', cursor: 'pointer' }}>
                    <img
                        src={item.imgSrc}
                        alt={item.text}
                        style={{ width: '50px', height: '50px' }}
                        onClick={() => handleLegendItemClick(item.namePiece)}
                    />
                    <span style={{ marginLeft: '10px', fontWeight: 'bold' }}>{item.text}</span>
                </li>
            ))}
        </ul>
    );

    const handleLegendItemClick = (namePiece: string) => {
        setSelectedPiece(namePiece);
    };

    const handleImageClick = (x: number, y: number) => {
        if (gameState?.board.playerOneReady) {
            setCurrentPlayer(2);
        }
        if (selectedPiece) {
            initializeGame(selectedPiece, x + 1, y + 1, currentPlayer);
        }
    };

    async function initializeGame(pieceName: string, xFromSquare: number, yFromSquare: number, playerId: number) {
        const response = await fetch("stratego/api/initialize", {
            method: "POST",
            headers: {
                Accept: "application/json",
                "Content-Type": "application/json",
            },
            body: JSON.stringify({
                pieceName: pieceName,
                xFromSquare: xFromSquare,
                yFromSquare: yFromSquare,
                playerId: playerId,
            }),
        });

        if (response.ok) {
            const updatedGameState = await response.json();
            setGameState(updatedGameState);

            if (updatedGameState.board.playerOneReady) {
                setCurrentPlayer(2);
            }
            
        } else {
            console.error("Error in network request:", response.statusText);
            console.log(gameState?.board.playerTwoReady);
            console.log(gameState?.board.playerOneReady);
        }
    }

    return (
        <>
        

            <div style={{ backgroundColor: 'lightgrey', padding: '5px', textAlign: 'center', borderBottom: '3px solid black', borderTop: '3px solid black' }}>
                <h1 style={{ fontSize: '3em', fontWeight: 'bold' }}>S  &emsp; &emsp; T &emsp; &emsp;  R  &emsp; &emsp; A &emsp; &emsp;  T &emsp; &emsp;  E &emsp; &emsp;  G  &emsp; &emsp; O</h1>
            </div>

            <div style={{ display: 'flex', backgroundColor: "rgba(0, 128, 0, 0.2)" }}>
                <div style={{ flex: 1, padding: '0', maxWidth: '25%', display: 'flex', flexDirection: 'column', backgroundColor: "rgba(0, 128, 0, 0.5)", borderRight: '3px solid black' }}>
                    <div style={{ backgroundColor: 'darkgreen', padding: '5px', textAlign: 'center', borderBottom: '3px solid black', fontSize: '2em', fontWeight: 'bold' }}>
                        <h1 style={{ color: 'white' }}>legenda</h1>
                    </div>
                    {renderLegend()}
                </div>
                <div style={{ flex: 2 }}>

                    <div style={{ maxWidth: '100%', padding: '0 16px', margin: '0 auto' }}>
                        <div style={{ width: '100%', maxWidth: '700px', margin: '0 auto', border: '5px solid black', marginTop: '30px' }}>
                            <InitializationGrid gameState={gameState} imageMapping={imageMapping} onImageClick={handleImageClick} />
                        </div>
                        <div className="centered-text">
                            het is jouw beurt {gameState!.players[0]!.hasTurn ? gameState!.players[0]!.name : gameState!.players[1]!.name}!
                        </div>
                    </div>
                </div>
                <div style={{ flex: 1, maxWidth: '25%', display: 'flex', flexDirection: 'column', backgroundColor: "rgba(0, 128, 0, 0.5)", borderLeft: '3px solid black' }}>
                    <div style={{ backgroundColor: 'darkgreen', padding: '5px', textAlign: 'center', borderBottom: '3px solid black', fontSize: '2em', fontWeight: 'bold' }}>
                        <h1 style={{ color: 'white' }}>stukken nog te plaatsen</h1>
                    </div>

                </div>
            </div>
        </>
    );
};





