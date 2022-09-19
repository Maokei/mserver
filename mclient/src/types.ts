export type ItemProps = {
    id: string;
    // imgSrc: string;
    title: string;
    mediaId: string;
    media: string;
    setId: (e: string) => void;
};

export type CardProps = {
    id: string;
    item: ItemProps;
};
