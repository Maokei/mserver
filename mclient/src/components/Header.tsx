import React from "react";
import { Search } from "./Search";
import { ShowMore } from "./ShowMore";

interface HeaderProps {
    onClick: () => void;
}

export const Header: React.FC<HeaderProps> = ({ onClick }) => {
    return (
        <header className="columns header">
            {/* <div data-testid="back-btn" className="icon">
                <BackIcon />
            </div> */}

            <Search onClick={onClick} />

            <ShowMore />
        </header>
    );
};
