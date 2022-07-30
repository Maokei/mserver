import styles from "./mediaItem.module.scss";

const MediaItem = ({
	baseUrl,
	id,
	title,
	foreignId,
}: {
	baseUrl: string;
	id: string;
	title: string;
	foreignId: string;
}) => {
	return (
		<>
			<li key={id} className={styles.item}>
				<figure className="image is-48x48">
					<img
						className="is-rounded"
						src={
							"https://bulma.io/images/placeholders/1280x960.png"
						}
						alt={title}
					/>
				</figure>
				<h3 className="title is-3 is-size-6-mobile">{title}</h3>
				<video
					src={`${baseUrl}/${foreignId}`}
					style={{
						width: "720px",
						height: "480px",
					}}
					controls
					preload="none"
				></video>
			</li>
		</>
	);
};

export default MediaItem;
