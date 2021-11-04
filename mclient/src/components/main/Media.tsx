import React from "react";

export interface MediaProps {
    id: number;
    imgSrc: string;
    mediaTitle: string;
    mediaSubtitle: string;
}

export const Media: React.FC<MediaProps> = ({
    id,
    imgSrc,
    mediaTitle,
    mediaSubtitle,
}) => {
    return (
        <div className="mediaList-item">
            <figure className="image is-48x48">
                <img src={imgSrc} alt={mediaTitle} />
            </figure>
            <p className="title is-4">{mediaTitle}</p>
            <p className="subtitle is-6">{mediaSubtitle}</p>
            <button className="button is-dark icon like-btn">
                <i className="fas fa-heart"></i>
            </button>
            <button className="button is-dark icon play-btn">
                <i className="fas fa-play"></i>
            </button>
        </div>
    );
};
