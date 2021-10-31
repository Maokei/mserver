import React from "react";
import { Search } from "./Search";
import { ShowMore } from "./ShowMore";

export const Header = () => {
    const [isHidden, setIsHidden] = React.useState<boolean>(true);

    const toggleSearchbar = () => {
        setIsHidden(!isHidden);
    };

    const ref = React.useRef<HTMLDivElement>(null);
    const [isActive, setIsActive] = React.useState<boolean>(false);

    React.useEffect(() => {
        const checkIfClickedOutside = (e: MouseEvent) => {
            if (
                isActive &&
                ref.current &&
                !ref.current.contains(e.target as Node)
            ) {
                setIsActive(false);
            }
        };

        document.addEventListener("mousedown", checkIfClickedOutside);

        return () => {
            document.removeEventListener("mousedown", checkIfClickedOutside);
        };
    }, [isActive]);

    return (
        <header className="columns header">
            {/* <div data-testid="back-btn" className="icon">
                <BackIcon />
            </div> */}

            <Search hidden={isHidden} onClick={toggleSearchbar} />

            <ShowMore
                dropdownRef={ref}
                state={isActive}
                setState={setIsActive}
            />
        </header>
    );
};
