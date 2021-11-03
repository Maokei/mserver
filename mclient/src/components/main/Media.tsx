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
        <div className="card media mediaList-item">
            <div className="card-content media-content">
                <div className="media media-image-wrapper">
                    <div className="media-left">
                        <figure className="image is-48x48">
                            <img src={imgSrc} alt={mediaTitle} />
                        </figure>
                    </div>
                    <div className="media-content">
                        <p className="title is-4">{mediaTitle}</p>
                        <p className="subtitle is-6">{mediaSubtitle}</p>
                    </div>
                </div>

                <div className="footer media-footer">
                    <div className="card-footer-item media-footer-item">
                        <button className="button is-dark">Like</button>
                    </div>
                    <div className="card-footer-item media-footer-item">
                        <button className="button is-dark">Play</button>
                    </div>
                </div>
            </div>
        </div>
    );
};
