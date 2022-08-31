import React, { useEffect, useState } from "react";
import Media from "./Media";
import styles from "./mediaList.module.scss";

const MediaList = () => {
    const [data, setData] = useState([]);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);

    useEffect(() => {
        const getData = async () => {
            try {
                const response = await fetch(
                    "http://localhost:8080/api/v1/media"
                );
                if (!response.ok) {
                    throw new Error(
                        `This is a HTTP error with a status of ${response.status}`
                    );
                }
                const data = await response.json();
                setData(data);
                setError(null);
            } catch (error: any) {
                setError(error.message);
                setData([]);
            } finally {
                setLoading(false);
            }
        };
        getData();
    }, []);

    return (
        <main className={styles.container}>
            <h3 className="is-size-3 has-text-white mt-6">MediaPlayer</h3>
            <>
                {loading && <div>A moment please...</div>}
                {error && (
                    <div>{`There is a problem fetching media data - ${error}`}</div>
                )}
                <ul className={styles.list}>
                    {data &&
                        data.map(
                            ({
                                id,
                                title,
                                foreignId,
                            }: {
                                id: string;
                                title: string;
                                foreignId: string;
                            }) => (
                                <li key={id} className={styles.item}>
                                    <figure className="image is-48x48">
                                        <img
                                            className="is-rounded"
                                            src={
                                                "https://bulma.io/images/placeholders/1280x960.png"
                                            }
                                            alt={title}
                                        />
                                    </figure>
                                    <h3 className="title is-3 is-size-6-mobile">
                                        {title}
                                    </h3>
                                    <video
                                        src={`http://localhost:8080/api/v1/media/${foreignId}`}
                                        controls
                                        preload="none"
                                    ></video>
                                </li>
                            )
                        )}
                </ul>
            </>
        </main>
    );
};

export default MediaList;
