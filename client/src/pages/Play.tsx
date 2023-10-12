import { useMancalaGame } from "../contexts/StrategoGameContext";

import { useState } from "react";
import { playGame, startGame } from "../services/api";
import { GameState, isGameState } from "../types";
import { FloatingInput } from "../components/FloatingInput";
import classNames from "classnames";
import { Alert } from "../components/Alert";

export const Play = () => {
    const { gameState, setGameState } = useMancalaGame();

    async function playGame(index: number) {
        const response = await fetch("stratego/api/play", {
            method: "POST",
            headers: {
                Accept: "application/json",
                "Content-Type": "application/json",
            },
            body: JSON.stringify({
                index: index,
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
        {gameState?.players[0].hasTurn && (<p>turn is: {gameState?.players[0].name}</p>)}<br />
        {gameState?.players[1].hasTurn && (<p>turn is: {gameState?.players[1].name}</p>)}<br />
        {gameState?.gameStatus.endOfGame && (<p>winner is: {gameState?.gameStatus.winner}</p>)}<br />


        
    </div><table>

            <tr>
                <th>KP2<br />{gameState?.players[1].pits[6].nrOfStones}</th>
                <td>B6P2<br />{gameState?.players[1].pits[5].nrOfStones}</td>
                <td>B5P2<br />{gameState?.players[1].pits[4].nrOfStones}</td>
                <td>B4P2<br />{gameState?.players[1].pits[3].nrOfStones}</td>
                <td>B3P2<br />{gameState?.players[1].pits[2].nrOfStones}</td>
                <td>B2P2<br />{gameState?.players[1].pits[1].nrOfStones}</td>
                <td>B1P2<br />{gameState?.players[1].pits[0].nrOfStones}</td>
            </tr>

            <tr>
                <td><br /></td>
                <td>B1P1<br />{gameState?.players[0].pits[0].nrOfStones}</td>
                <td>B2P1<br />{gameState?.players[0].pits[1].nrOfStones}</td>
                <td>B3P1<br />{gameState?.players[0].pits[2].nrOfStones}</td>
                <td>B4P1<br />{gameState?.players[0].pits[3].nrOfStones}</td>
                <td>B5P1<br />{gameState?.players[0].pits[4].nrOfStones}</td>
                <td>B6P1<br />{gameState?.players[0].pits[5].nrOfStones}</td>
                <th>KP1<br />{gameState?.players[0].pits[6].nrOfStones}</th>
            </tr>

        </table><button
            type="button"
            onClick={() => playGame(3)}
        >
            Play
        </button></>


};