import React from "react";
import {
    getByTestId,
    // cleanup,
    fireEvent,
    render,
    screen,
    getAllByRole,
} from "@testing-library/react";
import renderer from "react-test-renderer";
import { shallow } from "enzyme";
import { Header } from "../components/Header";
import { ShowMore } from "../components/ShowMore";
import { Search } from "../components/Search";
import userEvent from "@testing-library/user-event";

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

    it("should have a search button", () => {
        expect(getByTestId(container, "search-btn")).toBeTruthy();
    });

    it("should have a search input", () => {
        expect(getByTestId(container, "search-input")).toBeTruthy();
    });
});

// afterEach(cleanup);

describe("Test input functions", () => {
    it("render input", () => {
        const tree = renderer
            .create(
                <Search
                    value="something"
                    onKeyDown={() => {}}
                    setKeywords={() => {}}
                    onClick={function (): void {
                        throw new Error("Function not implemented.");
                    }}
                    hidden={false}
                />
            )
            .toJSON();

        expect(tree).toMatchSnapshot();
    });

    it("call setKeywords when user type in the input", () => {
        const setValue = jest.fn();

        render(
            <Search
                value=""
                onKeyDown={() => {}}
                setKeywords={setValue}
                onClick={function (): void {
                    throw new Error("Function not implemented.");
                }}
                hidden={false}
            />
        );

        userEvent.type(screen.getByPlaceholderText("search"), "M");
        expect(setValue).toHaveBeenCalledWith("M");
    });
});

describe("Test Button component", () => {
    it("Test click event", () => {
        const mockCallBack = jest.fn();

        const button = shallow(<button onClick={mockCallBack}>Ok!</button>);
        button.find("button").simulate("click");
        expect(mockCallBack.mock.calls.length).toEqual(1);
    });
});

describe("Test dropdown", () => {
    it("runs without crashing", () => {
        render(
            <ShowMore
                dropdownRef={undefined}
                state={false}
                setState={() => {}}
            />
        );
    });

    it("can show that the dropdown has children", () => {
        const dropdown = render(
            <ShowMore
                dropdownRef={undefined}
                state={false}
                setState={() => {}}
            />
        ).container;
        const display = dropdown.children[0];

        expect(display.textContent).toContain("dropdown");
        // console.log(display.textContent);

        fireEvent.click(dropdown);

        const dropdownOptions = getAllByRole(dropdown, "menu");
        fireEvent.click(dropdownOptions[0]);

        expect(display.ELEMENT_NODE).toBe(1);

        // console.log(display.ELEMENT_NODE);
    });
});
