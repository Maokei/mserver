import React from "react";
import { ReactComponent as SearchIcon } from "../assets/search-vector.svg";

interface SearchProps {
    onClick: () => void;
}

export const Search: React.FC<SearchProps> = ({ onClick }) => {
    return (
        <div
            data-testid="search"
            className="control column is-four-fifths search-wrapper"
        >
            <button className="icon" onClick={onClick}>
                <SearchIcon />
            </button>
            <input className="input" type="text" placeholder="search" />
        </div>
    );
};
