import React from "react";
import styles from "./mediaList.module.scss";

export interface SongItem {
	id: number;
	imgSrc: string;
	mediaTitle: string;
	mediaSubtitle: string;
	like: boolean;
}

export interface MediaProps {
	songItem: SongItem;
	handleToggleLike: Function;
}

export const Media: React.FC<MediaProps> = ({ songItem, handleToggleLike }) => {
	const play: boolean = false;

	const handleClick = (e: React.MouseEvent<HTMLButtonElement>) => {
		e.preventDefault();
		handleToggleLike(e.currentTarget.id);
	};

	return (
		<div className={styles.item}>
			<figure className="image is-48x48">
				<img
					className="is-rounded"
					src={songItem.imgSrc}
					alt={songItem.mediaTitle}
				/>
			</figure>
			<p className={`${styles.title} title is-4`}>
				{songItem.mediaTitle}
			</p>
			<p className={`${styles.subtitle} subtitle is-6`}>
				{songItem.mediaSubtitle}
			</p>
			<button
				id={songItem.id.toString()}
				className={`button is-dark icon ${styles.likeBtn} ${
					songItem.like ? styles.likeBtnActive : ""
				}`}
				onClick={handleClick}
			>
				<i className="fas fa-heart"></i>
			</button>
			<button
				id={songItem.id.toString()}
				className={`button is-dark icon play-btn ${
					play ? styles.playBtnActive : ""
				}`}
			>
				<i className="fas fa-play"></i>
			</button>
			{/* <button
                    id={songItem.id.toString()}
                    className="button is-dark icon pause-btn"
                >
                    <i className="fas fa-pause"></i>
                </button> */}
		</div>
	);
};
