// @ts-nocheck
import { render, screen } from "@testing-library/react";
import userEvent from "@testing-library/user-event";
import Login from "../components/auth/Login";

test("submit username and password", async () => {
	// ARRANGE
	const username = "testuser";
	const password = "testpw123";
	const mockLogin = jest.fn();

	render(<Login onSumit={mockLogin(username, password)} />);

	const usernameInput = screen.getByPlaceholderText("Username");
	userEvent.type(usernameInput, "testuser");
	const passwordInput = screen.getByPlaceholderText("Password");
	userEvent.type(passwordInput, "testpw123");
	const loginButton = screen.getByTestId("submitButton");
	// expect(loginButton).not.toBeDisabled();
	expect(loginButton).toBeDisabled();

	// ACT
	userEvent.click(loginButton);

	// ASSERT
	await expect(mockLogin).toHaveBeenCalled();
	await expect(mockLogin).toHaveBeenCalledTimes(1);
	await expect(mockLogin).toHaveBeenCalledWith("testuser", "testpw123");
});
