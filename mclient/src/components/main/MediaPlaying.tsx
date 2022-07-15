import styles from "./mediaPlaying.module.scss";

export const MediaPlaying = () => {
	return (
		<div data-testid="now-playing" className={`${styles.wrapper} medium`}>
			<div className={styles.art}>
				<figure className="image is-128x128">
					<img
						className="is-rounded"
						src="https://bulma.io/images/placeholders/96x96.png"
						alt="warhammer ch3"
					/>
				</figure>
			</div>
			<p className={`subtitle is-6 ${styles.album}`}>warhammer ch3</p>
			<p className={`title is-4 ${styles.title}`}>Dark Imperioum</p>
			<p className={`subtitle is-6 ${styles.artist}`}>Guy Hayley</p>
		</div>
	);
};
