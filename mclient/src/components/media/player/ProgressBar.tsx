import React, { ReactNode, useState } from "react";
import moment from "moment";

const ProgressBar = ({
	duration,
	currentTime,
}: {
	duration: ReactNode | any;
	currentTime: Number | any;
}) => {
	const currentPercentage = (currentTime / duration) * 100;

	function formatDuration(duration: moment.DurationInputArg1 | any) {
		return (
			moment
				.duration(duration, "seconds")
				// @ts-ignore
				.format("mm:ss", { trim: false })
		);
	}

	return (
		<div className="bar content">
			<span className="bar__time timestamp">{currentTime}</span>
			<progress
				data-testid="progressbar"
				id="progressbar"
				className={`progress is-small progressbar isOrange`}
				value={currentPercentage}
				max="100"
			/>
			{/* <div
				className="bar__progress"
				style={{
					background: `linear-gradient(to right, orange ${currentPercentage}%, white 0)`,
				}}
				onMouseDown={(e) => handleTimeDrag(e)}
			>
				<span
					className="bar__progress__knob"
					style={{ left: `${currentPercentage - 2}%` }}
				/>
			</div> */}
			<span className="bar__time timestamp">{duration}</span>
		</div>
	);
};

export default ProgressBar;
