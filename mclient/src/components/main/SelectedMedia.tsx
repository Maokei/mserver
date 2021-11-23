import React from "react";

export const SelectedMedia = () => {
    return (
        <section className="selectedMedia-wrapper">
            <div className="content progress-wrapper">
                <p id="timestamp">00:48</p>

                <progress
                    data-testid="progressbar"
                    id="progressbar"
                    className="progress is-small is-orange"
                    value="20"
                    max="100"
                >
                    20%
                </progress>

                <p id="timestamp">03:54</p>
            </div>

            <div
                data-testid="now-playing"
                className="medium now-playing-wrapper"
            >
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

            <div data-testid="button-group" className="buttons-wrapper">
                <button className="button is-dark icon icon-previous">
                    <i className="fas fa-caret-left"></i>
                </button>
                <button className="button is-dark icon icon-play">
                    <i className="fas fa-play"></i>
                </button>
                <button className="button is-dark icon icon-next">
                    <i className="fas fa-caret-right"></i>
                </button>
                <button className="button is-dark icon icon-shuffle">
                    <i className="fas fa-random"></i>
                </button>
                <button className="button is-dark icon icon-volume">
                    <i className="fas fa-volume-up"></i>
                </button>
            </div>
        </section>
    );
};
