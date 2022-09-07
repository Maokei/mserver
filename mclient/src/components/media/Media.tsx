import { useState, useEffect, useRef } from "react";
import styles from "./media.module.scss";
import Player from "./player/Player";

const Media = () => {
    const [clips, setClips] = useState([]);
    const [foreignId, setForeignId] = useState("");
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);
    const [isPlaying, setIsPlaying] = useState(false);
    const [currentMedia, setCurrentMedia] = useState({
        title: "Cyberpunk 2077",
        location: "file:test_files/netflix_cyberpunk.mp4",
    });

    const mediaElement = useRef<any>();

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
                // console.log(currentMedia);
                setClips(data);
                setForeignId(data.map((item: any) => item.foreignId));
                setCurrentMedia(currentMedia);
                setError(null);
            } catch (error: any) {
                setError(error.message);
                setClips([]);
            } finally {
                setLoading(false);
            }
        };
        getData();
    }, [currentMedia]);

    // useEffect(() => {
    //     if (isPlaying) {
    //         // console.log(mediaElement);
    //         mediaElement.current.play();
    //     } else {
    //         // console.log(mediaElement);
    //         mediaElement?.current.pause();
    //     }
    // }, [isPlaying]);
    useEffect(() => {
        const mediaFile: any = document.getElementById("media-file");
        var playPromise = mediaFile.play();

        if (playPromise !== undefined) {
            playPromise
                .then(() => {
                    if (isPlaying) {
                        mediaFile.play();
                    } else {
                        mediaFile.pause();
                    }
                })
                .catch((error: any) => {
                    console.log(error);
                });
        }
        // isPlaying ? mediaFile.play() : mediaFile.pause();
    }, [isPlaying]);

    const onPlaying = () => {
        const duration = mediaElement.current?.duration;
        const currentTime = mediaElement.current?.currentTime;

        setCurrentMedia({
            ...currentMedia,
            // @ts-ignore
            progress: (currentTime / duration) * 100,
            length: duration,
        });
    };

    return (
        <>
            <li className={`${styles.item} card`}>
                <div className="card-content">
                    {currentMedia && !currentMedia.location.includes("mp4") ? (
                        <div className={`card-image ${styles.imageContainer}`}>
                            <figure className="image is-4by3">
                                <img
                                    src={
                                        "https://bulma.io/images/placeholders/1280x960.png"
                                    }
                                    alt={"title"}
                                />
                            </figure>
                        </div>
                    ) : (
                        <div className={`card-image ${styles.imageContainer}`}>
                            <figure className="image is-16by9">
                                <img
                                    src={
                                        "https://bulma.io/images/placeholders/1280x960.png"
                                    }
                                    alt={"title"}
                                />
                            </figure>
                        </div>
                    )}
                    <div className={`${styles.contentContainer} content`}>
                        {currentMedia.location.includes("mp4") ? (
                            // <video id="media-file">
                            //     <source src={`${baseUrl}/${foreignId}`} />
                            //     Your browser does not support the <code>
                            //         audio
                            //     </code>{" "}
                            //     element.
                            // </video>
                            <video
                                // src={currentMedia.location}
                                id="media-file"
                                ref={mediaElement}
                                onTimeUpdate={onPlaying}
                            >
                                <source
                                    src={`http://localhost:8080/api/v1/media/${foreignId}`}
                                    type="video/mp4"
                                />
                                Your browser does not support the{" "}
                                <code>video</code> element.
                            </video>
                        ) : (
                            <audio
                                // src={currentMedia.location}
                                id="media-file"
                                ref={mediaElement}
                                onTimeUpdate={onPlaying}
                            >
                                <source
                                    src={`http://localhost:8080/api/v1/media/${foreignId}`}
                                    type="audio/mp3"
                                />
                                Your browser does not support the{" "}
                                <code>audio</code> element.
                            </audio>
                        )}
                        <div className="controls">
                            {currentMedia && (
                                <Player
                                    mediaElement={mediaElement}
                                    clips={clips}
                                    setClips={setClips}
                                    isPlaying={isPlaying}
                                    setIsPlaying={setIsPlaying}
                                    // @ts-ignore
                                    currentMedia={currentMedia}
                                    setCurrentMedia={setCurrentMedia}
                                />
                            )}
                        </div>
                    </div>
                </div>
            </li>
        </>
    );
};

export default Media;
