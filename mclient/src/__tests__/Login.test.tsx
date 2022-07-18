import { fireEvent, render, screen, waitFor } from "@testing-library/react";
import userEvent from "@testing-library/user-event";
import React from "react";

import Login from "../components/auth/Login";

const Wrapper = () => {
	const [username, setUsername] = React.useState("");
	const [password, setPassword] = React.useState("");

	return (
		<Login
			handleLoginSubmit={() => console.log("login")}
			username={username}
			password={password}
			setUsername={setUsername}
			setPassword={setPassword}
			message={""}
		/>
	);
};

describe("Login", () => {
	it("should render input element", () => {
		render(<input type="text" data-testid="username" name="username" />);
		const inputEl = screen.getByTestId("username");
		expect(inputEl).toBeInTheDocument();
	});

	test("should be able to type into input", async () => {
		render(<Wrapper />);
		const testText = "123456";
		const passwordInput = screen.getByTestId("password");

		fireEvent.change(passwordInput, { target: { value: testText } });
		await waitFor(() => expect(passwordInput).toHaveValue(testText));

		expect(passwordInput).toHaveDisplayValue(testText);
	});

	it("button should be enabled when username and password are filled", () => {
		render(<Wrapper />);
		const button = screen.getByTestId("submitButton");
		expect(button).toBeDisabled();

		const testUsername = "user";
		const testPassword = "123456";
		const usernameInput = screen.getByTestId("username");
		const passwordInput = screen.getByTestId("password");

		fireEvent.change(usernameInput, { target: { value: testUsername } });
		fireEvent.change(passwordInput, { target: { value: testPassword } });

		expect(button).toBeEnabled();

		userEvent.clear(usernameInput);
		userEvent.clear(passwordInput);
		expect(button).toBeDisabled();
	});
});
