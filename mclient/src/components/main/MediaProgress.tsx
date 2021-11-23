import React from "react";

export const MediaProgress = () => {
    return (
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
    );
};
