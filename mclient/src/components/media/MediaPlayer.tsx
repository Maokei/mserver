import React, { useEffect, useState } from "react";
import styles from "./mediaPlayer.module.scss";

const MediaPlayer = (/* { foreignId }: { foreignId: string } */) => {
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
			<p className="is-size-1 has-text-white mt-6">MediaPlayer</p>
			<div className={styles.test}>
				{loading && <div>A moment please...</div>}
				{error && (
					<div>{`There is a problem fetching media data - ${error}`}</div>
				)}
				<ul>
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
								<li key={id}>
									<p>{title}</p>
									<audio
										src={`http://localhost:8080/api/v1/media/${foreignId}`}
										style={{
											width: "720px",
											height: "480px",
										}}
										controls
										preload="none"
									></audio>
								</li>
							)
						)}
				</ul>
			</div>
		</main>
	);
};

export default MediaPlayer;
