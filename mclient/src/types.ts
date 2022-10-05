export type ItemProps = {
    id: string;
    title: string;
    mediaId: string;
    media: string;
    setId: (e: string) => void;
};

export type CardProps = {
    id: string;
    item: ItemProps;
};

export type ModalProps = {
    isShowing: boolean;
    hide: () => void;
    modalName: string;
    labelText: string[];
    isAudio?: boolean;
    isVideo?: boolean;
    fileType?: string;
};
