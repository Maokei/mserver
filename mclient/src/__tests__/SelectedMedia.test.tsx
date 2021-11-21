import * as ReactDOM from "react-dom";
import { getByTestId } from "@testing-library/react";
import { SelectedMedia } from "../components/main/SelectedMedia";

let container: HTMLDivElement;

beforeEach(() => {
    container = document.createElement("div");
    document.body.appendChild(container);
    ReactDOM.render(<SelectedMedia />, container);
});

afterEach(() => {
    document.body.removeChild(container);
    container.remove();
});

describe("Should show selected media when user choose a single item to play", () => {
    it("should show progress bar", () => {
        expect(getByTestId(container, "progressbar")).toBeTruthy();
    });

    it("should show now playing single media", () => {
        expect(getByTestId(container, "now-playing")).toBeTruthy();
    });

    it("should have buttons", () => {
        expect(getByTestId(container, "button-group")).toBeTruthy();
    });

    /* it("button-groups should have children", () => {
        const btnGroup = container.getElementsByClassName("buttons-wrapper");
        console.log(btnGroup);
    }); */
});
