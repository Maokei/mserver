import React from "react";
import { ReactComponent as SearchIcon } from "../assets/search-vector.svg";
import { ReactComponent as MoreIcon } from "../assets/union-vector.svg";
// import { ReactComponent as BackIcon } from "../assets/back-vector.svg";

interface HeaderProps {
    onClick: () => void;
}

export const Header: React.FC<HeaderProps> = ({ onClick }) => {
    return (
        <header className="columns header">
            {/* <div data-testid="back-btn" className="icon">
                <BackIcon />
            </div> */}

            <div
                data-testid="search"
                className="control column is-four-fifths search-wrapper"
            >
                <button className="icon" onClick={onClick}>
                    <SearchIcon />
                </button>
                <input className="input" type="text" placeholder="search" />
            </div>

            <div className="column show-more-wrapper">
                <button data-testid="show-more" className="button icon">
                    <MoreIcon />
                </button>
            </div>
        </header>
    );
};
