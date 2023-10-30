import React from 'react';

interface PreviousTurnPopupProps {
  previousTurnWonPiece: string;
  previousTurnLostPiece: string;
  onClose: () => void;
}

const PreviousTurnPopup: React.FC<PreviousTurnPopupProps> = ({
  previousTurnWonPiece,
  previousTurnLostPiece,
  onClose,
}) => {
  return (
    <div className="previous-turn-popup">
      <h2>Previous Turn Info</h2>
      <p>Won Piece: {previousTurnWonPiece}</p>
      <p>Lost Piece: {previousTurnLostPiece}</p>
      <button onClick={onClose}>Close</button>
    </div>
  );
};

export default PreviousTurnPopup;