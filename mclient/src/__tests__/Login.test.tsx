import { render, screen } from "@testing-library/react";
import userEvent from "@testing-library/user-event";

import Login from "../components/auth/Login";

describe("input", () => {
	it("accepts user typing", async () => {
		render(<input type="text" data-testid="username" name="username" />);
		const input = screen.getByTestId("username");
		await userEvent.type(input, "Hello,{enter}World!");
	});
});

it("button state in form", () => {
	render(<Login />);
	const button = screen.getByTestId("submitButton");
	expect(button).toBeDisabled();

	const usernameInput = screen.getByTestId("username");
	const passwordInput = screen.getByTestId("password");
	userEvent.type(usernameInput, "test123");
	userEvent.type(passwordInput, "123456");
	// expect(button).toBeEnabled();

	userEvent.clear(usernameInput);
	userEvent.clear(passwordInput);
	expect(button).toBeDisabled();
});
