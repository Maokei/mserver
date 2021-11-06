import React from "react";

export interface MediaProps {
    id: number;
    imgSrc: string;
    mediaTitle: string;
    mediaSubtitle: string;
    playState: boolean;
    likeState: boolean;
    likeId: number;
    activeItem: any;
    handleClick: any;
}

export const Media: React.FC<MediaProps> = ({
    id,
    imgSrc,
    mediaTitle,
    mediaSubtitle,
    playState,
    likeState,
    handleClick,
    likeId,
    activeItem,
}) => {
    const likeBtnActive = likeState ? "like-btn-active" : "";
    activeItem = 0;
    const playBtnActive = playState ? "play-btn-active" : "";

    return (
        <div className="mediaList-item">
            <figure className="image is-48x48">
                <img src={imgSrc} alt={mediaTitle} />
            </figure>
            <p className="title is-4">{mediaTitle}</p>
            <p className="subtitle is-6">{mediaSubtitle}</p>
            <button
                className={`button is-dark icon like-btn ${likeBtnActive}`}
                onClick={() => handleClick(likeId)}
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
