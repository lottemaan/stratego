import React from 'react'
import ReactDOM from 'react-dom/client'
import './index.css'
import { RouterProvider } from 'react-router-dom'
import { router } from './router.tsx'
import { StrategoGameProvider } from './contexts/StrategoGameContext.tsx'
import { ConfigurationProvider } from './contexts/ConfigurationContext.tsx'

ReactDOM.createRoot(document.getElementById('root')!).render(
  <React.StrictMode>
    <ConfigurationProvider>
    <StrategoGameProvider>
      
        <RouterProvider router={router} />
   
    </StrategoGameProvider>
    </ConfigurationProvider>
  </React.StrictMode>,
);
