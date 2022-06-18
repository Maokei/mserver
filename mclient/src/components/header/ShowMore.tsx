import React from "react";
import { Link } from "react-router-dom";
import { ShowMoreIcon } from "../../assets/union-vector";

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
			className={`column dropdown is-right ${dropdownActiveClass} show-more-wrapper`}
		>
			<div className="dropdown-trigger">
				<button
					data-testid="show-more"
					className="button icon"
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
						<Link to={"/"}>Home</Link>
					</div>
					<div
						data-testid="dropdown-item-test"
						className="dropdown-item"
					>
						<Link to={"/login"}>Login</Link>
					</div>
					<div className="dropdown-item">
						<Link to={"/signup"}>Sign Up</Link>
					</div>
				</div>
			</div>
		</div>
	);
};
