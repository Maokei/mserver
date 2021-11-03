import React from "react";
import { Header } from "./components/header/Header";
import { ClipList } from "./components/main/ClipList";
import "./App.scss";

function App() {
    return (
        <div className="app has-background-dark">
            <Header />
            <main>
                <ClipList />
            </main>
        </div>
    );
}

export default App;
