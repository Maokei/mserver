import React from "react";
import { useMediaData } from "../../context/mediaDataContext";
import Button from "../shared/Button";
import styles from "./player.module.scss";

type MediaProps = {
	id: string;
	setId: (e: string) => void;
};

const Player = ({ id, setId }: MediaProps) => {
	const { data, loading, error } = useMediaData();

	const [isPlaying, setIsPlaying] = React.useState<boolean>(false);
	const [volume, setVolume] = React.useState<string>("1");
	const [isMuted, setIsMuted] = React.useState<boolean>(false);

	// let testAudio = new Audio();
	// console.log(testAudio);
	const mediaTag = React.useRef<any>(new Audio());

	React.useEffect(() => {
		if (id !== "") {
			const media = data && data.find((d) => d.id === id);

			if (media) {
				const mediaUrl = `http://localhost:8080/api/v1/media/${media?.foreignId}`;
				console.log("is playing", mediaTag.current);
				mediaTag.current.setAttribute("src", mediaUrl);
				//mediaTag.current.play();
			} else {
				mediaTag.current.pause();
			}
		}
	}, [id]);

	// const testAudioPlayPause = () => {
	//     // console.log(id);
	//     const song = data.find((d) => d.id === id);
	//     const url = `http://localhost:8080/api/v1/media/${song?.foreignId}`;
	//     testAudio = new Audio(url);
	//     testAudio.play();
	// };

	const skipToPrevious = () => {
		console.log("previous");
	};

	const skipToNext = () => {
		console.log("next");
	};

	const shuffleMedia = () => {
		console.log("shuffle");
	};

	return (
		<div className={styles.wrapper}>
			<div className={styles.mediaContainer}>
				{data &&
					data.map(
						(item) =>
							id === item.id && (
								<div key={item.id} className={styles.mediaItem}>
									<img
										src={
											"https://bulma.io/images/placeholders/128x128.png"
										}
										alt={item.title}
									/>
									<div>
										<h1>{item.title}</h1>
										<h3>{"Author/Album"}</h3>
									</div>
									<audio
										src={`http://localhost:8080/api/v1/media/${item.foreignId}`}
									></audio>
								</div>
							)
					)}
			</div>
			<div className={styles.playerContainer}>
				<div className={styles.progressBar}>
					<p className={styles.currentTime}>
						{/* {calculateDuration(currentTime)} */}
						1:00
					</p>
					<input
						type="range"
						className={styles.currentProgress}
						defaultValue="0"
						// ref={progressBar}
						// onChange={changeRange}
					/>

					<p className={styles.duration}>
						{/* {(duration && !isNaN(duration)) && 
                      calculateDuration(duration)} */}
						3:00
					</p>
				</div>

				<div className={styles.controls}>
					<Button
						btnClass={`shuffle`}
						children={<i className="fas fa-random"></i>}
						onButtonClick={shuffleMedia}
					/>
					<Button
						btnClass={`previous`}
						children={<i className="fas fa-caret-left"></i>}
						onButtonClick={skipToPrevious}
					/>
					{isPlaying ? (
						<Button
							btnClass={`pause`}
							children={<i className="fas fa-pause"></i>}
							onButtonClick={() => {
								// testAudioPlayPause();
								setIsPlaying(!isPlaying);
							}}
						/>
					) : (
						<Button
							btnClass={`play`}
							children={<i className="fas fa-play"></i>}
							onButtonClick={() => {
								// testAudioPlayPause();
								setIsPlaying(!isPlaying);
							}}
						/>
					)}
					<Button
						btnClass={`next`}
						children={<i className="fas fa-caret-right"></i>}
						onButtonClick={skipToNext}
					/>
				</div>
			</div>
			<div className={styles.volumeContainer}>
				{isMuted ? (
					<Button
						btnClass={`volume`}
						children={<i className="fas fa-volume-up"></i>}
						onButtonClick={() => setIsMuted(!isMuted)}
					/>
				) : (
					<Button
						btnClass={`volume`}
						children={<i className="fas fa-volume-off"></i>}
						onButtonClick={() => setIsMuted(!isMuted)}
					/>
				)}
				<input
					type="range"
					step="0.01"
					onChange={(e) => setVolume(e.target.value)}
					value={volume}
					max="1"
					min="0"
				/>
			</div>
		</div>
	);
};

export default Player;
