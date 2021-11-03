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
    ReactDOM.render(<MediaList items={data} />, container);
});

afterEach(() => {
    document.body.removeChild(container);
    container.remove();
});

describe("Test MediaList component", () => {
    it("should render component", () => {
        const tree = renderer.create(<MediaList items={data} />).toJSON();

        expect(tree).toMatchSnapshot();
    });

    it("should find MediaList", () => {
        expect(getByTestId(container, "mediaList-wrapper")).toBeTruthy();
    });

    it("should have children", () => {
        const wrapper = shallow(<MediaList items={data} />);

        expect(
            wrapper.containsMatchingElement(
                <Media id={0} imgSrc={""} mediaTitle={""} mediaSubtitle={""} />
            )
        ).toEqual(true);
    });
});
