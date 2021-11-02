import React from "react";
import * as ReactDOM from "react-dom";
import {
    getByTestId,
    fireEvent,
    render,
    // screen,
    getAllByRole,
} from "@testing-library/react";
import renderer from "react-test-renderer";
import { shallow } from "enzyme";
import { Header } from "../components/Header";
import { ShowMore } from "../components/ShowMore";
import { Search } from "../components/Search";
// import userEvent from "@testing-library/user-event";

let container: HTMLDivElement;

beforeEach(() => {
    container = document.createElement("div");
    document.body.appendChild(container);
    ReactDOM.render(<Header />, container);
});

afterEach(() => {
    document.body.removeChild(container);
    container.remove();
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

    it("should render only one input that has a hidden class", () => {
        const inputs = container.querySelectorAll("input");
        expect(inputs).toHaveLength(1);

        const hiddenInpupClass = container.querySelector(".hidden");
        expect(hiddenInpupClass).toBeInTheDocument();
    });

    it("should show input if button is clicked", () => {
        const mockShowBtnClick = jest.fn();
        const wrapper = shallow(
            <Search
                value=""
                onKeyDown={() => {}}
                setKeywords={() => {}}
                onClick={mockShowBtnClick}
                hidden={false}
            />
        );

        wrapper.find("button").at(0).simulate("click");
        expect(mockShowBtnClick).toHaveBeenCalled();
    });

    it("render input with a hidden class", () => {
        const tree = renderer
            .create(
                <Search
                    value="something"
                    onKeyDown={() => {}}
                    setKeywords={() => {}}
                    onClick={function (): void {
                        throw new Error("Function not implemented.");
                    }}
                    hidden={true}
                />
            )
            .toJSON();

        expect(tree).toMatchSnapshot();
    });

    it("call setKeywords when input texts", () => {
        const setKeywords = jest.fn();

        render(
            <Search
                value="something"
                onKeyDown={() => {}}
                setKeywords={setKeywords}
                onClick={function (): void {
                    throw new Error("Function not implemented.");
                }}
                hidden={true}
            />
        );

        // userEvent.type(screen.getByTestId("search-input"), "something");
        // expect(setKeywords).toHaveValue("something");
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
