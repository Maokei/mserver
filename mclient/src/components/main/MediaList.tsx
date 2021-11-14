import * as React from "react";
import { Media, SongItem } from "./Media";

interface MediaPropsList {
    items: SongItem[];
    handleToggleLike: Function;
}

export const MediaList: React.FC<MediaPropsList> = ({
    items,
    handleToggleLike,
}) => {
    return (
        <section data-testid="mediaList-wrapper" className="mediaList-wrapper">
            <div className="divider"></div>
            <div data-testid="mediaList" className="mediaList clips">
                {items.map((item) => (
                    <Media
                        key={item.id}
                        songItem={item}
                        handleToggleLike={handleToggleLike}
                    />
                ))}
            </div>
        </section>
    );
};
