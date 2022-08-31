import React from "react";
import Button from "../../shared/Button";

type Song = {
    id: number;
    mediaTitle: string;
};

export type Player = {
    isPlaying: boolean;
    setIsPlaying: (isPlaying: boolean) => any;
    currentSong: any;
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
}: Player) => {
    const ref = React.useRef();

    const handlePlayPause = () => {
        setIsPlaying(!isPlaying);
    };

    const checkProgress = (e: any) => {
        // @ts-ignore
        let width = ref.current?.clientWidth;
        const offset = e.nativeEvent.offsetX;

        const divprogress = (offset / width) * 100;
        mediaElement.current.currentTime =
            (divprogress / 100) * currentSong.length;
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
        <div className="player_container">
            <div className="title">
                <p>{currentSong.mediaTitle}</p>
            </div>
            <div className="progress">
                <div className="progress" onClick={checkProgress}>
                    <div
                        className="seek_bar"
                        style={{ width: `${currentSong.progress + "%"}` }}
                    ></div>
                </div>
            </div>
            <div className="controls">
                <Button
                    btnClass="previous"
                    children={<i className="fas fa-caret-left"></i>}
                    onButtonClick={skipToPrevious}
                />
                {isPlaying ? (
                    <Button
                        btnClass={`play`}
                        children={<i className="fas fa-play"></i>}
                        onButtonClick={handlePlayPause}
                    />
                ) : (
                    <Button
                        btnClass={`pause`}
                        children={<i className="fas fa-pause"></i>}
                        onButtonClick={handlePlayPause}
                    />
                )}
                <Button
                    btnClass="next"
                    children={<i className="fas fa-caret-right"></i>}
                    onButtonClick={skipToNext}
                />
            </div>
        </div>
    );
};

export default Player;
