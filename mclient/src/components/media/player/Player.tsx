import React from "react";
import Button from "../../shared/Button";
import styles from "../media.module.scss";

type ClipProps = {
    id: number;
    mediaTitle: string;
};

export type PlayerProps = {
    isPlaying: boolean;
    setIsPlaying: (isPlaying: boolean) => void;
    currentMedia: {
        length: any;
        mediaTitle: string;
        progress: number;
    };
    setCurrentMedia: any;
    clips: ClipProps[];
    setClips: any;
    mediaElement: any;
};

const Player = ({
    isPlaying,
    setIsPlaying,
    currentMedia,
    setCurrentMedia,
    clips,
    mediaElement,
}: PlayerProps) => {
    const playRef = React.useRef<any>();

    const startTime = "00:00";
    const endTime =
        currentMedia.length && currentMedia.length > 0
            ? Math.floor(currentMedia.length / 60) +
              ":" +
              Math.floor(currentMedia.length % 60)
            : "00:00";

    const handlePlayPause = () => {
        setIsPlaying(!isPlaying);
    };

    const checkProgress = (e: any) => {
        let width = playRef.current?.clientWidth;
        const offset = e.nativeEvent.offsetX;

        const divProgress = (offset / width) * 100;
        mediaElement.current.currentTime =
            (divProgress / 100) * currentMedia.length;
    };

    const skipToPrevious = () => {
        const index = clips.findIndex(
            (item) => item.mediaTitle === currentMedia.mediaTitle
        );

        if (index === 0) {
            setCurrentMedia(clips[clips.length - 1]);
        } else {
            setCurrentMedia(clips[index - 1]);
        }

        mediaElement.currentTime = 0;
    };

    const skipToNext = () => {
        const index = clips.findIndex(
            (item) => item.mediaTitle === currentMedia.mediaTitle
        );

        if (index === clips.length - 1) {
            setCurrentMedia(clips[0]);
        } else {
            setCurrentMedia(clips[index + 1]);
        }

        mediaElement.currentTime = 0;
    };

    return (
        <div className={styles.playerContainer}>
            <div className={styles.title}>
                <p className={`title is-4 is-size-6-mobile ${styles.title}`}>
                    {currentMedia.mediaTitle}
                </p>
            </div>
            <div className={styles.progress}>
                <div className={styles.timeStamp}>
                    <p>{startTime}</p>
                    <p>{endTime}</p>
                </div>
                <div
                    className={styles.progressWrapper}
                    ref={playRef}
                    onClick={checkProgress}
                >
                    <div
                        className={styles.progressBar}
                        style={{ width: `${currentMedia.progress + "%"}` }}
                    ></div>
                </div>
            </div>
            <div className={styles.controls}>
                <Button
                    btnClass={`previous`}
                    children={<i className="fas fa-caret-left"></i>}
                    onButtonClick={skipToPrevious}
                />
                {isPlaying ? (
                    <Button
                        btnClass={`pause`}
                        children={<i className="fas fa-pause"></i>}
                        onButtonClick={handlePlayPause}
                    />
                ) : (
                    <Button
                        btnClass={`play`}
                        children={<i className="fas fa-play"></i>}
                        onButtonClick={handlePlayPause}
                    />
                )}
                <Button
                    btnClass={`next`}
                    children={<i className="fas fa-caret-right"></i>}
                    onButtonClick={skipToNext}
                />
            </div>
        </div>
    );
};

export default Player;
