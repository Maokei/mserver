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
			<li key={id} className={`${styles.item} card`}>
				<div className="card-image">
					<figure className="image is-4by3">
						<img
							src={
								"https://bulma.io/images/placeholders/1280x960.png"
							}
							alt={title}
						/>
					</figure>
				</div>
				<div className="card-content">
					<div className="media">
						<div className="media-content">
							<p className="title is-4 is-size-6-mobile">
								{title}
							</p>
						</div>
					</div>
					<div className="content">
						<video
							src={`${baseUrl}/${foreignId}`}
							controls
							preload="none"
						></video>
					</div>
				</div>
				{/* <figure className="image is-4by3">
					<img
						src={"https://bulma.io/images/placeholders/96x96.png"}
						alt={title}
					/>
				</figure>
				<h3 className="title is-3 is-size-6-mobile">{title}</h3> */}
			</li>
		</>
	);
};

export default MediaItem;
