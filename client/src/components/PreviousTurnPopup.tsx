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
      <h2>Er heeft een gevecht plaatsgevonden!</h2>
      <p>Stuk dat gewonnen heeft: {previousTurnWonPiece}</p>
      <p>Gesneuveld(e) stuk(ken): {previousTurnLostPiece}</p>
      <button onClick={onClose}>Close</button>
    </div>
  );
};

export default PreviousTurnPopup;