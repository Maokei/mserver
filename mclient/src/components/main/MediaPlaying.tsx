import React from "react";

export const MediaPlaying = () => {
    return (
        <div data-testid="now-playing" className="medium now-playing-wrapper">
            <div className="medium-art">
                <figure className="image is-128x128">
                    <img
                        className="is-rounded"
                        src="https://bulma.io/images/placeholders/96x96.png"
                        alt="warhammer ch3"
                    />
                </figure>
            </div>
            <p className="subtitle is-6 medium-album">warhammer ch3</p>
            <p className="title is-4 medium-title">Dark Imperioum</p>
            <p className="subtitle is-6 medium-artist">Guy Hayley</p>
        </div>
    );
};
