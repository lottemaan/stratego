export type GameState = {
    players: [Player, Player];
    board: Board;
    gameStatus: {
        endOfGame: boolean;
        winner: string;
    };
}

export type Board = {
    squares: Square[][];
}


export type Square = {
    xCoordinate: number;
    yCoordinate: number;
    piece: Piece;
}

export type Piece = {
    name: string;
    players: [Player, Player];
}

export type Player = {
    name: string;
    type: "player1" | "player2";
    hasTurn: boolean;
}

export function isGameState(gameState: unknown): gameState is GameState {
    return (gameState as GameState).players !== undefined;
}
