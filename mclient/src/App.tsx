import React from "react";
import { Header } from "./components/header/Header";
import { MediaList } from "./components/main/MediaList";
import "./App.scss";
import data from "./dummyData.json";

function App() {
    return (
        <div className="app has-background-dark">
            <Header />
            <main className="main">
                {!data ? "There is nothing yet" : <MediaList items={data} />}
            </main>
        </div>
    );
}

export default App;
