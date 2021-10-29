import React from "react";
import { Search } from "./Search";
import { ShowMore } from "./ShowMore";

/* interface HeaderProps {
    onSearch: () => void;
} */

export const Header = () => {
    const [isHidden, setIsHidden] = React.useState<boolean>(true);

    const toggleSearchbar = () => {
        setIsHidden(!isHidden);
    };

    return (
        <header className="columns header">
            {/* <div data-testid="back-btn" className="icon">
                <BackIcon />
            </div> */}

            <Search hidden={isHidden} onClick={toggleSearchbar} />

            <ShowMore />
        </header>
    );
};
