import React from "react";
import { ReactComponent as SearchIcon } from "../assets/search-vector.svg";
import { ReactComponent as MoreIcon } from "../assets/union-vector.svg";
// import { ReactComponent as BackIcon } from "../assets/back-vector.svg";

export const Header = () => {
    return (
        <header className="header">
            {/* <div data-testid="back-btn" className="icon">
                <BackIcon />
            </div> */}

            <div data-testid="search-wrapper" className="wrapper">
                <form data-testid="search" action="">
                    <SearchIcon />
                    <input type="text" placeholder="search" />
                </form>
            </div>

            <button data-testid="show-more" className="button icon">
                <MoreIcon />
            </button>
        </header>
    );
};
