import { useEffect, useState } from 'react'
import reactLogo from './assets/react.svg'
import viteLogo from '/vite.svg'
import './App.css'

function App() {
  const [data, setData] = useState<string>('');
  const [count, setCount] = useState(1);

  useEffect(() => {
    const fetchData = async () => {
      try {
        // Make a GET request to the backend API using await fetch
        const response = await fetch('/stratego/api/start');

        if (!response.ok) {
          throw new Error('Network response was not ok');
        }

        const responseData = await response.json();

        // Extract the "name" property from the responseData object
        const name = responseData.name;

        // Set the extracted name to the data state variable
        setData(name);
      } catch (error) {
        console.error('Error:', error);
      }
    };

    fetchData();
  }, []);

  return (
    <>
      <div>
        <a href="https://vitejs.dev" target="_blank">
          <img src={viteLogo} className="logo" alt="Vite logo" />
        </a>
        <a href="https://react.dev" target="_blank">
          <img src={reactLogo} className="logo react" alt="React logo" />
        </a>
      </div>
      <h1>Vite + React</h1>
      <div className="card">
      
  
        <button onClick={() => setCount(count+1)}>
          count is {count}
          <p>{data}</p>
        </button>

      </div>
      <p className="read-the-docs">
        Click on the Vite and React logos to learn more
      </p>
    </>
  );
}

export default App;
  
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
