import React from "react";
import { Header } from "./components/Header";
import "./App.scss";

function App() {
    const handleSearch = () => {
        console.log("search");
    };

    return (
        <div className="app has-background-dark">
            <Header onClick={() => handleSearch()} />
        </div>
    );
}

export default App;
