import { useState, useEffect, useRef } from "react";
import styles from "./media.module.scss";
import Player from "./player/Player";

const Media = ({
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
	const [clips, setClips] = useState([]);
	const [loading, setLoading] = useState(true);
	const [error, setError] = useState(null);
	// const [clips, setClips] = useState(dummyData);
	const [isPlaying, setIsPlaying] = useState(false);
	const [currentMedia, setCurrentMedia] = useState({});
	console.log(currentMedia);

	const mediaElement = useRef<any>();

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
				setClips(data);
				setError(null);
			} catch (error: any) {
				setError(error.message);
				setClips([]);
			} finally {
				setLoading(false);
			}
		};
		getData();
	}, []);

	useEffect(() => {
		if (isPlaying) {
			mediaElement.current?.play();
		} else {
			mediaElement?.current?.pause();
		}
	}, [isPlaying]);

	const onPlaying = () => {
		const duration = mediaElement.current?.duration;
		const currentTime = mediaElement.current?.currentTime;

		setCurrentMedia({
			// ...currentMedia,
			// @ts-ignore
			progress: (currentTime / duration) * 100,
			length: duration,
		});
	};

	return (
		<>
			<li key={id} className={`${styles.item} card`}>
				<div className="card-content">
					{/* {currentMedia && !currentMedia.mediaUrl.includes("mp4") ? ( */}
					<div className={`card-image ${styles.imageContainer}`}>
						<figure className="image is-4by3">
							<img
								src={
									"https://bulma.io/images/placeholders/1280x960.png"
								}
								alt={title}
							/>
						</figure>
					</div>
					{/* ) : null} */}
					<div className={`${styles.contentContainer} content`}>
						{/* {currentMedia.mediaUrl.includes("mp4") ? ( */}
						<video id="media-file">
							<source src={`${baseUrl}/${foreignId}`} />
							Your browser does not support the <code>
								audio
							</code>{" "}
							element.
						</video>
						{/* <video
						src={currentMedia.mediaUrl}
						ref={mediaElement}
						onTimeUpdate={onPlaying}
						>
							<source
								src={`${baseUrl}/${foreignId}`}
								onTimeUpdate={onPlaying}
							/>
							Your browser does not support the <code>audio</code>{" "}
							element.
						</video> */}
						) : (
						<audio
						// src={currentMedia.mediaUrl}
						// ref={mediaElement}
						// onTimeUpdate={onPlaying}
						>
							<source src={`${baseUrl}/${foreignId}`} />
							Your browser does not support the <code>
								audio
							</code>{" "}
							element.
						</audio>
						{/* )} */}
						<div className="controls">
							{currentMedia && (
								<Player
									mediaElement={mediaElement}
									clips={clips}
									setClips={setClips}
									isPlaying={isPlaying}
									setIsPlaying={setIsPlaying}
									// @ts-ignore
									currentMedia={currentMedia}
									setCurrentMedia={setCurrentMedia}
								/>
							)}
						</div>
					</div>
				</div>
			</li>
		</>
	);
};

export default Media;
