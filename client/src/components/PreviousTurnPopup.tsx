import React from 'react';

interface PreviousTurnPopupProps {
  previousTurnWonPiece: string;
  previousTurnLostPiecePlayer1: string | null;
  previousTurnLostPiecePlayer2: string | null;
  namePlayerOne: string;
  namePlayerTwo: string;
  onClose: () => void;
}

const PreviousTurnPopup: React.FC<PreviousTurnPopupProps> = ({
  previousTurnWonPiece,
  previousTurnLostPiecePlayer1,
  previousTurnLostPiecePlayer2,
  namePlayerOne,
  namePlayerTwo,
  onClose,
}) => {
  let message = '';

  if (previousTurnLostPiecePlayer1 && previousTurnLostPiecePlayer2) {
    message = `Er heeft een gevecht plaatsgevonden! Helaas zijn jullie beide stukken gesneuveld (${previousTurnLostPiecePlayer1}).`;
  } else if (previousTurnLostPiecePlayer1 === null && previousTurnLostPiecePlayer2) {
    message = `Er heeft een gevecht plaatsgevonden! ${namePlayerOne}s ${previousTurnWonPiece} heeft gewonnen en ${namePlayerTwo}s ${previousTurnLostPiecePlayer2} heeft verloren.`;
  } else if (previousTurnLostPiecePlayer2 === null && previousTurnLostPiecePlayer1) {
    message = `Er heeft een gevecht plaatsgevonden! ${namePlayerTwo}s ${previousTurnWonPiece} heeft gewonnen en ${namePlayerOne}s ${previousTurnLostPiecePlayer1} heeft verloren.`;
  }

  return (
    <div className="previous-turn-popup">
      <h2>Er heeft een gevecht plaatsgevonden!</h2>
      <p>{message}</p>
      <button onClick={onClose}>Close</button>
    </div>
  );
};

export default PreviousTurnPopup;