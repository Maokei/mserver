import React from "react";

import { SearchIcon } from "../../assets/SearchIcon";
import styles from "./search.module.scss";
import headerStyles from "./header.module.scss";

interface SearchProps {
	onClick: () => void;
	hidden: boolean;
	value: string;
	setKeywords: any;
	onKeyDown: any;
}

export const Search: React.FC<SearchProps> = ({
	onClick,
	hidden,
	value,
	setKeywords,
	onKeyDown,
}) => {
	const hiddenClass = hidden ? `${styles.hidden}` : "";

	return (
		<div
			data-testid="search"
			className={`control column is-four-fifths ${styles.wrapper}`}
		>
			<button
				data-testid="search-btn"
				className={`button ${headerStyles.icon} ${styles.btn}`}
				onClick={onClick}
			>
				<SearchIcon />
			</button>
			<input
				data-testid="search-input"
				className={`input is-large ${hiddenClass}`}
				type="text"
				placeholder="search"
				value={value}
				onChange={(e: any) => setKeywords(e.target.value)}
				onKeyDown={onKeyDown}
			/>
		</div>
	);
};
