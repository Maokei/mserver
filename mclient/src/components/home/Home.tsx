import React from "react";
import { useMediaData } from "../../context/mediaDataContext";
import useModal from "../../hooks/useModal";
import Modal from "../modal/Modal";
import Player from "../player/Player";
import { Card } from "../shared/Card";
import styles from "./home.module.scss";

const Home = () => {
    const { data, loading, error } = useMediaData();
    const { isShowing, toggle } = useModal();

    const [id, setId] = React.useState<string>("");

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
                        <li>
                            <button
                                className={styles.downloadButton}
                                onClick={toggle}
                            >
                                Download
                            </button>
                            <Modal
                                isShowing={isShowing}
                                hide={toggle}
                                modalName="Download Youtube clip"
                                labelText={["URL"]}
                                fileType="Audio"
                                isAudio={true}
                                isVideo={true}
                            />
                        </li>
                    </ul>

                    <div className={styles.divider} />

                    <ul className="menuBottom">
                        <li>
                            <a href="/music">Music</a>
                        </li>
                        <li>
                            <a href="/video">Video</a>
                        </li>
                        <li>
                            <a href="/podcast">Podcast</a>
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
                                    <Card
                                        key={item.id}
                                        id={item.id}
                                        setId={setId}
                                        title={item.title}
                                        mediaId={item.id}
                                        media={`http://localhost:8080/api/v1/media/${item.foreignId}`}
                                    />
                                ))}
                        </div>
                    )}
                </div>
            </main>

            <Player id={id} setId={setId} />
        </>
    );
};

export default Home;
