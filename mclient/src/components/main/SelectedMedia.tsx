import React from "react";
import { MediaPlaying } from "./MediaPlaying";
import { MediaProgress } from "./MediaProgress";
import styles from "./selectedMedia.module.scss";

interface ButtonGroupProps {
	onClickPlay: Function;
}

export const SelectedMedia: React.FC<ButtonGroupProps> = ({ onClickPlay }) => {
	return (
		<div className={styles.playingNow}>
			<MediaProgress />
			<MediaPlaying />
		</div>
	);
};
