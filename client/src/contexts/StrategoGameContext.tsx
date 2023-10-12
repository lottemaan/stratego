import { createContext, useContext, useState } from "react";
import { GameState } from "../types";

type ContextType = {
    gameState: GameState | undefined,
    setGameState: (gameState: GameState) => void;
}

const StrategoGameContext = createContext<ContextType>({
    gameState: undefined,
    setGameState() { },
});

type Props = React.PropsWithChildren;

export const StrategoGameProvider = (props: Props) => {
    const { children } = props;

    const [gameState, setGameState] = useState<GameState | undefined>(undefined);

    return <StrategoGameContext.Provider value={{
        gameState: gameState,
        setGameState: setGameState
    }}>{children}</StrategoGameContext.Provider>
}

export const useMancalaGame = () => {
    const context = useContext(StrategoGameContext);

    if (context === undefined) {
        throw new Error('useMancalaGame must be used within a MancalaGameProvider');
    }

    return context;
}
