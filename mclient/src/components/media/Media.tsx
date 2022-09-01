import { useState, useEffect, useRef } from "react";
import styles from "./media.module.scss";
import Player from "./player/Player";
import dummyData from "../../dummyData.json";

const Media = ({
    baseUrl,
    id,
    title,
    foreignId,
}: {
    baseUrl: string;
    id: string;
    title: string;
    foreignId: string;
}) => {
    const [songs, setSongs] = useState(dummyData);
    const [isPlaying, setIsPlaying] = useState(false);
    const [currentSong, setCurrentSong] = useState(dummyData[1]);

    const mediaElement = useRef<any>();

    useEffect(() => {
        if (isPlaying) {
            // @ts-ignore
            mediaElement.current?.play();
        } else {
            // @ts-ignore
            mediaElement?.current?.pause();
        }
    }, [isPlaying]);

    const onPlaying = () => {
        // @ts-ignore
        const duration = mediaElement.current?.duration;
        // @ts-ignore
        const ct = mediaElement.current?.currentTime;

        setCurrentSong({
            ...currentSong,
            // @ts-ignore
            progress: (ct / duration) * 100,
            length: duration,
        });
    };

    return (
        <>
            <li key={id} className={`${styles.item} card`}>
                <div className="card-content">
                    {!currentSong.mediaUrl.includes("mp4") ? (
                        <div className="card-image">
                            <figure className="image is-4by3">
                                <img
                                    src={
                                        "https://bulma.io/images/placeholders/1280x960.png"
                                    }
                                    alt={title}
                                />
                            </figure>
                        </div>
                    ) : null}
                    <div className="media">
                        <div className="media-content">
                            <p className="title is-4 is-size-6-mobile">
                                {title}
                            </p>
                        </div>
                    </div>
                    <div className={`${styles.contentContainer} content`}>
                        {currentSong.mediaUrl.includes("mp4") && (
                            <video
                                id="media-file"
                                src={currentSong.mediaUrl}
                                ref={mediaElement}
                                onTimeUpdate={onPlaying}
                            >
                                {/* <source
                                src={`${baseUrl}/${foreignId}`}
                                onTimeUpdate={onPlaying}
                            /> */}
                                Your browser does not support the{" "}
                                <code>audio</code> element.
                            </video>
                        )}
                        <div className="controls">
                            <Player
                                mediaElement={mediaElement}
                                songs={songs}
                                setSongs={setSongs}
                                isPlaying={isPlaying}
                                setIsPlaying={setIsPlaying}
                                currentSong={currentSong}
                                setCurrentSong={setCurrentSong}
                            />
                        </div>
                    </div>
                </div>
            </li>
        </>
    );
};

export default Media;
