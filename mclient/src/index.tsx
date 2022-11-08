import React from "react";
import { createRoot } from "react-dom/client";
import { BrowserRouter } from "react-router-dom";
import "./styles/styles.scss";
import App from "./App";
import reportWebVitals from "./reportWebVitals";
import { MediaDataProvider } from "./context/mediaDataContext";

// tell typescript that root won't be null with the exclamation mark ('!')
const root = createRoot(document.getElementById("root")!);

root.render(
    <React.StrictMode>
        <BrowserRouter>
            <MediaDataProvider>
                <App />
            </MediaDataProvider>
        </BrowserRouter>
    </React.StrictMode>
);

// If you want to start measuring performance in your app, pass a function
// to log results (for example: reportWebVitals(console.log))
// or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals
reportWebVitals();
