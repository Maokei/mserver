import React from "react";

export const MediaProgress = () => {
    const currentValue = "20";

    return (
        <div className="content progress-wrapper">
            <p id="timestamp">00:48</p>

            <progress
                data-testid="progressbar"
                id="progressbar"
                className="progress is-small is-orange"
                value={currentValue}
                max="100"
            >
                {currentValue}
            </progress>

            <p id="timestamp">03:54</p>
        </div>
    );
};
