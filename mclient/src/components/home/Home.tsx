import { useMediaData } from "../../context/mediaDataContext";
// import { MediaPlaying } from "../main/MediaPlaying";
// import { MediaProgress } from "../main/MediaProgress";
import Player from "../player/Player";
import { Card } from "../shared/Card";
// import { CardProps } from "../../types";
import styles from "./home.module.scss";

// type CardListProps = {
//     items: CardProps[];
// };

const Home = () => {
    const { data, loading, error } = useMediaData();

    return (
        <>
            <main className={styles.container}>
                <aside className={styles.menu}>
                    <ul className="menuTop">
                        <li>
                            <a href="/">Home</a>
                        </li>
                        <li>
                            <a href="/library">Library</a>
                        </li>
                    </ul>

                    <div className={styles.divider} />

                    <ul className="menuBottom">
                        <li>
                            <a href="/beats">Beats</a>
                        </li>
                        <li>
                            <a href="/ambient">Ambient</a>
                        </li>
                        <li>
                            <a href="/synthetic">Synthetic</a>
                        </li>
                    </ul>
                </aside>

                <div className={styles.recentlyPlayed}>
                    <p className="title is-4 is-size-6-mobile">
                        Recently Played
                    </p>

                    {error && <p>{error}</p>}
                    {loading ? (
                        <p>Loading ...</p>
                    ) : (
                        <div className={styles.cardsGroup}>
                            {data &&
                                data.map((item) => (
                                    <Card key={item.id} {...item} />
                                ))}
                        </div>
                    )}
                </div>
            </main>

            <Player
                id={""}
                isFull={false}
                setId={function (e: string): void {
                    throw new Error("Function not implemented.");
                }}
                setIsFull={function (e: boolean): void {
                    throw new Error("Function not implemented.");
                }}
            />
            {/* <div className={styles.playingNow}>
                <MediaProgress />
                <MediaPlaying />
            </div> */}
        </>
    );
};

export default Home;
