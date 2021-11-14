import React from "react";
import { Header } from "./components/header/Header";
import { MediaList } from "./components/main/MediaList";
import { SongItem } from "./components/main/Media";
import "./App.scss";
import dummyData from "./dummyData.json";

function App() {
    const [data, setData] = React.useState<SongItem[]>(dummyData);

    const handleToggleLike = (id: number) => {
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
                    <MediaList
                        items={data}
                        handleToggleLike={handleToggleLike}
                    />
                )}
            </main>
        </div>
    );
}

export default App;
