import React from "react";

export interface MediaProps {
    id: number;
    imgSrc: string;
    mediaTitle: string;
    mediaSubtitle: string;
    play: boolean;
    like: boolean;
    playState: boolean;
    likeState: boolean;
    handleToggle: Function;
}

export const Media: React.FC<MediaProps> = ({
    id,
    imgSrc,
    mediaTitle,
    mediaSubtitle,
    playState,
    likeState,
    handleToggle,
}) => {
    const playBtnActive = playState ? "play-btn-active" : "";

    const handleClick = (e: React.MouseEvent<HTMLButtonElement>) => {
        e.preventDefault();
        handleToggle(e.currentTarget.id);
    };

    return (
        <div className="mediaList-item">
            <figure className="image is-48x48">
                <img src={imgSrc} alt={mediaTitle} />
            </figure>
            <p className="title is-4">{mediaTitle}</p>
            <p className="subtitle is-6">{mediaSubtitle}</p>
            <button
                id={id.toString()}
                className={`button is-dark icon like-btn ${
                    likeState ? "like-btn-active" : ""
                }`}
                onClick={handleClick}
            >
                <i className="fas fa-heart"></i>
            </button>
            {!playState ? (
                <button
                    className={`button is-dark icon play-btn ${playBtnActive}`}
                >
                    <i className="fas fa-play"></i>
                </button>
            ) : (
                <button className="button is-dark icon pause-btn">
                    <i className="fas fa-pause"></i>
                </button>
            )}
        </div>
    );
};
