import { useEffect, useState } from 'react'
import React from 'react';
import { useMancalaGame } from "../contexts/StrategoGameContext";
import { startGame } from '../services/api';
import { isGameState } from '../types';


export const Start = () => {
    const { setGameState } = useMancalaGame();


    const [input1, setInput1] = useState('');
    const [input2, setInput2] = useState('');

    const handleInput1Change = (e: React.ChangeEvent<HTMLInputElement>) => {
        setInput1(e.target.value);
    };

    const handleInput2Change = (e: React.ChangeEvent<HTMLInputElement>) => {
        setInput2(e.target.value);
    };


    const [data, setData] = useState<string>('');
    const [count, setCount] = useState(1);

    const handlePlayClick = async () => {
        const result = await startGame(input1, input2);

        if (isGameState(result)) {
            setGameState(result);
        }
    }

    //   useEffect(() => {
    //     const fetchData = async () => {
    //       try {
    //         // Make a GET request to the backend API using await fetch
    //         const response = await fetch('/stratego/api/start');

    //         if (!response.ok) {
    //           throw new Error('Network response was not ok');
    //         }

    //         const responseData = await response.json();

    //         // Extract the "name" property from the responseData object
    //         const name = responseData.name;

    //         // Set the extracted name to the data state variable
    //         setData(name);
    //       } catch (error) {
    //         console.error('Error:', error);
    //       }
    //     };

    //     fetchData();
    //   }, []);

    return (

        <div className="min-h-screen flex flex-col items-center justify-center bg-stratego relative bg-no-repeat bg-cover">
            <div className="aspect-w-16 aspect-h-9 bg-fixed">
                <div className="h-full flex items-center justify-center">
                    <div className="text-white relative">
                        <div className="bg-black bg-opacity-50 p-4 rounded-lg">
                            <h2 className="mb-4 text-4xl font-semibold text-white">Welkom bij Stratego</h2>
                            <h4 className="mb-6 text-xl font-semibold text-white">Voer de namen van de spelers in om te beginnen</h4>



                            <div className="inputfields">

                                <input
                                    type="text"
                                    value={input1}
                                    onChange={handleInput1Change}
                                    placeholder="naam speler 1"
                                    className="text-black" // Apply text-black class to the input field
                                />
                                <input
                                    type="text"
                                    value={input2}
                                    onChange={handleInput2Change}
                                    placeholder="naam speler 2"
                                    className="text-black" // Apply text-black class to the input field
                                />
                                <br />
                                <br />
                                <button
                                    className="bg-blue-500 text-white font-semibold py-2 px-4 rounded hover:bg-blue-700"
                                    onClick={() => handlePlayClick()}
                                >
                                    Speel
                                </button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    );
}


  
      {/* <div style={containerStyle}>
        <img src={logo} alt="Logo" style={imageStyle} />
      </div> */}
      {/* <h1>Stratego</h1>
      <div className="card">
        <button onClick={() => setCount(count+1)}>
          count is {count}
          <p>{data}</p>
        </button>
      </div> */}



//   const [data, setData] = useState<string>('');

//   useEffect(() => {
//     const fetchData = async () => {
//       try {
//         // Make a GET request to the backend API using await fetch
//         const response = await fetch('/stratego/api/start');

//         if (!response.ok) {
//           throw new Error('Network response was not ok');
//         }

//         const responseData = await response.json();
//         setData(responseData); // Set the response data to the state
//       } catch (error) {
//         console.error('Error:', error);
//       }
//     };

//     fetchData();
//   }, []);

//   return (
//     <>
//       <div>
//         <a href="https://vitejs.dev" target="_blank">
//           <img src={viteLogo} className="logo" alt="Vite logo" />
//         </a>
//         <a href="https://react.dev" target="_blank">
//           <img src={reactLogo} className="logo react" alt="React logo" />
//         </a>
//       </div>
//       <h1>Vite + React</h1>
//       <div className="card">
//         <button onClick={{data} => <p>{data}</p>}>
//         count is
//         </button>
//         <p>
//           Edit <code>src/App.tsx</code> and save to test HMR
//         </p>
//       </div>
//       <p className="read-the-docs">
//         Click on the Vite and React logos to learn more
//       </p>
//     </>
//   )

// }

// export default App;
