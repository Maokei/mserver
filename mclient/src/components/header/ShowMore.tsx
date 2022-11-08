import React from "react";
import { Link } from "react-router-dom";
import { ShowMoreIcon } from "../../assets/union-vector";
import useModal from "../../hooks/useModal";
import Modal from "../modal/Modal";
import headerStyle from "./header.module.scss";
import styles from "./showMore.module.scss";

interface ShowMoreProps {
    dropdownRef: any;
    state: boolean;
    setState: Function;
}

export const ShowMore: React.FC<ShowMoreProps> = ({
    dropdownRef,
    state,
    setState,
}) => {
    const { isShowing, toggle } = useModal();
    const dropdownActiveClass = state ? "is-active" : "";
    const toggleDownload = () => {
        toggle();
        setState(!state);
    };

    return (
        <div
            data-testid="dropdown-test"
            className={` dropdown  ${dropdownActiveClass} ${styles.wrapper}`}
        >
            <div className="dropdown-trigger">
                <button
                    data-testid="show-more"
                    className={`button ${headerStyle.icon}`}
                    aria-haspopup="true"
                    aria-controls="dropdown-menu"
                    onClick={() => setState(!state)}
                >
                    <ShowMoreIcon />
                </button>
            </div>

            <div
                data-testid="dropdown-menu-test"
                ref={dropdownRef}
                className="dropdown-menu"
                id="dropdown-menu"
                role="menu"
            >
                <div
                    data-testid="dropdown-content-test"
                    className="dropdown-content"
                >
                    <div
                        data-testid="dropdown-item-test"
                        className="dropdown-item"
                    >
                        <Link to={"/"} onClick={() => setState(!state)}>
                            Home
                        </Link>
                    </div>
                    <div
                        data-testid="dropdown-item-test"
                        className="dropdown-item"
                    >
                        <Link to={"/library"} onClick={() => setState(!state)}>
                            Library
                        </Link>
                    </div>
                    <div className="dropdown-item">
                        <button
                            className={styles.downloadButton}
                            onClick={toggleDownload}
                        >
                            Download
                        </button>
                        <Modal
                            isShowing={isShowing}
                            hide={toggle}
                            modalName="Download Youtube clip"
                            labelText={["URL"]}
                            fileType="Audio"
                            isAudio={true}
                        />
                    </div>
                    <div
                        data-testid="dropdown-item-test"
                        className="dropdown-item"
                    >
                        {/* TODO: if authenticated, show Logout */}
                        <Link to={"/login"} onClick={() => setState(!state)}>
                            Login
                        </Link>
                    </div>
                    <div className="dropdown-item">
                        {/* TODO: if authenticated, show Logout */}
                        <Link to={"/signup"} onClick={() => setState(!state)}>
                            Sign Up
                        </Link>
                    </div>
                </div>
            </div>
        </div>
    );
};
