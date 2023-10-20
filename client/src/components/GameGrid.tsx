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
      onImageClick(firstClick.x, firstClick.y, x, y);
      setFirstClick(null);
    } else {
      setFirstClick({ x, y });
    }
  };

  if (gridRows) {
    const numRows = gridRows.length;
    const numCols = gridRows[0].length;

    const gridImages = [];

    for (let y = 0; y < numCols; y++) {
      const colImages = [];

      for (let x = 0; x < numRows; x++) {
        const cell = gridRows[x][y];
        const pieceName = cell.piece.name;
        const hasTurn = cell.piece.hasTurn;
        let imageUrl;

        if (pieceName === "marshal" && hasTurn === true) {
          imageUrl = imageMapping["marshalThatHasTurn"];
        } else if (pieceName === "flag" && hasTurn === true) {
          imageUrl = imageMapping["flagThatHasTurn"];

        } else if (pieceName == null) {
          imageUrl = imageMapping["noPiece"];

        } else if (pieceName != null && hasTurn == false) {
          imageUrl = imageMapping["pieceWithoutTurn"];
        }

        const tdStyle = {
          backgroundColor: firstClick && firstClick.x === x && firstClick.y === y ? 'red' : 'transparent', // Set background color to red for the selected cell
        };

        colImages.push(
          <td key={x} onClick={() => handleImageClick(x, y)} style={tdStyle}>
            <img src={imageUrl} alt={`Square ${y}-${x}`} />
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
