import React from "react";
import { ReactComponent as SearchIcon } from "../assets/search-vector.svg";

interface SearchProps {
    onClick: () => void;
    hidden: boolean;
}

export const Search: React.FC<SearchProps> = ({ onClick, hidden }) => {
    const hiddenClass = hidden ? "hidden" : "";

    return (
        <div
            data-testid="search"
            className="control column is-four-fifths search-wrapper"
        >
            <button className="icon" onClick={onClick}>
                <SearchIcon />
            </button>
            <input
                className={`input is-large ${hiddenClass}`}
                type="text"
                placeholder="search"
            />
        </div>
    );
};
