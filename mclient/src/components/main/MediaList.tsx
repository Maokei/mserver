import * as React from "react";
import { Media, MediaProps } from "./Media";

interface MediaListProps {
    // items: MediaProps[];
    items: any[];
}

export const MediaList: React.FC<MediaListProps> = ({ items }) => {
    const [isLiked, setIsLiked] = React.useState<boolean>(false);
    const [currentItem, setCurrentItem] = React.useState<number>(0);

    const handleLikeColor = (likeId: number) => {
        setCurrentItem(likeId);
        setIsLiked(!isLiked);
        console.log(likeId);
    };

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
                        likeState={isLiked}
                        likeId={item.id}
                        activeItem={currentItem}
                        handleClick={handleLikeColor}
                    />
                ))}
            </div>
        </section>
    );
};
