import * as React from "react";
import { ChangeEvent } from "react";
import { FormProps } from "./Login";
import styles from "./form.module.scss";

interface SignupProps extends FormProps {
	email: string;
	confirmPassword: string;
	message: string;
	setEmail: Function;
	setConfirmPassword: Function;
	handleSignupSubmit: (e: React.FormEvent<HTMLFormElement>) => void;
}

const Signup: React.FC<SignupProps> = ({
	email,
	setEmail,
	username,
	setUsername,
	password,
	setPassword,
	confirmPassword,
	setConfirmPassword,
	message,
	handleSignupSubmit,
}) => {
	const submitBtnClasses =
		email.length < 3 ||
		username.length < 3 ||
		password.length < 3 ||
		confirmPassword.length < 3
			? `${styles.submitBtn} ${styles.btnDisabled}`
			: `${styles.submitBtn}`;

	const submitDisabled = () =>
		username.length < 3 || username.length < 3 || password.length < 3;

	return (
		<div className={styles.wrapper}>
			<form className={styles.login} onSubmit={handleSignupSubmit}>
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
				<input
					type="password"
					data-testid="confirm-password"
					placeholder="Confirm Password"
					value={confirmPassword}
					onChange={(e: ChangeEvent<HTMLInputElement>) =>
						setConfirmPassword(e.target.value)
					}
				/>
				<p className={styles.message}>{message}</p>
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
