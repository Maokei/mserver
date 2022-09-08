import React, { ChangeEvent } from "react";
import { Link } from "react-router-dom";
import { Search } from "./Search";
import { ShowMore } from "./ShowMore";
import styles from "./header.module.scss";
import { BackIcon } from "../../assets/back-vector";

export const Header = () => {
    const [inputTexts, setInputTexts] = React.useState<string>("");

    const handleKeyDown = (e: ChangeEvent<HTMLInputElement>) => {
        console.log(e.target.value);
    };

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
        <header className={`${styles.header}`}>
            {window.location.pathname === "/" ||
            window.location.pathname === "/library" ? (
                <Search
                    hidden={isHidden}
                    onClick={toggleSearchbar}
                    value={inputTexts}
                    setKeywords={setInputTexts}
                    onKeyDown={handleKeyDown}
                />
            ) : (
                <div
                    data-testid="back-btn"
                    className={`icon ${styles.backBtn}`}
                >
                    <Link to="/">
                        <BackIcon />
                    </Link>
                </div>
            )}

            <ShowMore
                dropdownRef={ref}
                state={isActive}
                setState={setIsActive}
            />
        </header>
    );
};
