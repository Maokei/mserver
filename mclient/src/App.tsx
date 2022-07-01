import * as React from "react";
import { Routes, Route, useNavigate } from "react-router-dom";
import Layout from "./components/Layout";
import Login from "./components/auth/Login";
import Signup from "./components/auth/Signup";
import { Header } from "./components/header/Header";
import { MediaList } from "./components/main/MediaList";
import { SongItem } from "./components/main/Media";
import "./App.scss";
import dummyData from "./dummyData.json";
import { SelectedMedia } from "./components/main/SelectedMedia";

function App() {
	const navigate = useNavigate();

	const [data, setData] = React.useState<SongItem[]>(dummyData);
	const hasSelected = React.useState<boolean>(false);

	const handleToggleLike = (id: number) => {
		const mapped = data.map((item) => {
			return item.id === Number(id)
				? { ...item, like: !item.like }
				: { ...item };
		});
		setData(mapped);
	};

	const handlePlay = () => {
		console.log("play media...");
	};

	const [username, setUsername] = React.useState("");
	const [email, setEmail] = React.useState("");
	const [password, setPassword] = React.useState("");
	const [message, setMessage] = React.useState("");

	const handleLoginSubmit = (e: React.FormEvent<HTMLFormElement>) => {
		e.preventDefault();

		// hardcoded token
		const tempToken = {
			username: "test1234",
			password: "pw1234",
		};

		if (
			username === tempToken.username &&
			password === tempToken.password
		) {
			// redirect to /
			navigate("/");
			setUsername("");
			setPassword("");
		} else {
			setMessage("Wrong username or password!");
		}
	};

	const handleSignupSubmit = (e: React.FormEvent<HTMLFormElement>) => {
		e.preventDefault();

		const emailRegex = /\S+@\S+\.\S+/;
		const usernameReg = /^[a-zA-Z0-9]+$/;
		const passwordReg = /^[A-Za-z]\w{7,14}$/;

		if (
			emailRegex.test(email) &&
			usernameReg.test(username) &&
			passwordReg.test(password)
		) {
			// redirect to /
			navigate("/");

			// store credientials

			// empty form
			setEmail("");
			setUsername("");
			setPassword("");
		} else {
			console.log(email, username, password);
			setMessage("Invalid email, username or password!");
		}
	};

	return (
		<div className="app has-background-dark">
			<Header />

			<Routes>
				<Route path="/" element={<Layout />}>
					<Route
						index
						element={
							<main className="main">
								{!data ? (
									"There is nothing yet"
								) : !hasSelected ? (
									<>
										<MediaList
											items={data}
											handleToggleLike={handleToggleLike}
										/>
									</>
								) : (
									<>
										<MediaList
											items={data}
											handleToggleLike={handleToggleLike}
										/>
										<SelectedMedia
											onClickPlay={handlePlay}
										/>
									</>
								)}
							</main>
						}
					/>
				</Route>
				<Route
					path="login"
					element={
						<Login
							username={username}
							password={password}
							setUsername={setUsername}
							setPassword={setPassword}
							message={message}
							handleLoginSubmit={handleLoginSubmit}
						/>
					}
				/>
				<Route
					path="signup"
					element={
						<Signup
							email={email}
							username={username}
							password={password}
							setEmail={setEmail}
							setUsername={setUsername}
							setPassword={setPassword}
							message={message}
							handleSignupSubmit={handleSignupSubmit}
						/>
					}
				/>
			</Routes>
		</div>
	);
}

export default App;
