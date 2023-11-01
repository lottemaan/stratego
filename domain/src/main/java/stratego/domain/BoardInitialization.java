package stratego.domain;

import java.util.Random;

final class BoardInitialization {
    
    protected void initializeRandomly(Square[][] squares, Player player, Player opponent) {
        Random random = new Random();
        int choice = random.nextInt(2);

        if (choice == 0) {
            initializeScript1(squares, player, opponent);
        } else {
            initializeScript2(squares, player, opponent);
        }
    }

    private void initializeScript1(Square[][] squares, Player player, Player opponent) {
        squares[3][0].updatePiece(new Flag());
        squares[0][9].updatePiece(new Flag());
        squares[4][1].updatePiece(new Marshal());
        squares[7][9].updatePiece(new Marshal());
        squares[2][2].updatePiece(new Spy());
        squares[7][8].updatePiece(new Spy());
        squares[2][2].updatePiece(new General());
        squares[7][7].updatePiece(new General());

        squares[0][2].updatePiece(new Colonel());
        squares[5][1].updatePiece(new Colonel());
        squares[1][8].updatePiece(new Colonel());
        squares[5][8].updatePiece(new Colonel());

        squares[2][1].updatePiece(new Major());
        squares[4][0].updatePiece(new Major());
        squares[6][1].updatePiece(new Major());
        squares[0][8].updatePiece(new Major());
        squares[2][8].updatePiece(new Major());
        squares[6][8].updatePiece(new Major());

        squares[7][1].updatePiece(new Captain());
        squares[3][1].updatePiece(new Captain());
        squares[8][1].updatePiece(new Captain());
        squares[9][0].updatePiece(new Captain());
        squares[3][8].updatePiece(new Captain());
        squares[9][7].updatePiece(new Captain());
        squares[6][7].updatePiece(new Captain());
        squares[4][7].updatePiece(new Captain());

        squares[1][2].updatePiece(new Lieutenant());
        squares[4][2].updatePiece(new Lieutenant());
        squares[6][2].updatePiece(new Lieutenant());
        squares[8][2].updatePiece(new Lieutenant());
        squares[2][7].updatePiece(new Lieutenant());
        squares[5][7].updatePiece(new Lieutenant());
        squares[8][7].updatePiece(new Lieutenant());
        squares[0][7].updatePiece(new Lieutenant());

        squares[3][2].updatePiece(new Sergeant());
        squares[5][2].updatePiece(new Sergeant());
        squares[7][2].updatePiece(new Sergeant());
        squares[9][1].updatePiece(new Sergeant());
        squares[3][7].updatePiece(new Sergeant());
        squares[3][6].updatePiece(new Sergeant());
        squares[6][6].updatePiece(new Sergeant());
        squares[7][6].updatePiece(new Sergeant());

        squares[0][1].updatePiece(new Bomb());
        squares[1][1].updatePiece(new Bomb());
        squares[1][0].updatePiece(new Bomb());
        squares[3][3].updatePiece(new Bomb());
        squares[4][3].updatePiece(new Bomb());
        squares[8][0].updatePiece(new Bomb());

        squares[8][9].updatePiece(new Bomb());
        squares[8][8].updatePiece(new Bomb());
        squares[9][8].updatePiece(new Bomb());
        squares[4][9].updatePiece(new Bomb());
        squares[4][8].updatePiece(new Bomb());
        squares[2][9].updatePiece(new Bomb());

        squares[6][0].updatePiece(new Miner());
        squares[7][0].updatePiece(new Miner());
        squares[0][3].updatePiece(new Miner());
        squares[2][0].updatePiece(new Miner());
        squares[5][0].updatePiece(new Miner());

        squares[1][7].updatePiece(new Miner());
        squares[1][9].updatePiece(new Miner());
        squares[3][9].updatePiece(new Miner());
        squares[5][9].updatePiece(new Miner());
        squares[6][9].updatePiece(new Miner());

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (j < 4 || j > 5){
                    if (squares[i][j].getPieceFromSquare() == null) {
                        squares[i][j].updatePiece(new Scout());
                    } 
                    if (j < 4) {
                        squares[i][j].getPieceFromSquare().assignPlayer(opponent);
                    } else if (j > 5) {
                        squares[i][j].getPieceFromSquare().assignPlayer(player);
                    } 
                } 
                else if ((i == 2 || i == 3 || i == 6 || i == 7) && (j == 4 || j == 5)) {
                    squares[i][j].turnInWater();
            }
        }
    }
    } 
    
    private void initializeScript2(Square[][] squares, Player player, Player opponent) {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if ((i == 2 && j == 2) || (i == 8 && j == 8)) {
                    squares[i][j].updatePiece(new Flag()); 
                } else if ((i == 3 && j == 3) || (i == 7 && j == 7)) {
                    squares[i][j].updatePiece(new Spy());
                } else if ((i == 1 && j == 3) || (i == 9 && j == 8)) {
                    squares[i][j].updatePiece(new Marshal());
                } else if (j > 3 && j < 6) {
                    squares[i][j].updatePiece(null);
                } else {squares[i][j].updatePiece(new Scout());}
                
                if (j < 4) {
                    squares[i][j].getPieceFromSquare().assignPlayer(opponent);
                } else if (j > 5) {
                    squares[i][j].getPieceFromSquare().assignPlayer(player);
                }
            } 
        }
    }
}
    
    



        



