// src/GameGrid.tsx
import React from 'react';

interface GameGridProps {
  board: string[][];
  imageMapping: { [key: string]: string };
}

const cellStyle: React.CSSProperties = {
  width: '100px', // Adjust to your desired square size
  height: '100px', // Adjust to your desired square size
  border: '2px solid #ccc', // Thicker borders
  textAlign: 'center',
};

const GameGrid: React.FC<GameGridProps> = ({ board, imageMapping }) => {
  return (
    <table className="game-grid">
      <tbody>
        {board.map((row, rowIndex) => (
          <tr key={rowIndex}>
            {row.map((cellValue, cellIndex) => (
              <td key={cellIndex} style={cellStyle}>
                <img
                  src={imageMapping[cellValue]}
                  alt={`Square ${rowIndex}-${cellIndex}`}
                  width="100%"
                  height="100%"
                />
              </td>
            ))}
          </tr>
        ))}
      </tbody>
    </table>
  );
};

export default GameGrid;


