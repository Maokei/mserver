import React from "react";
import { ReactComponent as MoreIcon } from "../assets/union-vector.svg";

export const ShowMore = () => {
    const ref = React.useRef<HTMLDivElement>(null);
    const [isActive, setIsActive] = React.useState<boolean>(false);
    const dropdownActiveClass = isActive ? "is-active" : "";

    React.useEffect(() => {
        const checkIfClickedOutside = (e: MouseEvent) => {
            if (
                isActive &&
                ref.current &&
                !ref.current.contains(e.target as HTMLElement)
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
        <div
            className={`column dropdown is-right ${dropdownActiveClass} show-more-wrapper`}
        >
            <div className="dropdown-trigger">
                <button
                    data-testid="show-more"
                    className="button icon"
                    aria-haspopup="true"
                    aria-controls="dropdown-menu"
                    onClick={() => setIsActive(!isActive)}
                >
                    <MoreIcon />
                </button>
            </div>

            <div
                ref={ref}
                className="dropdown-menu"
                id="dropdown-menu"
                role="menu"
            >
                <div className="dropdown-content">
                    <div className="dropdown-item">Dropdown item</div>
                    <div className="dropdown-item">Other dropdown item</div>
                    <div className="dropdown-item is-active">
                        Active dropdown item
                    </div>
                </div>
            </div>
        </div>
    );
};
