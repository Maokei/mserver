import React from "react";
import { render, screen } from "@testing-library/react";
import userEvent from "@testing-library/user-event";

import Login from "../components/auth/Login";

describe("input", () => {
	it("accepts user typing", async () => {
		render(
			<input
				type="text"
				data-testid="username"
				// aria-label="blah"
				name="username"
			/>
		);
		// const input = screen.getByLabelText("blah");
		const input = screen.getByTestId("username");
		await userEvent.type(input, "Hello,{enter}World!");
	});
});

it("form can be submitted & input field is modifiable", () => {
	const mockSubmit = jest.fn();
	render(<Login />);

	// fireEvent.change(queryByTestId('input'), { target: { value: 'username'}})
	screen.queryByTestId("form");

	expect(mockSubmit).toHaveBeenCalled();
});
