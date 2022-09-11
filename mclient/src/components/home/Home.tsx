import { useMediaData } from "../../context/mediaDataContext";
import { Card, CardProps } from "../shared/Card";
import { MediaPlaying } from "../main/MediaPlaying";
import { MediaProgress } from "../main/MediaProgress";
import styles from "./home.module.scss";

type CardListProps = {
    items: CardProps[];
};

const Home: React.FC<CardListProps> = ({ items }) => {
    const { data, loading, error }: any = useMediaData();

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

                    <div className={styles.cardsGroup}>
                        {data.map((item: any, index: number) => (
                            <Card key={index} {...item} />
                        ))}
                    </div>
                </div>
            </main>

            <div className={styles.playingNow}>
                <MediaProgress />
                <MediaPlaying />
            </div>
        </>
    );
};

export default Home;
