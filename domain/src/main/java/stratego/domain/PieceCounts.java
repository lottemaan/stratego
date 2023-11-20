package stratego.domain;

import java.util.HashMap;
import java.util.Map;

final class PieceCounts {
    private Map<String, Integer> maxAllowedCounts = new HashMap<>();
    private Map<String, Integer> playerPieceCounts1 = new HashMap<>();
    private Map<String, Integer> playerPieceCounts2 = new HashMap<>();

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

    protected boolean isValidPieceCount(String newPieceName, int playerId) {
        Map<String, Integer> playerPieceCounts = (playerId == 1) ? playerPieceCounts1 : playerPieceCounts2;
    
        if (playerPieceCounts.containsKey(newPieceName)) {
            int currentCount = playerPieceCounts.get(newPieceName);
            int maxAllowedCount = maxAllowedCounts.get(newPieceName);
            
            if (currentCount < maxAllowedCount) {
                playerPieceCounts.put(newPieceName, currentCount + 1);
                return true;
            } else {
                return false;
            }
        } else {
            playerPieceCounts.put(newPieceName, 1);
            return true;
        }
    }
}
