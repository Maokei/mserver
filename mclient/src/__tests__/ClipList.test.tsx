import * as ReactDOM from "react-dom";
import renderer from "react-test-renderer";

import { ClipList } from "../components/main/ClipList";

let container: HTMLDivElement;

beforeEach(() => {
    container = document.createElement("div");
    document.body.appendChild(container);
    ReactDOM.render(<ClipList />, container);
});

afterEach(() => {
    document.body.removeChild(container);
    container.remove();
});

describe("Test ClipList component", () => {
    it("should render component", () => {
        const tree = renderer.create(<ClipList />).toJSON();

        expect(tree).toMatchSnapshot();
    });
});
