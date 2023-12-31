import React, { useState, useEffect } from 'react';
import { GameState } from '../types';

interface GameGridProps {
  gameState?: GameState;
  imageMapping: Record<string, string>;
  onImageClick: (x1: number, y1: number, x2: number, y2: number) => void;
}

const GameGrid: React.FC<GameGridProps> = ({ gameState, imageMapping, onImageClick }) => {
  const gridRows = gameState?.board.squares;
  const [firstClick, setFirstClick] = useState<{ x: number; y: number } | null>(null);
  const [flipBoard, setFlipBoard] = useState(false); // Track whether the board is flipped

  const handleImageClick = (x: number, y: number) => {
    if (!gridRows) {
      // Handle the case when gridRows is undefined
      return;
    }
  
    if (gameState && gameState.players[0].hasTurn) {
      // For player 1, handle orientation based on the flipBoard state
      if (flipBoard) {
        x = gridRows.length - x - 1;
        y = gridRows[0].length - y - 1;
      }
    } else if (gameState && gameState.players[1].hasTurn) {
      // For player 2, keep the same orientation as player 1
      if (flipBoard) {
        x = x;
        y = y;
      }
    }
  
    if (firstClick) {
      onImageClick(firstClick.x, firstClick.y, x, y);
      setFirstClick(null);
    } else {
      setFirstClick({ x, y });
    }
  };
  
  
  useEffect(() => {
    // Toggle the flipBoard state when the players switch turns (after a move)
    if (gameState && gameState.players[0].hasTurn !== gameState.players[1].hasTurn) {
      setFlipBoard((prevState) => !prevState);
    }
  }, [gameState]);

  if (gridRows) {
    const numRows = gridRows.length;
    const numCols = gridRows[0].length;

    const gridImages = [];

    for (let y = 0; y < numCols; y++) {
      const colImages = [];

      for (let x = 0; x < numRows; x++) {
        const cell = flipBoard
          ? gridRows[numRows - x - 1][numCols - y - 1] // Reverse both rows and columns when board is flipped
          : gridRows[x][y];

        const lastMoveFrom = cell.lastMoveFrom;
        const lastMove = cell.lastMove;
        const pieceName = cell.piece.name;
        const isWater = cell.water;

        let imageUrl;

        if (isWater) {
          imageUrl = imageMapping['water'];
        } else {
          if (gameState?.gameStatus.endOfGame) {
            // Game has ended, ignore 'hasTurn'
            if (pieceName === "marshal") {
              imageUrl = imageMapping["marshalThatHasTurn"];
            } else if (pieceName === "flag") {
              imageUrl = imageMapping["flagThatHasTurn"];
            } else if (pieceName === 'spy') {
              imageUrl = imageMapping["spyThatHasTurn"];
            } else if (pieceName === "scout") {
              imageUrl = imageMapping["scoutThatHasTurn"];
            } else if (pieceName === "miner") {
              imageUrl = imageMapping["minerThatHasTurn"];
            } else if (pieceName === "bomb") {
              imageUrl = imageMapping["bombThatHasTurn"];

            } else if (pieceName === "sergeant") {
              imageUrl = imageMapping["sergeantThatHasTurn"];
            } else if (pieceName === "lieutenant") {
              imageUrl = imageMapping["lieutenantThatHasTurn"];
            } else if (pieceName === "captain") {
              imageUrl = imageMapping["captainThatHasTurn"];
            } else if (pieceName === "major") {
              imageUrl = imageMapping["majorThatHasTurn"];
            } else if (pieceName === "colonel") {
              imageUrl = imageMapping["colonelThatHasTurn"];
            } else if (pieceName === "general") {
              imageUrl = imageMapping["generalThatHasTurn"];


            } else if (pieceName == null) {
              imageUrl = imageMapping["noPiece"];
            } else {
              imageUrl = imageMapping["pieceWithoutTurn"];
            }
          } else {
            // Game is ongoing, consider 'hasTurn'
            const hasTurn = cell.piece.hasTurn;
            if (pieceName === "marshal" && hasTurn === true) {
              imageUrl = imageMapping["marshalThatHasTurn"];
            } else if (pieceName === "flag" && hasTurn === true) {
              imageUrl = imageMapping["flagThatHasTurn"];
            } else if (pieceName === 'spy' && hasTurn == true) {
              imageUrl = imageMapping["spyThatHasTurn"];
            } else if (pieceName === "scout" && hasTurn == true) {
              imageUrl = imageMapping["scoutThatHasTurn"];
            } else if (pieceName === "miner" && hasTurn == true) {
              imageUrl = imageMapping["minerThatHasTurn"];
            } else if (pieceName === "bomb" && hasTurn == true) {
              imageUrl = imageMapping["bombThatHasTurn"];

            } else if (pieceName === "sergeant" && hasTurn == true) {
              imageUrl = imageMapping["sergeantThatHasTurn"];
            } else if (pieceName === "lieutenant" && hasTurn == true) {
              imageUrl = imageMapping["lieutenantThatHasTurn"];
            } else if (pieceName === "captain" && hasTurn == true) {
              imageUrl = imageMapping["captainThatHasTurn"];
            } else if (pieceName === "major" && hasTurn == true) {
              imageUrl = imageMapping["majorThatHasTurn"];
            } else if (pieceName === "colonel" && hasTurn == true) {
              imageUrl = imageMapping["colonelThatHasTurn"];
            } else if (pieceName === "general" && hasTurn == true) {
              imageUrl = imageMapping["generalThatHasTurn"];


            } else if (pieceName == null) {
              imageUrl = imageMapping["noPiece"];
            } else if (pieceName != null && hasTurn === false) {
              imageUrl = imageMapping["pieceWithoutTurn"];
            }
          }
        }

        const tdStyle = {
          backgroundColor: firstClick && firstClick.x === x && firstClick.y === y ? 'black' : 'transparent',
          border: '1px solid black', // Default border for all cells
        };
        
        if (lastMoveFrom || lastMove) {
          // Determine the color of the border based on the player who made the last move
          tdStyle.border = gameState.players[0].hasTurn ? '5px solid blue': '5px solid red';
        } else {
          if (cell.piece.playerId === 1) {
            tdStyle.border = '2px solid red';
          } else if (cell.piece.playerId === 2) {
            tdStyle.border = '2px solid blue';
          }
        }
        const imgStyle = {
          backgroundColor: cell.piece.playerId === 1 ? 'red' : 'lightblue', // Set the background color based on the player's ID
        };



colImages.push(
  <td key={x} onClick={() => handleImageClick(x, y)} style={tdStyle}>
    <img src={imageUrl} alt={`Square ${y}-${x}`} style={imgStyle} />
  </td>
);
      }

      gridImages.push(
        <tr key={y}>
          {colImages}
        </tr>
      );
    }

    return (
      <table>
        <tbody>{gridImages}</tbody>
      </table>
    );
  } else {
    return <div>Game state does not exist...</div>;
  }
};

export default GameGrid;
