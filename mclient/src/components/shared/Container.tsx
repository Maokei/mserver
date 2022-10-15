import { ReactNode } from "react";
import styles from "../sidebar/sidebar.module.scss";

const Container = ({
    isSidebar,
    children,
}: {
    isSidebar: boolean;
    children: ReactNode;
}) => {
    return (
        <div className={!isSidebar ? `${styles.sidebar}` : ``}>{children}</div>
    );
};

export default Container;
