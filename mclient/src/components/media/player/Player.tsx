import React from "react";
import Button from "../../shared/Button";
import styles from "../media.module.scss";

type Song = {
    id: number;
    mediaTitle: string;
};

export type PlayerProps = {
    isPlaying: boolean;
    setIsPlaying: (isPlaying: boolean) => any;
    currentSong: any;
    // currentSong: { length:}
    setCurrentSong: any;
    songs: Song[];
    setSongs: any;
    mediaElement: any;
};

const Player = ({
    isPlaying,
    setIsPlaying,
    currentSong,
    setCurrentSong,
    songs,
    mediaElement,
}: PlayerProps) => {
    const playRef = React.useRef<any>();

    const startTime = "00:00";
    const endTime =
        currentSong.length && currentSong.length > 0
            ? Math.floor(currentSong.length / 60) +
              ":" +
              Math.floor(currentSong.length % 60)
            : "00:00";

    const handlePlayPause = () => {
        setIsPlaying(!isPlaying);
    };

    const checkProgress = (e: any) => {
        // @ts-ignore
        let width = playRef.current?.clientWidth;
        const offset = e.nativeEvent.offsetX;

        const divProgress = (offset / width) * 100;
        mediaElement.current.currentTime =
            (divProgress / 100) * currentSong.length;
    };

    const skipToPrevious = () => {
        const index = songs.findIndex(
            (song) => song.mediaTitle === currentSong.mediaTitle
        );

        if (index === 0) {
            setCurrentSong(songs[songs.length - 1]);
        } else {
            setCurrentSong(songs[index - 1]);
        }

        mediaElement.currentTime = 0;
    };

    const skipToNext = () => {
        const index = songs.findIndex(
            (song) => song.mediaTitle === currentSong.mediaTitle
        );

        if (index === songs.length - 1) {
            setCurrentSong(songs[0]);
        } else {
            setCurrentSong(songs[index + 1]);
        }

        mediaElement.currentTime = 0;
    };

    return (
        <div className={styles.container}>
            <div className={styles.title}>
                <p>{currentSong.mediaTitle}</p>
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
                        style={{ width: `${currentSong.progress + "%"}` }}
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
