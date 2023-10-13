import { useMancalaGame } from "../contexts/StrategoGameContext";
import { GameState, isGameState } from "../types";
import React from 'react';
import GameGrid from '../components/GameGrid';

export const Play = () => {
    const { gameState, setGameState } = useMancalaGame();

    const imageMapping: { [key: string]: string } = {
        'X': '/tower.png',
        'O': '/grass.png',
        // Add more mappings for other string values
      };

    const initialBoard: string[][] = [
        ['X', 'O', 'X', 'O', 'X'],
        ['O', 'X', 'O', 'X', 'O'],
        ['X', 'O', 'X', 'O', 'X'],
        ['O', 'X', 'O', 'X', 'O'],
        ['X', 'O', 'X', 'O', 'X'],
      ];
    
    // const containerStyle: React.CSSProperties = {
    //     width: '300px', // Set the width to your desired size
    //     height: '300px', // Set the height to your desired size
    //     margin: '0 auto', // Center the container horizontally (optional)
    //     border: '1px solid #ccc', // Add a border for visual separation (optional)
    //     display: 'flex', // Enable centering content vertically
    //     justifyContent: 'left', // Center content horizontally
    //     alignItems: 'left', // Center content vertically
    //   };

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

    return <>
    <h1>STRATEGO</h1>
        <div>
            Player 1: {gameState?.players[0].name}<br />
            Player 2: {gameState?.players[1].name}<br />
            
            {gameState?.gameStatus.endOfGame.toString()}
        </div>

        <div className="App">
      <h1>5x5 Game Grid</h1>
      <div className="game-container">
        <GameGrid board={initialBoard} imageMapping={imageMapping} />
      </div>
    </div>


        <table>

        <tr>
                <th>vak21<br />{gameState?.board.squares[0][2].xCoordinate}</th>
                <td><br />{gameState?.board.squares[0][2].yCoordinate}</td>
            </tr>
                <th>vak11<br />{gameState?.board.squares[0][1].xCoordinate}</th>
                <td><br />{gameState?.board.squares[0][1].yCoordinate}</td>
                <th>vak12<br />{gameState?.board.squares[1][1].xCoordinate}</th>
                <td><br />{gameState?.board.squares[1][1].yCoordinate}</td>
                <th>vak13<br />{gameState?.board.squares[2][1].xCoordinate}</th>
                <td><br />{gameState?.board.squares[2][1].yCoordinate}</td>
                <th>vak14<br />{gameState?.board.squares[3][1].xCoordinate}</th>
                <td><br />{gameState?.board.squares[3][1].yCoordinate}</td>
                <th>vak15<br />{gameState?.board.squares[4][1].xCoordinate}</th>
                <td><br />{gameState?.board.squares[4][1].yCoordinate}</td>
                <th>vak16<br />{gameState?.board.squares[5][1].xCoordinate}</th>
                <td><br />{gameState?.board.squares[5][1].yCoordinate}</td>
                <th>vak17<br />{gameState?.board.squares[6][1].xCoordinate}</th>
                <td><br />{gameState?.board.squares[6][1].yCoordinate}</td>
                <th>vak18<br />{gameState?.board.squares[7][1].xCoordinate}</th>
                <td><br />{gameState?.board.squares[7][1].yCoordinate}</td>
                <th>vak19<br />{gameState?.board.squares[8][1].xCoordinate}</th>
                <td><br />{gameState?.board.squares[8][1].yCoordinate}</td>
                <th>vak20<br />{gameState?.board.squares[9][1].xCoordinate}</th>
                <td><br />{gameState?.board.squares[9][1].yCoordinate}</td>


            <tr>
                <th>vak1<br />{gameState?.board.squares[0][0].xCoordinate}</th>
                <td><br />{gameState?.board.squares[0][0].yCoordinate}</td>
                <th>vak2<br />{gameState?.board.squares[1][0].xCoordinate}</th>
                <td><br />{gameState?.board.squares[1][0].yCoordinate}</td>
                <th>vak3<br />{gameState?.board.squares[2][0].xCoordinate}</th>
                <td><br />{gameState?.board.squares[2][0].yCoordinate}</td>
                <th>vak4<br />{gameState?.board.squares[3][0].xCoordinate}</th>
                <td><br />{gameState?.board.squares[3][0].yCoordinate}</td>
                <th>vak5<br />{gameState?.board.squares[4][0].xCoordinate}</th>
                <td><br />{gameState?.board.squares[4][0].yCoordinate}</td>
                <th>vak6<br />{gameState?.board.squares[5][0].xCoordinate}</th>
                <td><br />{gameState?.board.squares[5][0].yCoordinate}</td>
                <th>vak7<br />{gameState?.board.squares[6][0].xCoordinate}</th>
                <td><br />{gameState?.board.squares[6][0].yCoordinate}</td>
                <th>vak8<br />{gameState?.board.squares[7][0].xCoordinate}</th>
                <td><br />{gameState?.board.squares[7][0].yCoordinate}</td>
                <th>vak9<br />{gameState?.board.squares[8][0].xCoordinate}</th>
                <td><br />{gameState?.board.squares[8][0].yCoordinate}</td>
                <th>vak10<br />{gameState?.board.squares[9][0].xCoordinate}</th>
                <td><br />{gameState?.board.squares[9][0].yCoordinate}</td>

                
            </tr>

        </table>



            <button
                type="button"
                onClick={() => playGame(3, 2, 4, 5)}
            >
                Play
            </button></>
            

};