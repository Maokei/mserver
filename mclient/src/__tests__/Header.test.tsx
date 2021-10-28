import React from "react";
import { getByTestId, render } from "@testing-library/react";
import { Header } from "../components/Header";

let container: any = null;

beforeEach(() => {
    container = render(<Header onClick={() => "test"} />).container;
});

describe("Page should have a header component", () => {
    it("should show show-more-icon", () => {
        expect(getByTestId(container, "show-more")).toBeTruthy();
    });

    it("should have a show-more-wrapper", () => {
        expect(getByTestId(container, "show-more-wrapper")).toBeTruthy();
    });

    it("should show searchbar", () => {
        expect(getByTestId(container, "search")).toBeTruthy();
    });
});
