import React, { useState, useEffect } from 'react';
import { GameState } from '../types';

interface InitializationGridProps {
  gameState?: GameState;
  imageMapping: Record<string, string>;
  onImageClick: (x: number, y: number) => void;
  playerOnePiecesSaved: boolean;
}

const InitializationGrid: React.FC<InitializationGridProps> = ({ gameState, imageMapping, onImageClick, playerOnePiecesSaved}) => {
  const gridRows = gameState?.board.squares;
  const [firstClick, setFirstClick] = useState<{ x: number; y: number } | null>(null);
  const [flipBoard, setFlipBoard] = useState(false);


  const handleImageClick = (x: number, y: number) => {
    if (!gridRows) {
      return;
    }

    if (gameState && gameState.board.playerOneReady && playerOnePiecesSaved) {
  
        x = gridRows.length - x - 1;
        y = gridRows[0].length - y - 1;
      
    } else if (gameState && !gameState.board.playerOneReady && !playerOnePiecesSaved) {

        x = x;
        y = y;
      
    }

    onImageClick(x, y);
  };

  useEffect(() => {
    // Set flipBoard to true when playerOneIsReady
    if (gameState && gameState.board.playerOneReady && playerOnePiecesSaved) {
      setFlipBoard(true);
    }
  }, [gameState, playerOnePiecesSaved]);

  useEffect(() => {
    console.log('playerOnePiecesSaved in InitializationGrid:', playerOnePiecesSaved);
  }, [playerOnePiecesSaved]);


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
    
        const pieceName = cell.piece.name;
        const isWater = cell.water;

        let imageUrl;

        if (isWater) {
          imageUrl = imageMapping['water'];
        } else {
          const hasTurn = cell.piece.hasTurn; 
        
          if (gameState?.board.playerOneReady && playerOnePiecesSaved) {
        
            if (pieceName === "marshal" && !hasTurn) {
              imageUrl = imageMapping["marshalThatHasTurn"];
            } else if (pieceName === "flag" && !hasTurn) {
              imageUrl = imageMapping["flagThatHasTurn"];
            } else if (pieceName === 'spy' && !hasTurn) {
              imageUrl = imageMapping["spyThatHasTurn"];
            } else if (pieceName === "scout" && !hasTurn) {
              imageUrl = imageMapping["scoutThatHasTurn"];
            } else if (pieceName === "miner" && !hasTurn) {
              imageUrl = imageMapping["minerThatHasTurn"];
            } else if (pieceName === "bomb" && !hasTurn) {
              imageUrl = imageMapping["bombThatHasTurn"];
            } else if (pieceName === "sergeant" && !hasTurn) {
              imageUrl = imageMapping["sergeantThatHasTurn"];
            } else if (pieceName === "lieutenant" && !hasTurn) {
              imageUrl = imageMapping["lieutenantThatHasTurn"];
            } else if (pieceName === "captain" && !hasTurn) {
              imageUrl = imageMapping["captainThatHasTurn"];
            } else if (pieceName === "major" && !hasTurn) {
              imageUrl = imageMapping["majorThatHasTurn"];
            } else if (pieceName === "colonel" && !hasTurn) {
              imageUrl = imageMapping["colonelThatHasTurn"];
            } else if (pieceName === "general" && !hasTurn) {
              imageUrl = imageMapping["generalThatHasTurn"];
            } else if (pieceName == null) {
            imageUrl = imageMapping["noPiece"];
          } else if (pieceName != null && hasTurn) {
            imageUrl = imageMapping["pieceWithoutTurn"];
          } 
        }else {
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
          border: '1px solid black', 
        };

        
        if (cell.piece.playerId === 1) {
          tdStyle.border = '2px solid red';
        } else if (cell.piece.playerId === 2) {
          tdStyle.border = '2px solid blue';
        }

        const imgStyle = {
          backgroundColor: cell.piece.playerId === 1 ? 'red' : 'lightblue', 
          
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

export default InitializationGrid;


