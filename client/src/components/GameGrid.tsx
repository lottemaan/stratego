// GameGrid.tsx
import React from 'react';
import { GameState } from '../types';


interface GameGridProps {
  gameState?: GameState; // Define the GameState type
  imageMapping: Record<string, string>;
}

const GameGrid: React.FC<GameGridProps> = ({ gameState, imageMapping }) => {
  // Assuming you have gameState and imageMapping available
  const gridRows = gameState?.board.squares;

  // Check if gridRows is defined before rendering the grid
  if (gridRows) {
    const gridImages = gridRows.map((row, rowIndex) => (
      <tr key={rowIndex}>
        {row.map((cell, colIndex) => {
          const pieceName = cell.piece.name;
          // const hasTurn = cell.piece.player.HasTurn;
          let imageUrl;

          if (pieceName === "marshal") {
            imageUrl = imageMapping["marshalThatHasTurn"];
          } else if (pieceName == null) {
            imageUrl = imageMapping["noPiece"];
          }

          return (
            <td key={colIndex}>
              <img src={imageUrl} alt={`Square ${rowIndex}-${colIndex}`} />
            </td>
          );
        })}
      </tr>
    ));

    return (
      <table>
        <tbody>{gridImages}</tbody>
      </table>
    );
  } else {
    // Handle the case where gridRows is undefined (e.g., display a loading message)
    return <div>Gamestate does not exist...</div>;
  }
}

export default GameGrid;