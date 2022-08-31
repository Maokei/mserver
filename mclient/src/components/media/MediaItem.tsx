import { useState, useEffect, useRef } from "react";
import styles from "./mediaItem.module.scss";
import Player from "./player/Player";
import dummyData from "../../dummyData.json";
// import Pause from "./player/Pause";
// import Play from "./player/Play";
// import ProgressBar from "./player/ProgressBar";

const MediaItem = ({
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
    const [isPlaying, setIsPlaying] = useState(true);
    const [currentSong, setCurrentSong] = useState(dummyData[1]);
    const [duration, setDuration] = useState();
    const [currentTime, setCurrentTime] = useState();

    const mediaElement = useRef<HTMLAudioElement>(null);

    // useEffect(() => {
    //     const mediaFile: HTMLElement | any =
    //         document.getElementById("media-file");

    //     setDuration(mediaFile.duration);
    //     setCurrentTime(mediaFile.currentTime);

    //     // isPlaying ? mediaFile?.play() : mediaFile?.pause();
    // }, [isPlaying]);

    useEffect(() => {
        if (isPlaying) {
            mediaElement?.current?.play();
        } else {
            mediaElement?.current?.pause();
        }
    }, [isPlaying]);

    const onPlaying = () => {
        const duration = mediaElement.current?.duration;
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
                <div className="card-content">
                    <div className="media">
                        <div className="media-content">
                            <p className="title is-4 is-size-6-mobile">
                                {title}
                            </p>
                        </div>
                    </div>
                    <div className="content">
                        <video id="media-file">
                            <source
                                src={`${baseUrl}/${foreignId}`}
                                onTimeUpdate={onPlaying}
                            />
                            Your browser does not support the <code>audio</code>{" "}
                            element.
                        </video>
                        <div className="controls">
                            {/* {playing ? (
								<Pause handleClick={() => setPlaying(false)} />
							) : (
								<Play
									handlePlayClick={() => setPlaying(true)}
								/>
							)} */}

                            <Player
                                mediaElement={mediaElement}
                                songs={songs}
                                setSongs={setSongs}
                                isPlaying={isPlaying}
                                setIsPlaying={setIsPlaying}
                                currentSong={currentSong}
                                setCurrentSong={setCurrentSong}
                            />
                            {/* <ProgressBar
								duration={duration}
								currentTime={currentTime}
							/> */}
                        </div>
                    </div>
                </div>
            </li>
        </>
    );
};

export default MediaItem;
