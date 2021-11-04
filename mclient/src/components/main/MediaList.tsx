import * as React from "react";
import { Media, MediaProps } from "./Media";

interface MediaListProps {
    items: MediaProps[];
}

export const MediaList: React.FC<MediaListProps> = ({ items }) => {
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
                        like={item.like}
                        play={item.play}
                        // onPlayClick={handlePlayClick}
                    />
                ))}
            </div>
        </section>
    );
};
