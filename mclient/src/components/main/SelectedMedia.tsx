import React from "react";

export const SelectedMedia = () => {
    return (
        <section className="selectedMedia-wrapper">
            <div className="content progress-wrapper">
                <p id="timestamp">00:48</p>

                <progress
                    data-testid="progressbar"
                    className="progress is-small"
                    value="20"
                    max="100"
                >
                    20%
                </progress>

                <p id="timestamp">03:54</p>
            </div>

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
        </section>
    );
};
