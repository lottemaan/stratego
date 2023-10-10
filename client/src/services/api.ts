import { GameState } from "../types";
import { useStrategoGame } from "../contexts/StrategoGameContext";

// export async function startGame() {
//     const response = await fetch("mancala/api/start", {
//         method: "GET",
//         headers: {
//             Accept: "application/json",
//             "Content-Type": "application/json",
//         },
//         // body: JSON.stringify({
//         //     player1: player1,
//         //     player2: player2,
//         // }),
//     });
//     // player1: string, player2: string

//     if (response.ok) {
//         const gameState = await response.json();
//         return gameState as GameState;
//     } else {
//         return {
//             statusCode: response.status,
//             statusText: response.statusText
//         };
//     }
// }