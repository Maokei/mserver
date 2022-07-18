import { fireEvent, render, screen, waitFor } from "@testing-library/react";
import userEvent from "@testing-library/user-event";
import React from "react";

import Login from "../components/auth/Login";

const Wrapper = () => {
	const [password, setPassword] = React.useState("");
	const [username, setUsername] = React.useState("");
	return (<Login
		handleLoginSubmit={() => console.log("login")}
		username={username}
		password={password}
		setUsername={setUsername}
		setPassword={setPassword}
		message={""}
/>);
}

describe("Login", () => {
	it("should render input element", () => {
		render(<input type="text" data-testid="username" name="username" />);
		const inputEl = screen.getByTestId("username");
		expect(inputEl).toBeInTheDocument();
	});

 test("should be able to type into input", () => {
		const { getByTestId, getByPlaceholderText } = render(
			<Wrapper />
		);
		const testText = "123456";
		const passwordInput = screen.getByTestId("password");

		userEvent.type(passwordInput, testText);
		//fireEvent.change(passwordInput, {target: {value: testText}})
		waitFor(() => expect(passwordInput).toHaveValue(testText))

		expect(passwordInput).toHaveDisplayValue(testText);
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
