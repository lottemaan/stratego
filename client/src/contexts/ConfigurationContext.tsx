import { createContext, useContext, useState } from "react";

type ConfigurationContextType = {
    playerTwoPiecesSaved: boolean,
    savePlayerTwoPieces: (value: boolean) => void;
};

const ConfigurationContext = createContext<ConfigurationContextType>({
    playerTwoPiecesSaved: false,
    savePlayerTwoPieces() { },
});

type ConfigurationProviderProps = React.PropsWithChildren;

export const ConfigurationProvider = (props: ConfigurationProviderProps) => {
    const { children } = props;

    const [playerTwoPiecesSaved, savePlayerTwoPieces] = useState<boolean>(false);

    return (
        <ConfigurationContext.Provider value={{
            playerTwoPiecesSaved: playerTwoPiecesSaved,
            savePlayerTwoPieces: savePlayerTwoPieces,
        }}>
            {children}
        </ConfigurationContext.Provider>
    );
};

export const useConfigurationContext = () => {
    const context = useContext(ConfigurationContext);

    if (context === undefined) {
        throw new Error('useConfigurationContext must be used within a ConfigurationProvider');
    }

    return context;
};
