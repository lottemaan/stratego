export type GameState = {
    players: [Player, Player];
    board: Board;
    gameStatus: {
        endOfGame: boolean;
        winner: string;
        gameBegun: boolean;
        winnerName: string;
    };
}

export type Board = {
    squares: Square[][];
    previousTurnLostPiece: string;
    previousTurnWonPiece: string;
    previousTurnLostPiecePlayer1: string;
    previousTurnLostPiecePlayer2: string;
    playerOneReady: boolean;
    playerTwoReady: boolean;
}


export type Square = {
    water: boolean;
    lastMove: boolean;
    lastMoveFrom: boolean;
    xCoordinate: number;
    yCoordinate: number;
    piece: Piece;
}

export type Piece = {
    name: string;
    playerId: number;
    hasTurn: boolean;
}

export type Player = {
    name: string;
    type: "player1" | "player2";
    hasTurn: boolean;
}

export type RankingItem = {
    playerName: string;
    nrOfGamesWon: number;
};

export type ArmyConfiguration = {
    initializationDone: boolean;
    playerOnePiecesSaved: boolean;
    playerTwoPiecesSaved: boolean;
};


export function isGameState(gameState: unknown): gameState is GameState {
    return (gameState as GameState).players !== undefined;
}

