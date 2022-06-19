import * as React from "react";
import { ChangeEvent } from "react";

// TODO error handling for submitting form
const Login = () => {
	const [username, setUsername] = React.useState("");
	const [password, setPassword] = React.useState("");

	const submitDisabled = () => username.length === 0 || password.length === 0;

	const handleSubmit = (e: React.FormEvent<HTMLFormElement>) => {
		e.preventDefault();
		console.log("Submit button clicked!");
	};

	return (
		<div className="form-wrapper">
			<form className="login" onSubmit={handleSubmit}>
				<input
					type="text"
					data-testid="username"
					placeholder="Username"
					value={username}
					onChange={(e: ChangeEvent<HTMLInputElement>) =>
						setUsername(e.target.value)
					}
				/>
				<input
					type="password"
					data-testid="password"
					placeholder="Password"
					value={password}
					onChange={(e: ChangeEvent<HTMLInputElement>) =>
						setPassword(e.target.value)
					}
				/>
				<button
					type="submit"
					data-testid="submitButton"
					disabled={submitDisabled()}
				>
					Login
				</button>
			</form>
		</div>
	);
};

export default Login;
