import React from "react";
import { Header } from "./components/header/Header";
import { MediaList } from "./components/main/MediaList";
import "./App.scss";
import dummyData from "./dummyData.json";

function App() {
    const [data, setData] = React.useState(dummyData);

    const handleToggle = (id: number) => {
        const mapped = data.map((item) => {
            return item.id === Number(id)
                ? { ...item, like: !item.like }
                : { ...item };
        });
        setData(mapped);
    };

    return (
        <div className="app has-background-dark">
            <Header />
            <main className="main">
                {!data ? (
                    "There is nothing yet"
                ) : (
                    // @ts-ignore
                    <MediaList items={data} handleToggle={handleToggle} />
                )}
            </main>
        </div>
    );
}

export default App;
