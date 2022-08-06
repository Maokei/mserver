import React from "react";
import Button from "../../shared/Button";
// import styles from "../../main/MediaButtons";

const Play = ({ handlePlayClick }: { handlePlayClick: Function }) => {
	return (
		<Button
			btnClass={`play`}
			children={<i className="fas fa-play"></i>}
			onButtonClick={() => handlePlayClick()}
		/>
	);
};

export default Play;
