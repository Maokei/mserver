import * as React from "react";
import { Media, MediaProps } from "./Media";

interface MediaListProps {
    items: MediaProps[];
    handleToggle: Function;
}

export const MediaList: React.FC<MediaListProps> = ({
    items,
    handleToggle,
}) => {
    return (
        <section data-testid="mediaList-wrapper" className="mediaList-wrapper">
            <div className="divider"></div>
            <div data-testid="mediaList" className="mediaList clips">
                {items.map((item) => (
                    <Media
                        key={item.id}
                        id={item.id}
                        imgSrc={item.imgSrc}
                        mediaTitle={item.mediaTitle}
                        mediaSubtitle={item.mediaSubtitle}
                        playState={item.play}
                        likeState={item.like}
                        handleToggle={handleToggle}
                        play={false}
                        like={false}
                    />
                ))}
            </div>
        </section>
    );
};
