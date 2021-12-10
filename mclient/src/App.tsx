import React from "react";
import { Header } from "./components/header/Header";
import { MediaList } from "./components/main/MediaList";
import { SongItem } from "./components/main/Media";
import "./App.scss";
import dummyData from "./dummyData.json";
import { SelectedMedia } from "./components/main/SelectedMedia";

function App() {
    const [data, setData] = React.useState<SongItem[]>(dummyData);
    const hasSelected = React.useState<boolean>(false);

    const handleToggleLike = (id: number) => {
        const mapped = data.map((item) => {
            return item.id === Number(id)
                ? { ...item, like: !item.like }
                : { ...item };
        });
        setData(mapped);
    };

    const handlePlay = () => {
        console.log("play media...");
    };

    return (
        <div className="app has-background-dark">
            <Header />
            <main className="main">
                {!data ? (
                    "There is nothing yet"
                ) : !hasSelected ? (
                    <MediaList
                        items={data}
                        handleToggleLike={handleToggleLike}
                    />
                ) : (
                    <>
                        <MediaList
                            items={data}
                            handleToggleLike={handleToggleLike}
                        />
                        <SelectedMedia onClickPlay={handlePlay} />
                    </>
                )}
            </main>
        </div>
    );
}

export default App;
