package stratego.domain;

import java.util.Random;

final class BoardInitialization {

    Player player = new Player();
    Player opponent = player.getOpponent();
    Square[][] squares = new Square[10][10];

    protected Board initializeEmptyBoard() {

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                squares[i][j] = new Square(i + 1, j + 1);
    
                if ((i == 2 || i == 3 || i == 6 || i == 7) && (j == 4 || j == 5)) {
                    squares[i][j].turnInWater();
                }
            }
        }

        return new Board(squares, player, opponent);
    }

    
    protected void initializePiecesRandomly(Board emptyBoard) {
        Random random = new Random();
        int choice = random.nextInt(2);
        
        if (choice == 0) {
        initializeScript1(emptyBoard);
        } else {
            initializeScript2(emptyBoard);
        }
        emptyBoard.checkIfInitialized();
    }

    private void initializeScript1(Board emptyBoard) {
        emptyBoard.getSquare(4, 1).updatePiece(new Flag());
        emptyBoard.getSquare(1, 10).updatePiece(new Flag());
        emptyBoard.getSquare(5, 2).updatePiece(new Marshal());
        emptyBoard.getSquare(8, 10).updatePiece(new Marshal());
        emptyBoard.getSquare(3, 3).updatePiece(new Spy());
        emptyBoard.getSquare(8, 9).updatePiece(new Spy());
        emptyBoard.getSquare(3, 3).updatePiece(new General());
        emptyBoard.getSquare(8, 8).updatePiece(new General());

        emptyBoard.getSquare(1, 3).updatePiece(new Colonel());
        emptyBoard.getSquare(6, 2).updatePiece(new Colonel());
        emptyBoard.getSquare(2, 9).updatePiece(new Colonel());
        emptyBoard.getSquare(6, 9).updatePiece(new Colonel());

        emptyBoard.getSquare(3, 2).updatePiece(new Major());
        emptyBoard.getSquare(5, 1).updatePiece(new Major());
        emptyBoard.getSquare(7, 2).updatePiece(new Major());
        emptyBoard.getSquare(1, 9).updatePiece(new Major());
        emptyBoard.getSquare(3, 9).updatePiece(new Major());
        emptyBoard.getSquare(7, 9).updatePiece(new Major());

        emptyBoard.getSquare(8, 2).updatePiece(new Captain());
        emptyBoard.getSquare(4, 2).updatePiece(new Captain());
        emptyBoard.getSquare(9, 2).updatePiece(new Captain());
        emptyBoard.getSquare(10, 1).updatePiece(new Captain());
        emptyBoard.getSquare(4, 8).updatePiece(new Captain());
        emptyBoard.getSquare(10, 8).updatePiece(new Captain());
        emptyBoard.getSquare(7, 8).updatePiece(new Captain());
        emptyBoard.getSquare(5, 8).updatePiece(new Captain());

        emptyBoard.getSquare(2, 3).updatePiece(new Lieutenant());
        emptyBoard.getSquare(5, 3).updatePiece(new Lieutenant());
        emptyBoard.getSquare(7, 3).updatePiece(new Lieutenant());
        emptyBoard.getSquare(9, 3).updatePiece(new Lieutenant());
        emptyBoard.getSquare(3, 8).updatePiece(new Lieutenant());
        emptyBoard.getSquare(6, 8).updatePiece(new Lieutenant());
        emptyBoard.getSquare(9, 8).updatePiece(new Lieutenant());
        emptyBoard.getSquare(1, 8).updatePiece(new Lieutenant());

        emptyBoard.getSquare(4, 3).updatePiece(new Sergeant());
        emptyBoard.getSquare(6, 3).updatePiece(new Sergeant());
        emptyBoard.getSquare(8, 3).updatePiece(new Sergeant());
        emptyBoard.getSquare(10, 2).updatePiece(new Sergeant());
        emptyBoard.getSquare(4, 8).updatePiece(new Sergeant());
        emptyBoard.getSquare(4, 7).updatePiece(new Sergeant());
        emptyBoard.getSquare(7, 7).updatePiece(new Sergeant());
        emptyBoard.getSquare(8, 7).updatePiece(new Sergeant());

        emptyBoard.getSquare(1, 2).updatePiece(new Bomb());
        emptyBoard.getSquare(2, 2).updatePiece(new Bomb());
        emptyBoard.getSquare(2, 1).updatePiece(new Bomb());
        emptyBoard.getSquare(4, 4).updatePiece(new Bomb());
        emptyBoard.getSquare(5, 4).updatePiece(new Bomb());
        emptyBoard.getSquare(9, 1).updatePiece(new Bomb());

        emptyBoard.getSquare(9, 10).updatePiece(new Bomb());
        emptyBoard.getSquare(9, 9).updatePiece(new Bomb());
        emptyBoard.getSquare(10, 9).updatePiece(new Bomb());
        emptyBoard.getSquare(5, 10).updatePiece(new Bomb());
        emptyBoard.getSquare(5, 9).updatePiece(new Bomb());
        emptyBoard.getSquare(3, 10).updatePiece(new Bomb());

        emptyBoard.getSquare(7, 1).updatePiece(new Miner());
        emptyBoard.getSquare(8, 1).updatePiece(new Miner());
        emptyBoard.getSquare(1, 4).updatePiece(new Miner());
        emptyBoard.getSquare(3, 1).updatePiece(new Miner());
        emptyBoard.getSquare(6, 1).updatePiece(new Miner());

        emptyBoard.getSquare(2, 8).updatePiece(new Miner());
        emptyBoard.getSquare(2, 10).updatePiece(new Miner());
        emptyBoard.getSquare(4, 10).updatePiece(new Miner());
        emptyBoard.getSquare(6, 10).updatePiece(new Miner());
        emptyBoard.getSquare(7, 10).updatePiece(new Miner());




        for (int i = 1; i < 11; i++) {
            for (int j = 1; j < 11; j++) {
                if (j < 5 || j > 6){
                    if (emptyBoard.getSquare(i, j).getPieceFromSquare() == null) {
                        emptyBoard.getSquare(i, j).updatePiece(new Scout());
                    } 
                    if (j < 5) {
                        emptyBoard.getSquare(i, j).getPieceFromSquare().assignPlayer(opponent);
                    } else if (j > 6) {
                        emptyBoard.getSquare(i, j).getPieceFromSquare().assignPlayer(player);
                    } 
                } 
            }
        }
    }
    
    
    private void initializeScript2(Board emptyBoard) {
        emptyBoard.getSquare(4, 1).updatePiece(new Flag());
        emptyBoard.getSquare(1, 10).updatePiece(new Flag());
        emptyBoard.getSquare(5, 2).updatePiece(new Marshal());
        emptyBoard.getSquare(8, 10).updatePiece(new Marshal());
        emptyBoard.getSquare(3, 3).updatePiece(new Spy());
        emptyBoard.getSquare(8, 9).updatePiece(new Spy());
        emptyBoard.getSquare(3, 3).updatePiece(new General());
        emptyBoard.getSquare(8, 8).updatePiece(new General());

        emptyBoard.getSquare(1, 3).updatePiece(new Colonel());
        emptyBoard.getSquare(6, 2).updatePiece(new Colonel());
        emptyBoard.getSquare(2, 9).updatePiece(new Colonel());
        emptyBoard.getSquare(6, 9).updatePiece(new Colonel());

        emptyBoard.getSquare(3, 2).updatePiece(new Major());
        emptyBoard.getSquare(5, 1).updatePiece(new Major());
        emptyBoard.getSquare(7, 2).updatePiece(new Major());
        emptyBoard.getSquare(1, 9).updatePiece(new Major());
        emptyBoard.getSquare(3, 9).updatePiece(new Major());
        emptyBoard.getSquare(7, 9).updatePiece(new Major());

        emptyBoard.getSquare(8, 2).updatePiece(new Captain());
        emptyBoard.getSquare(4, 2).updatePiece(new Captain());
        emptyBoard.getSquare(9, 2).updatePiece(new Captain());
        emptyBoard.getSquare(10, 1).updatePiece(new Captain());
        emptyBoard.getSquare(4, 8).updatePiece(new Captain());
        emptyBoard.getSquare(10, 8).updatePiece(new Captain());
        emptyBoard.getSquare(7, 8).updatePiece(new Captain());
        emptyBoard.getSquare(5, 8).updatePiece(new Captain());

        emptyBoard.getSquare(2, 3).updatePiece(new Lieutenant());
        emptyBoard.getSquare(5, 3).updatePiece(new Lieutenant());
        emptyBoard.getSquare(7, 3).updatePiece(new Lieutenant());
        emptyBoard.getSquare(9, 3).updatePiece(new Lieutenant());
        emptyBoard.getSquare(3, 8).updatePiece(new Lieutenant());
        emptyBoard.getSquare(6, 8).updatePiece(new Lieutenant());
        emptyBoard.getSquare(9, 8).updatePiece(new Lieutenant());
        emptyBoard.getSquare(1, 8).updatePiece(new Lieutenant());

        emptyBoard.getSquare(4, 3).updatePiece(new Sergeant());
        emptyBoard.getSquare(6, 3).updatePiece(new Sergeant());
        emptyBoard.getSquare(8, 3).updatePiece(new Sergeant());
        emptyBoard.getSquare(10, 2).updatePiece(new Sergeant());
        emptyBoard.getSquare(4, 8).updatePiece(new Sergeant());
        emptyBoard.getSquare(4, 7).updatePiece(new Sergeant());
        emptyBoard.getSquare(7, 7).updatePiece(new Sergeant());
        emptyBoard.getSquare(8, 7).updatePiece(new Sergeant());

        emptyBoard.getSquare(1, 2).updatePiece(new Bomb());
        emptyBoard.getSquare(2, 2).updatePiece(new Bomb());
        emptyBoard.getSquare(2, 1).updatePiece(new Bomb());
        emptyBoard.getSquare(4, 4).updatePiece(new Bomb());
        emptyBoard.getSquare(5, 4).updatePiece(new Bomb());
        emptyBoard.getSquare(9, 1).updatePiece(new Bomb());

        emptyBoard.getSquare(9, 10).updatePiece(new Bomb());
        emptyBoard.getSquare(9, 9).updatePiece(new Bomb());
        emptyBoard.getSquare(10, 9).updatePiece(new Bomb());
        emptyBoard.getSquare(5, 10).updatePiece(new Bomb());
        emptyBoard.getSquare(5, 9).updatePiece(new Bomb());
        emptyBoard.getSquare(3, 10).updatePiece(new Bomb());

        emptyBoard.getSquare(7, 1).updatePiece(new Miner());
        emptyBoard.getSquare(8, 1).updatePiece(new Miner());
        emptyBoard.getSquare(1, 4).updatePiece(new Miner());
        emptyBoard.getSquare(3, 1).updatePiece(new Miner());
        emptyBoard.getSquare(6, 1).updatePiece(new Miner());

        emptyBoard.getSquare(2, 8).updatePiece(new Miner());
        emptyBoard.getSquare(2, 10).updatePiece(new Miner());
        emptyBoard.getSquare(4, 10).updatePiece(new Miner());
        emptyBoard.getSquare(6, 10).updatePiece(new Miner());
        emptyBoard.getSquare(7, 10).updatePiece(new Miner());



        for (int i = 1; i < 11; i++) {
            for (int j = 1; j < 11; j++) {
                if (j < 5 || j > 6){
                    if (emptyBoard.getSquare(i, j).getPieceFromSquare() == null) {
                        emptyBoard.getSquare(i, j).updatePiece(new Scout());
                    } 
                    if (j < 5) {
                        emptyBoard.getSquare(i, j).getPieceFromSquare().assignPlayer(opponent);
                    } else if (j > 6) {
                        emptyBoard.getSquare(i, j).getPieceFromSquare().assignPlayer(player);
                    } 
                } 
            }
        }
    }
}
    
    



        



