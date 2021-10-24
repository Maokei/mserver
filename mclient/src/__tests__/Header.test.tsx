import React from "react";
import { getByTestId, render } from "@testing-library/react";
import { Header } from "../components/Header";

let container: any = null;

beforeEach(() => {
    container = render(<Header />).container;
});

describe("Page should have a header component", () => {
    it("should show logo", () => {
        expect(getByTestId(container, "logo")).toBeTruthy();
    });

    it("should show searchbar", () => {
        expect(getByTestId(container, "search")).toBeTruthy();
    });
});
