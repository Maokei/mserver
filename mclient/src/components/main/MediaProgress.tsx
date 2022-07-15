import styles from "./mediaProgress.module.scss";

export const MediaProgress = () => {
	const currentValue = "20";

	return (
		<div className={`content ${styles.wrapper}`}>
			<p id="timestamp" className={styles.timestamp}>
				00:48
			</p>

			<progress
				data-testid="progressbar"
				id="progressbar"
				className={`progress is-small ${styles.progressbar} ${styles.isOrange}`}
				value={currentValue}
				max="100"
			>
				{currentValue}
			</progress>

			<p id="timestamp" className={styles.timestamp}>
				03:54
			</p>
		</div>
	);
};
