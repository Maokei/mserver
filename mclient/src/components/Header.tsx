import React from "react";

export const Header = () => {
    return (
        <header>
            <div data-testid="logo" className="logo">
                Logo here!
            </div>

            <form data-testid="search" action="">
                <input type="text" placeholder="search" />
            </form>
        </header>
    );
};
