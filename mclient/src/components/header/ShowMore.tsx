import React from "react";
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
						Dropdown item
					</div>
					<div className="dropdown-item">Other dropdown item</div>
					<div className="dropdown-item is-active">
						Active dropdown item
					</div>
				</div>
			</div>
		</div>
	);
};
