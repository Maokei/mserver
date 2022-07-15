import * as React from "react";
import { ChangeEvent } from "react";
import styles from "./form.module.scss";

export interface FormProps {
	username: string;
	password: string;
	setUsername: Function;
	setPassword: Function;
}

interface LoginProps {
	message: string;
	handleLoginSubmit: (e: React.FormEvent<HTMLFormElement>) => void;
}

type LoginType = FormProps & LoginProps;

const Login: React.FC<LoginType> = ({
	username,
	setUsername,
	password,
	setPassword,
	message,
	handleLoginSubmit,
}) => {
	const submitBtnClasses =
		username.length < 3 || password.length < 3
			? `${styles.submitBtn} ${styles.btnDisabled}`
			: `${styles.submitBtn}`;

	const submitDisabled = () => username.length < 3 || password.length < 3;

	return (
		<div className={styles.wrapper}>
			<form className={styles.login} onSubmit={handleLoginSubmit}>
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
				<p className={styles.message}>{message}</p>
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
