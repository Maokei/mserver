import React from "react";
import { ReactComponent as SearchIcon } from "../../assets/search-vector.svg";

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
    const hiddenClass = hidden ? "hidden" : "";

    return (
        <div
            data-testid="search"
            className="control column is-four-fifths search-wrapper"
        >
            <button
                data-testid="search-btn"
                className="button icon search-btn"
                onClick={onClick}
            >
                {/* <SearchIcon /> */}
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
