import styles from "../home/home.module.scss";
import { ItemProps } from "../../types";

export const Card = (item: ItemProps) => {
    return (
        <div className={`${styles.card} card`}>
            <div className="card-image">
                <figure className="image is-1by1">
                    <img
                        className="is-rounded"
                        src={"https://bulma.io/images/placeholders/128x128.png"}
                        alt={item.title}
                    />
                </figure>
                <div className={`${styles.album} media-content`}>
                    <p className="title is-4">{item.title}</p>
                </div>
            </div>
        </div>
    );
};
