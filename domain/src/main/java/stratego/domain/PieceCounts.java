package stratego.domain;

import java.util.HashMap;
import java.util.Map;

final class PieceCounts {
    private Map<String, Integer> maxAllowedCounts = new HashMap<>();


    public PieceCounts() {
        maxAllowedCounts.put("Flag", 1);
        maxAllowedCounts.put("Marshal", 1);
        maxAllowedCounts.put("General", 1);
        maxAllowedCounts.put("Colonel", 2);
        maxAllowedCounts.put("Major", 3);
        maxAllowedCounts.put("Captain", 4);
        maxAllowedCounts.put("Lieutenant", 4);
        maxAllowedCounts.put("Sergeant", 4);
        maxAllowedCounts.put("Miner", 5);
        maxAllowedCounts.put("Scout", 8);
        maxAllowedCounts.put("Spy", 1);
        maxAllowedCounts.put("Bomb", 6);
    }

    protected boolean isValidPieceCount(String newPieceName, int playerId, Board board) {
        int countPlayer = countPiecesOfType(board, newPieceName, playerId);
        int maxAllowedCount = maxAllowedCounts.get(newPieceName);
            
            if (countPlayer < maxAllowedCount) {
                return true;
            } else {
                return false;
            }
    }

protected int countPiecesOfType(Board board, String pieceType, int playerId) {
    int count = 0;

    for (int i = 0; i < 10; i++) {
        for (int j = 0; j < 10; j++) {
            Piece piece = board.getSquare(i + 1, j + 1).getPieceFromSquare();

            if (piece != null && piece.getNamePiece().equalsIgnoreCase(pieceType)) {
                if ((playerId == 1 && j + 1 > 6) || (playerId == 2 && j + 1 < 5)) {
                    count++;
                }
            }
        }
    }

    return count;
}
}

