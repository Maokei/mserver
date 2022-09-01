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
    const [clips, setClips] = useState(dummyData);
    const [isPlaying, setIsPlaying] = useState(false);
    const [currentMedia, setCurrentMedia] = useState(dummyData[1]);

    const mediaElement = useRef<any>();

    useEffect(() => {
        if (isPlaying) {
            mediaElement.current?.play();
        } else {
            mediaElement?.current?.pause();
        }
    }, [isPlaying]);

    const onPlaying = () => {
        const duration = mediaElement.current?.duration;
        const currentTime = mediaElement.current?.currentTime;

        setCurrentMedia({
            ...currentMedia,
            progress: (currentTime / duration) * 100,
            length: duration,
        });
    };

    return (
        <>
            <li key={id} className={`${styles.item} card`}>
                <div className="card-content">
                    {!currentMedia.mediaUrl.includes("mp4") ? (
                        <div className={`card-image ${styles.imageContainer}`}>
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
                    <div className={`${styles.contentContainer} content`}>
                        {currentMedia.mediaUrl.includes("mp4") ? (
                            <video
                                src={currentMedia.mediaUrl}
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
                        ) : (
                            <audio
                                src={currentMedia.mediaUrl}
                                ref={mediaElement}
                                onTimeUpdate={onPlaying}
                            >
                                {/* <source
                                src={`${baseUrl}/${foreignId}`}
                                onTimeUpdate={onPlaying}
                            /> */}
                                Your browser does not support the{" "}
                                <code>audio</code> element.
                            </audio>
                        )}
                        <div className="controls">
                            <Player
                                mediaElement={mediaElement}
                                clips={clips}
                                setClips={setClips}
                                isPlaying={isPlaying}
                                setIsPlaying={setIsPlaying}
                                currentMedia={currentMedia}
                                setCurrentMedia={setCurrentMedia}
                            />
                        </div>
                    </div>
                </div>
            </li>
        </>
    );
};

export default Media;
