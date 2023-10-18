import React, { useState } from 'react';
import { GameState } from '../types';

interface GameGridProps {
  gameState?: GameState;
  imageMapping: Record<string, string>;
  onImageClick: (x1: number, y1: number, x2: number, y2: number) => void;
}

const GameGrid: React.FC<GameGridProps> = ({ gameState, imageMapping, onImageClick }) => {
  const gridRows = gameState?.board.squares;
  const [firstClick, setFirstClick] = useState<{ x: number, y: number } | null>(null);

  const handleImageClick = (x: number, y: number) => {
    if (firstClick) {
      onImageClick(firstClick.x, firstClick.y, x, y); // Pass the coordinates of both clicks
      setFirstClick(null); // Reset the firstClick
    } else {
      setFirstClick({ x, y }); // Store the first click
    }
  };

  if (gridRows) {
    const numRows = gridRows.length;
    const numCols = gridRows[0].length;

    const gridImages = [];

    for (let y = 0; y < numCols; y++) {
      const colImages = [];

      for (let x = 0; x < numRows; x++) {
        const cell = gridRows[x][y]; // Swap the indices
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
          <td key={x} onClick={() => handleImageClick(x, y)}> {/* Swap x and y in the onClick handler */}
            <img src={imageUrl} alt={`Square ${y}-${x}`} /> {/* Swap y and x in the alt text */}
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
    return <div>Gamestate does not exist...</div>;
  }
}

export default GameGrid;