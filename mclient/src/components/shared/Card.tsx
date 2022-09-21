import styles from "../home/home.module.scss";
import { ItemProps } from "../../types";

export const Card = ({ id, title, setId, mediaId, media }: ItemProps) => {
	return (
		<>
			{mediaId === id && (
				<div
					className={`${styles.card} card`}
					onClick={() => {
						console.log(id);
						setId(mediaId);
					}}
				>
					<div className="card-image">
						<figure className="image is-1by1">
							<img
								className="is-rounded"
								src={
									"https://bulma.io/images/placeholders/128x128.png"
								}
								alt={title}
							/>
						</figure>
						<div className={`${styles.album} media-content`}>
							<p className="title is-4">{title}</p>
						</div>
						<audio src={media} />
					</div>
				</div>
			)}
		</>
	);
};
