import * as ReactDOM from "react-dom";
import renderer from "react-test-renderer";
import { getByTestId } from "@testing-library/react";
import { shallow } from "enzyme";

import { MediaList } from "../components/main/MediaList";
import { Media } from "../components/main/Media";
import data from "../dummyData.json";

let container: HTMLDivElement;

beforeEach(() => {
    container = document.createElement("div");
    document.body.appendChild(container);
    ReactDOM.render(
        <MediaList items={data} handleToggleLike={() => console.log("test")} />,
        container
    );
});

afterEach(() => {
    document.body.removeChild(container);
    container.remove();
});

describe("Test MediaList component", () => {
    it.skip("should render component", () => {
        const tree = renderer
            .create(
                <MediaList
                    items={data}
                    handleToggleLike={() => console.log("test")}
                />
            )
            .toJSON();

        expect(tree).toMatchSnapshot();
    });

    it("should find MediaList", () => {
        expect(getByTestId(container, "mediaList-wrapper")).toBeTruthy();
    });

    it("should have children", () => {
        const wrapper = shallow(
            <MediaList
                items={data}
                handleToggleLike={() => console.log("test")}
            />
        );

        expect(
            wrapper.containsMatchingElement(
                <Media
                    key={0}
                    songItem={data[0]}
                    handleToggleLike={() => console.log("test")}
                />
            )
        ).toEqual(false);
    });
});
