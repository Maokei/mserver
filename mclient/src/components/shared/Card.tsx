import styles from "../home/home.module.scss";

export type ItemProps = {
    id: number;
    imgSrc: string;
    mediaTitle: string;
    // mediaSubtitle: string;
    // like: boolean;
};

export type CardProps = {
    id: any | null | undefined;
    item: ItemProps;
};

export const Card = () => {
    return (
        <div className={`${styles.card} card`}>
            <div className="card-image">
                <figure className="image is-1by1">
                    <img
                        className="is-rounded"
                        src={"https://bulma.io/images/placeholders/128x128.png"}
                        alt={"title"}
                    />
                </figure>
                <div className={`${styles.album} media-content`}>
                    <p className="title is-4">Album</p>
                </div>
            </div>
        </div>
    );
};
