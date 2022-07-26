import { useState } from "react";
import Button from "../shared/Button";
import styles from "./mediaPlaying.module.scss";

export const MediaPlaying = () => {
	const [isPlaying, setIsPlaying] = useState(false);

	return (
		<div data-testid="now-playing" className={`${styles.wrapper} medium`}>
			<div className={styles.art}>
				<figure className="image is-48x48">
					<img
						className="is-rounded"
						src="https://bulma.io/images/placeholders/96x96.png"
						alt="warhammer ch3"
					/>
				</figure>
			</div>

			<div className={styles.mediaTextGroup}>
				<p className={`subtitle is-size-7-mobile is-6 ${styles.album}`}>
					warhammer ch3
				</p>
				<p className={`title is-size-6-mobile is-4 ${styles.title}`}>
					Dark Imperioum
				</p>
				<p
					className={`subtitle is-size-7-mobile is-6 ${styles.artist}`}
				>
					Guy Hayley
				</p>
			</div>

			<Button
				btnClass={!isPlaying ? `${styles.play}` : `${styles.hidden}`}
				children={<i className="fas fa-play"></i>}
				onButtonClick={() => setIsPlaying(!isPlaying)}
			/>
			<Button
				btnClass={isPlaying ? `${styles.pause}` : `${styles.hidden}`}
				children={<i className="fas fa-pause"></i>}
				onButtonClick={() => setIsPlaying(!isPlaying)}
			/>
		</div>
	);
};
