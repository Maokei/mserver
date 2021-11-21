import React from "react";

export const SelectedMedia = () => {
    return (
        <div className="selectedMedia-wrapper">
            <progress
                data-testid="progressbar"
                className="progress is-small now-playing"
                value="20"
                max="100"
            >
                20%
            </progress>

            <div data-testid="now-playing" className="now-playing-wrapper">
                <figure className="image is-128x128">
                    <img
                        className="is-rounded"
                        src="https://bulma.io/images/placeholders/96x96.png"
                        alt="warhammer ch3"
                    />
                </figure>
                <p className="subtitle is-6">warhammer ch3</p>
                <p className="title is-4">Dark Imperioum</p>
                <p className="subtitle is-6">Guy Hayley</p>
            </div>

            <div data-testid="button-group" className="buttons-wrapper">
                <button className="button">Prev</button>
                <button className="button">Play</button>
                <button className="button">Next</button>
                <button className="button">Shuffle</button>
                <button className="button">Volume</button>
            </div>
        </div>
    );
};
