import React from "react";
import { getByTestId, cleanup, render } from "@testing-library/react";
import { shallow } from "enzyme";
import { Header } from "../components/Header";

let container: any = null;

beforeEach(() => {
    container = render(<Header />).container;
});

describe("Page should have a header component", () => {
    it("should show show-more-icon", () => {
        expect(getByTestId(container, "show-more")).toBeTruthy();
    });

    it("should have a dropdown menu", () => {
        expect(getByTestId(container, "dropdown-menu-test")).toBeTruthy();
    });

    it("should show search", () => {
        expect(getByTestId(container, "search")).toBeTruthy();
    });

    it("should have a show-more-wrapper", () => {
        expect(getByTestId(container, "show-more-wrapper")).toBeTruthy();
    });

    it("should have a search button", () => {
        expect(getByTestId(container, "search-btn")).toBeTruthy();
    });

    it("should have a search input", () => {
        expect(getByTestId(container, "search-input")).toBeTruthy();
    });
});

afterEach(cleanup);

describe("Test Button component", () => {
    it("Test click event", () => {
        const mockCallBack = jest.fn();

        const button = shallow(<button onClick={mockCallBack}>Ok!</button>);
        button.find("button").simulate("click");
        expect(mockCallBack.mock.calls.length).toEqual(1);
    });
});
