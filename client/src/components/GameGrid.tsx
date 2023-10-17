import React from 'react';
import { GameState } from '../types';

interface GameGridProps {
  gameState?: GameState;
  imageMapping: Record<string, string>;
}

const GameGrid: React.FC<GameGridProps> = ({ gameState, imageMapping }) => {
  const gridRows = gameState?.board.squares;

  if (gridRows) {
    const numRows = gridRows.length;
    const numCols = gridRows[0].length; // Assuming all rows have the same number of columns

    const gridImages = [];

    for (let x = 0; x < numCols; x++) {
      const colImages = [];

      for (let y = 0; y < numRows; y++) {
        const cell = gridRows[y][x]; // Swap the indices
        const pieceName = cell.piece.name;
        let imageUrl;

        if (pieceName === "marshal") {
          imageUrl = imageMapping["marshalThatHasTurn"];
        } else if (pieceName == null) {
          imageUrl = imageMapping["noPiece"];
        } else if (pieceName === "flag") {
          imageUrl = imageMapping["flagThatHasTurn"];
        }

        colImages.push(
          <td key={y}>
            <img src={imageUrl} alt={`Square ${x}-${y}`} />
          </td>
        );
      }

      gridImages.push(
        <tr key={x}>
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
    return <div>Gamestate does not exist...</div>;
  }
}

export default GameGrid;