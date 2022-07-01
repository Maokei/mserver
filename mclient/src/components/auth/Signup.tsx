import * as React from "react";
import { ChangeEvent } from "react";
import { FormProps } from "./Login";

interface SignupProps extends FormProps {
	email: string;
	message: string;
	setEmail: Function;
	handleSignupSubmit: (e: React.FormEvent<HTMLFormElement>) => void;
}

const Signup: React.FC<SignupProps> = ({
	email,
	setEmail,
	username,
	setUsername,
	password,
	setPassword,
	message,
	handleSignupSubmit,
}) => {
	const submitBtnClasses =
		email.length < 3 || username.length < 3 || password.length < 3
			? "submit-btn btn-disabled"
			: "submit-btn";

	const submitDisabled = () =>
		username.length < 3 || username.length < 3 || password.length < 3;

	return (
		<div className="form-wrapper">
			<form className="login" onSubmit={handleSignupSubmit}>
				<input
					type="text"
					data-testid="email"
					placeholder="email@email.com"
					value={email}
					onChange={(e: ChangeEvent<HTMLInputElement>) =>
						setEmail(e.target.value)
					}
				/>
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
				<p className="message">{message}</p>
				<button
					type="submit"
					data-testid="submitButton"
					className={submitBtnClasses}
					disabled={submitDisabled()}
				>
					Sign Up
				</button>
			</form>
		</div>
	);
};

export default Signup;
