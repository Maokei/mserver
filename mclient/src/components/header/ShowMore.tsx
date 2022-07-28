import React from "react";
import { Link } from "react-router-dom";
import { ShowMoreIcon } from "../../assets/union-vector";
import headerStyle from "./header.module.scss";
import styles from "./showMore.module.scss";

interface ShowMoreProps {
	dropdownRef: any;
	state: boolean;
	setState: Function;
}

export const ShowMore: React.FC<ShowMoreProps> = ({
	dropdownRef,
	state,
	setState,
}) => {
	const dropdownActiveClass = state ? "is-active" : "";

	return (
		<div
			data-testid="dropdown-test"
			className={`column dropdown is-right ${dropdownActiveClass} ${styles.wrapper}`}
		>
			<div className="dropdown-trigger">
				<button
					data-testid="show-more"
					className={`button ${headerStyle.icon}`}
					aria-haspopup="true"
					aria-controls="dropdown-menu"
					onClick={() => setState(!state)}
				>
					<ShowMoreIcon />
				</button>
			</div>

			<div
				data-testid="dropdown-menu-test"
				ref={dropdownRef}
				className="dropdown-menu"
				id="dropdown-menu"
				role="menu"
			>
				<div
					data-testid="dropdown-content-test"
					className="dropdown-content"
				>
					<div
						data-testid="dropdown-item-test"
						className="dropdown-item"
					>
						<Link to={"/"} onClick={() => setState(!state)}>
							Home
						</Link>
					</div>
					<div
						data-testid="dropdown-item-test"
						className="dropdown-item"
					>
						<Link to={"/library"} onClick={() => setState(!state)}>
							Library
						</Link>
					</div>
					<div
						data-testid="dropdown-item-test"
						className="dropdown-item"
					>
						<Link to={"/create"} onClick={() => setState(!state)}>
							Create Playlist
						</Link>
					</div>
					<div
						data-testid="dropdown-item-test"
						className="dropdown-item"
					>
						<Link to={"/liked"} onClick={() => setState(!state)}>
							Liked
						</Link>
					</div>
					<div
						data-testid="dropdown-item-test"
						className="dropdown-item"
					>
						{/* TODO: if authenticated, show Logout */}
						<Link to={"/login"} onClick={() => setState(!state)}>
							Login
						</Link>
					</div>
					<div className="dropdown-item">
						{/* TODO: if authenticated, show Logout */}
						<Link to={"/signup"} onClick={() => setState(!state)}>
							Sign Up
						</Link>
					</div>
				</div>
			</div>
		</div>
	);
};
