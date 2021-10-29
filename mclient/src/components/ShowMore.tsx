import React from "react";
import { ReactComponent as MoreIcon } from "../assets/union-vector.svg";

interface ShowMoreProps {
    ref: any;
    state: boolean;
    setState: Function;
}

export const ShowMore: React.FC<ShowMoreProps> = ({ ref, state, setState }) => {
    const dropdownActiveClass = state ? "is-active" : "";

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
                    onClick={() => setState(!state)}
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
