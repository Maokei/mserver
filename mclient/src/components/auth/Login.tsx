import React from "react";

const Login = () => {
	return (
		<div className="form-wrapper">
			<form className="login">
				<input type="text" placeholder="Username" />
				<input type="password" placeholder="Password" />
				<button>Login</button>
			</form>
		</div>
	);
};

export default Login;
