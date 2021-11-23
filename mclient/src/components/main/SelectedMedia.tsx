import React from "react";
import { MediaButtons } from "./MediaButtons";
import { MediaPlaying } from "./MediaPlaying";
import { MediaProgress } from "./MediaProgress";

export const SelectedMedia = () => {
    return (
        <section className="selectedMedia-wrapper">
            <MediaProgress />

            <MediaPlaying />

            <MediaButtons />
        </section>
    );
};
