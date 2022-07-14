import React from "react";
import styles from "./mediaButtons.module.scss";

export interface ButtonClick {
	onClickPlay: Function;
}

export const MediaButtons: React.FC<ButtonClick> = ({ onClickPlay }) => {
	return (
		<div data-testid="button-group" className={styles.wrapper}>
			<button className={`button is-dark icon ${styles.previous}`}>
				<i className="fas fa-caret-left"></i>
			</button>
			<button
				className={`button is-dark icon ${styles.play}`}
				onClick={() => console.log("play")}
			>
				<i className="fas fa-play"></i>
			</button>
			{/* <button className="button is-dark icon icon-pause">
                <i className="fas fa-pause"></i>
            </button> */}
			<button className={`button is-dark icon ${styles.next}`}>
				<i className="fas fa-caret-right"></i>
			</button>
			<button className={`button is-dark icon ${styles.shuffle}`}>
				<i className="fas fa-random"></i>
			</button>
			<button className={`button is-dark icon ${styles.volume}`}>
				<i className="fas fa-volume-up"></i>
			</button>
		</div>
	);
};
