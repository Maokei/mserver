import * as React from "react";
import { ChangeEvent } from "react";

// TODO
// error handling for submitting form

export interface formProps {
	handleSubmit: (e: React.FormEvent<HTMLFormElement>) => void;
}

const Login: React.FC<formProps> = ({ handleSubmit }) => {
	const [username, setUsername] = React.useState("");
	const [password, setPassword] = React.useState("");

	const submitBtnClasses =
		username.length < 3 || password.length < 3
			? "submit-btn btn-disabled"
			: "submit-btn";

	const submitDisabled = () => username.length < 3 || password.length < 3;

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
					className={submitBtnClasses}
					disabled={submitDisabled()}
				>
					Login
				</button>
			</form>
		</div>
	);
};

export default Login;
