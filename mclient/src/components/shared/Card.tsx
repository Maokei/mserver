import React from "react";
import styles from "../home/home.module.scss";
import { ItemProps } from "../../types";
import { EditVector } from "../../assets/edit-vector";
import Modal from "../modal/Modal";
import useModal from "../../hooks/useModal";

export const Card = ({ id, title, setId, mediaId, media }: ItemProps) => {
    const { isShowing, toggle } = useModal();

    return (
        <>
            {mediaId === id && (
                <div className={`${styles.card} card`}>
                    <Modal
                        isShowing={isShowing}
                        hide={toggle}
                        modalName="Edit info"
                    />
                    <button
                        className={`button ${styles.editButton} ${styles.defaultButton}`}
                        onClick={toggle}
                    >
                        <EditVector />
                    </button>
                    <div
                        className={styles.cardImage}
                        onClick={() => {
                            setId(mediaId);
                        }}
                    >
                        <figure className="image is-1by1">
                            <img
                                className="is-rounded"
                                src={
                                    "https://bulma.io/images/placeholders/128x128.png"
                                }
                                alt={title}
                            />
                        </figure>
                        <div
                            className={`${styles.album} media-content`}
                            onClick={() => {
                                setId(mediaId);
                            }}
                        >
                            <p className="title is-4">{title}</p>
                        </div>
                        <audio src={media} />
                    </div>
                </div>
            )}
        </>
    );
};
