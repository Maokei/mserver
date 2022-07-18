import { render, screen } from "@testing-library/react";
import userEvent from "@testing-library/user-event";

import Login from "../components/auth/Login";

describe("Login", () => {
	it("should render input element", () => {
		render(<input type="text" data-testid="username" name="username" />);
		const inputEl = screen.getByTestId("username");
		expect(inputEl).toBeInTheDocument();
	});

	it("should be able to type into input", () => {
		render(
			<Login
				handleLoginSubmit={() => console.log("login")}
				username={""}
				password={""}
				setUsername={() => console.log("test1234")}
				setPassword={() => console.log("12345")}
				message={""}
			/>
		);
		const passwordInput = screen.getByTestId("password");
		userEvent.type(passwordInput, "123456");
	});

	it("button should be enabled when username and password are filled", () => {
		render(
			<Login
				handleLoginSubmit={() => console.log("login")}
				username={""}
				password={""}
				setUsername={() => "test1234"}
				setPassword={() => "12345"}
				message={""}
			/>
		);
		const button = screen.getByTestId("submitButton");
		expect(button).toBeDisabled();

		const usernameInput = screen.getByTestId("username");
		const passwordInput = screen.getByTestId("password");
		userEvent.type(usernameInput, "test1234");
		userEvent.type(passwordInput, "123456");
		expect(button).toBeEnabled();

		userEvent.clear(usernameInput);
		userEvent.clear(passwordInput);
		expect(button).toBeDisabled();
	});
});
