import React from "react";

export const MediaButtons = () => {
    return (
        <div data-testid="button-group" className="buttons-wrapper">
            <button className="button is-dark icon icon-previous">
                <i className="fas fa-caret-left"></i>
            </button>
            {/* <button className="button is-dark icon icon-play">
                <i className="fas fa-play"></i>
            </button> */}
            <button className="button is-dark icon icon-pause">
                <i className="fas fa-pause"></i>
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
    );
};
