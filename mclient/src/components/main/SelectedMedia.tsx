import React from "react";
import { MediaButtons } from "./MediaButtons";
import { MediaPlaying } from "./MediaPlaying";
import { MediaProgress } from "./MediaProgress";
import styles from "./selectedMedia.module.scss";

interface ButtonGroupProps {
	onClickPlay: Function;
}

export const SelectedMedia: React.FC<ButtonGroupProps> = ({ onClickPlay }) => {
	return (
		<section className={styles.wrapper}>
			<MediaProgress />

			<MediaPlaying />

			<MediaButtons onClickPlay={onClickPlay} />
		</section>
	);
};
