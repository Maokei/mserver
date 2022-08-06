import { useState, useEffect } from "react";
import styles from "./mediaItem.module.scss";
import Pause from "./player/Pause";
import Play from "./player/Play";
import ProgressBar from "./player/ProgressBar";

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
	const [playing, setPlaying] = useState(false);
	const [duration, setDuration] = useState();
	const [currentTime, setCurrentTime] = useState();

	useEffect(() => {
		const mediaFile: HTMLElement | any =
			document.getElementById("media-file");

		setDuration(mediaFile.duration);
		setCurrentTime(mediaFile.currentTime);

		playing ? mediaFile?.play() : mediaFile?.pause();
	}, [playing]);

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
						<video id="media-file">
							<source src={`${baseUrl}/${foreignId}`} />
							Your browser does not support the <code>
								audio
							</code>{" "}
							element.
						</video>
						<div className="controls">
							{playing ? (
								<Pause handleClick={() => setPlaying(false)} />
							) : (
								<Play
									handlePlayClick={() => setPlaying(true)}
								/>
							)}
							<ProgressBar
								duration={duration}
								currentTime={currentTime}
							/>
						</div>
					</div>
				</div>
			</li>
		</>
	);
};

export default MediaItem;
