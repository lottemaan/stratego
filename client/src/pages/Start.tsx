import { useEffect, useState } from 'react';
import React from 'react';
import styled from 'styled-components';
import { useMancalaGame } from '../contexts/StrategoGameContext';
import { startGame } from '../services/api';
import { RankingItem, isGameState } from '../types';

const Table = styled.table`
    width: 100%;
    text-align: center;
    border-collapse: collapse;

`;

const Th = styled.th`
    color: white;
    padding: 10px;
    border-bottom: 2px solid white; /* Add a white bottom border */
`;

const Td = styled.td`
    padding: 10px;
`;

export const Start = () => {
    const { setGameState } = useMancalaGame();
    const [rankingData, setRankingData] = useState<RankingItem[] | null>(null);
    const [input1, setInput1] = useState('');
    const [input2, setInput2] = useState('');

    const handleInput1Change = (e: React.ChangeEvent<HTMLInputElement>) => {
        setInput1(e.target.value);
    };

    const handleInput2Change = (e: React.ChangeEvent<HTMLInputElement>) => {
        setInput2(e.target.value);
    };

    useEffect(() => {
        // Make an HTTP request to your API endpoint to fetch ranking data
        fetch('/stratego/api/ranking')
            .then((response) => response.json())
            .then((data) => setRankingData(data))
            .catch((error) => console.error('Error:', error));
    }, []);

    const handlePlayClick = async () => {
        const result = await startGame(input1, input2);

        if (isGameState(result)) {
            setGameState(result);
        } else {
            // Handle the error, you can display it or perform any other error handling here
            console.error(`Error: ${result.statusCode} ${result.statusText}`);
        }
    };

    const sortedRankingData = rankingData
        ? [...rankingData].sort((a, b) => b.nrOfGamesWon - a.nrOfGamesWon)
        : null;

        return (
            <div className="min-h-screen flex flex-col items-center justify-center bg-stratego relative bg-no-repeat bg-cover">
                <div className="aspect-w-16 aspect-h-9 bg-fixed">
                    <div className="h-full flex items-center justify-center">
                        <div className="text-white relative">
                            <div className="bg-black bg-opacity-50 p-4 rounded-lg">
                                <h2 className="mb-4 text-4xl font-semibold text-white text-center">Welkom bij Stratego</h2>
                                <h4 className="mb-6 text-xl font-semibold text-white text-center">Voer de namen van de spelers in om te beginnen</h4>
                                <div className="inputfields">
                                <div className="flex justify-center space-x-4"> {/* Center text and button with spacing */}
                                        <input
                                            type="text"
                                            value={input1}
                                            onChange={handleInput1Change}
                                            placeholder="naam speler 1"
                                            className="text-black"
                                        />
                                        <input
                                            type="text"
                                            value={input2}
                                            onChange={handleInput2Change}
                                            placeholder="naam speler 2"
                                            className="text-black"
                                        />
                               
                                    <br />
                                    <button
                                        className="bg-blue-500 text-white font-semibold py-2 px-4 rounded hover-bg-blue-700"
                                        onClick={() => handlePlayClick()}
                                    >
                                        Speel
                                    </button>
                                    </div>
                                </div>
                                <div className="mt-4"></div>
                                <div className="mt-4"></div>
                                <Table>
                                    <thead>
                                        <tr>
                                            <Th>Rank</Th>
                                            <Th>Name</Th>
                                            <Th>Points</Th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        {sortedRankingData?.map((item, index) => (
                                            <tr key={index}>
                                                <Td>{index + 1}</Td>
                                                <Td>{item.playerName}</Td>
                                                <Td>{item.nrOfGamesWon}</Td>
                                            </tr>
                                        ))}
                                    </tbody>
                                </Table>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        );
    };