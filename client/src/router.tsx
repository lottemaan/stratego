import { createBrowserRouter } from "react-router-dom";

import { Mancala } from "./pages/Stratego";

export const router = createBrowserRouter([
    {
        path: "/",


        children: [
            {
                index: true,
                element: <Mancala />
            },

        ]
    }
]);