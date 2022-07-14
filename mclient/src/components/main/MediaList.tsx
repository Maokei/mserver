import * as React from "react";
import { Media, SongItem } from "./Media";
import styles from "./mediaList.module.scss";

interface MediaPropsList {
	items: SongItem[];
	handleToggleLike: Function;
}

export const MediaList: React.FC<MediaPropsList> = ({
	items,
	handleToggleLike,
}) => {
	return (
		<section data-testid="mediaList-wrapper" className={styles.wrapper}>
			<div className={styles.divider} />
			<div
				data-testid="mediaList"
				className={`${styles.mediaList} clips`}
			>
				{items.map((item) => (
					<Media
						key={item.id}
						songItem={item}
						handleToggleLike={handleToggleLike}
					/>
				))}
			</div>
		</section>
	);
};
