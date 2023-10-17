import { useMancalaGame } from "../contexts/StrategoGameContext";
import { GameState, isGameState } from "../types";
import React from 'react';
import GameGrid from '../components/GameGrid';

export const Play = () => {
    const { gameState, setGameState } = useMancalaGame();

    const imageMapping: Record<string, string> = {
        "marshalThatHasTurn": "/tower.png",
        "noPiece": "/grass.png",
        "flagThatHasTurn": "/flag.png"
    };

    async function playGame() {
        const response = await fetch("stratego/api/play", {
            method: "POST",
            headers: {
                Accept: "application/json",
                "Content-Type": "application/json",
            },
            body: JSON.stringify({

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

    return <>

        <div>
            Player 1: {gameState?.players[0].name} Player 2: {gameState?.players[1].name}

        </div>

        <div style={{ maxWidth: '100%', padding: '0 16px', margin: '0 auto' }}>

            <div style={{ width: '100%', maxWidth: '700px', margin: '0 auto' }}>
                <GameGrid gameState={gameState} imageMapping={imageMapping} />
            </div>
        </div>

        <button
            type="button"
            onClick={() => playGame()}
        >
            Play
        </button></>


};