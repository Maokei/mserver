import React from "react";
import { ReactComponent as SearchIcon } from "../assets/search-vector.svg";
import { ReactComponent as MoreIcon } from "../assets/union-vector.svg";
// import { ReactComponent as BackIcon } from "../assets/back-vector.svg";

export const Header = () => {
    return (
        <header className="columns header">
            {/* <div data-testid="back-btn" className="icon">
                <BackIcon />
            </div> */}

            <div
                data-testid="search-wrapper"
                className="column is-four-fifths wrapper"
            >
                <div className="columns">
                    <button className="icon">
                        <SearchIcon />
                    </button>
                    <form data-testid="search" action="" className="column">
                        <input type="text" placeholder="search" />
                    </form>
                </div>
            </div>

            <div className="column show-more-wrapper">
                <button data-testid="show-more" className="button icon">
                    <MoreIcon />
                </button>
            </div>
        </header>
    );
};
