import React, { useEffect, useState } from "react";
import MediaItem from "./MediaItem";
import styles from "./mediaPlayer.module.scss";

const MediaPlayer = () => {
	const [data, setData] = useState([]);
	const [loading, setLoading] = useState(true);
	const [error, setError] = useState(null);

	useEffect(() => {
		const getData = async () => {
			try {
				const response = await fetch(
					"http://localhost:8080/api/v1/media"
				);
				if (!response.ok) {
					throw new Error(
						`This is a HTTP error with a status of ${response.status}`
					);
				}
				const data = await response.json();
				setData(data);
				setError(null);
			} catch (error: any) {
				setError(error.message);
				setData([]);
			} finally {
				setLoading(false);
			}
		};
		getData();
	}, []);

	return (
		<main className={styles.container}>
			<h1 className="is-size-1 has-text-white mt-6">Library</h1>
			<div className={styles.test}>
				{loading && <div>A moment please...</div>}
				{error && (
					<div>{`There is a problem fetching media data - ${error}`}</div>
				)}
				<ul className={styles.mediaList}>
					{data &&
						data.map(
							({
								id,
								title,
								foreignId,
							}: {
								id: string;
								title: string;
								foreignId: string;
							}) => (
								<MediaItem
									key={id}
									baseUrl={
										"http://localhost:8080/api/v1/media"
									}
									id={id}
									title={title}
									foreignId={foreignId}
								/>
							)
						)}
				</ul>
			</div>
		</main>
	);
};

export default MediaPlayer;
