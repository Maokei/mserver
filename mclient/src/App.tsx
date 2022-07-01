import * as React from "react";
import { Routes, Route, useNavigate } from "react-router-dom";
import Layout from "./components/Layout";
import Login from "./components/auth/Login";
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

	// TODO: hardcode token to test authentication
	const handleSubmit = (e: React.FormEvent<HTMLFormElement>) => {
		e.preventDefault();
		// redirect to /
		navigate("/");
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
					element={<Login handleSubmit={handleSubmit} />}
				/>
			</Routes>
		</div>
	);
}

export default App;
