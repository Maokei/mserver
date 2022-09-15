import React from "react";
import Button from "../shared/Button";
import styles from "./player.module.scss";

type MediaProps = {
    id: string;
    isFull: boolean;
    setId: (e: string) => void;
    setIsFull: (e: boolean) => void;
};

const Player = ({ id, setId, setIsFull, isFull }: MediaProps) => {
    const [isPlaying, setIsPlaying] = React.useState<boolean>(false);
    const [volume, setVolume] = React.useState<string>("1");
    const [isMuted, setIsMuted] = React.useState<boolean>(false);

    const mediaTag = React.useRef<any>(null);

    React.useEffect(() => {
        if (id !== "") {
            if (isPlaying) {
                console.log(mediaTag.current);
                mediaTag.current.play();
            } else {
                mediaTag.current.pause();
            }
        } else {
            console.log("id is empty");
        }
    }, [id, isPlaying]);

    const skipToPrevious = () => {
        console.log("previous");
    };

    const skipToNext = () => {
        console.log("next");
    };

    const shuffleMedia = () => {
        console.log("shuffle");
    };

    return (
        <div>
            <div className={styles.playerContainer}>
                <div className="progressBar">
                    <p className="PcurrentTime">
                        {/* {calculateDuration(currentTime)} */}
                        1:00
                    </p>
                    <input
                        type="range"
                        className="currentProgress"
                        defaultValue="0"
                        // ref={progressBar}
                        // onChange={changeRange}
                    />

                    <p className="Pduration">
                        {/* {(duration && !isNaN(duration)) && 
                      calculateDuration(duration)} */}
                        3:00
                    </p>
                </div>

                <div className={styles.controls}>
                    <Button
                        btnClass={`shuffle`}
                        children={<i className="fas fa-random"></i>}
                        onButtonClick={shuffleMedia}
                    />
                    <Button
                        btnClass={`previous`}
                        children={<i className="fas fa-caret-left"></i>}
                        onButtonClick={skipToPrevious}
                    />
                    {isPlaying ? (
                        <Button
                            btnClass={`pause`}
                            children={<i className="fas fa-pause"></i>}
                            onButtonClick={() => setIsPlaying(!isPlaying)}
                        />
                    ) : (
                        <Button
                            btnClass={`play`}
                            children={<i className="fas fa-play"></i>}
                            onButtonClick={() => setIsPlaying(!isPlaying)}
                        />
                    )}
                    <Button
                        btnClass={`next`}
                        children={<i className="fas fa-caret-right"></i>}
                        onButtonClick={skipToNext}
                    />
                </div>

                <div className="volumeContainer">
                    {isMuted ? (
                        <Button
                            btnClass={`volume`}
                            children={<i className="fas fa-volume-up"></i>}
                            onButtonClick={() => setIsMuted(!isMuted)}
                        />
                    ) : (
                        <Button
                            btnClass={`volume`}
                            children={<i className="fas fa-volume-off"></i>}
                            onButtonClick={() => setIsMuted(!isMuted)}
                        />
                    )}
                    <input
                        type="range"
                        step="0.01"
                        onChange={(e) => setVolume(e.target.value)}
                        value={volume}
                        max="1"
                        min="0"
                    />
                </div>
            </div>
        </div>
    );
};

export default Player;
