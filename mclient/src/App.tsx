import * as React from "react";
import { Routes, Route, useNavigate } from "react-router-dom";
import Layout from "./components/Layout";
import Home from "./components/home/Home";
import Login from "./components/auth/Login";
import Signup from "./components/auth/Signup";
import { Header } from "./components/header/Header";
import Media from "./components/media/Media";
import MediaList from "./components/media/MediaList";
// import { SongItem } from "./components/main/Media";
// import { SelectedMedia } from "./components/main/SelectedMedia";
import { fetchLoginAPI } from "./lib/api";
import styles from "./App.module.scss";

function App() {
    const navigate = useNavigate();

    const [data, setData] = React.useState([]);
    const hasSelected = React.useState<boolean>(false);

    // const handleToggleLike = (id: number) => {
    //     const mapped = data.map((item) => {
    //         return item.id === Number(id)
    //             ? { ...item, like: !item.like }
    //             : { ...item };
    //     });
    //     setData(mapped);
    // };

    const handlePlay = () => {
        console.log("play media...");
    };

    const [username, setUsername] = React.useState("");
    const [email, setEmail] = React.useState("");
    const [password, setPassword] = React.useState("");
    const [confirmPassword, setConfirmPassword] = React.useState("");
    const [message, setMessage] = React.useState("");

    const handleLoginSubmit = async (e: React.FormEvent<HTMLFormElement>) => {
        e.preventDefault();

        try {
            await fetchLoginAPI({ username, password });

            // should redirect to dashboard
            navigate("/");
            // empty all fields
            setUsername("");
            setPassword("");
            setMessage("");
        } catch (error) {
            setMessage("Wrong username or password! Login Failed.");
        }
    };

    const handleSignupSubmit = (e: React.FormEvent<HTMLFormElement>) => {
        e.preventDefault();

        const emailRegex = /\S+@\S+\.\S+/;
        const usernameReg = /^[a-zA-Z0-9]+$/;
        const passwordReg = /^[A-Za-z]\w{7,14}$/;

        // TODO
        // get existed users if any from database
        let savedUser = {
            email: "test@test.com",
            username: "test1234",
            password: "pw1234",
        };
        if (localStorage.getItem("mserver-client") !== null) {
            const info: any = localStorage.getItem("mserver-client");
            savedUser = JSON.parse(info);
        } else {
            console.log("Invalid");
        }

        if (
            emailRegex.test(email) &&
            usernameReg.test(username) &&
            passwordReg.test(password) &&
            email !== savedUser.email &&
            confirmPassword === password
        ) {
            // redirect to /
            navigate("/");

            // if the new credentials do not exist already store them in the database
            localStorage.setItem(
                "mserver-client",
                JSON.stringify({ email, username, password, confirmPassword })
            );

            // empty form
            setEmail("");
            setUsername("");
            setPassword("");
            setConfirmPassword("");
            setMessage("");
        } else {
            setMessage("Invalid email, username or password!");
        }
    };

    return (
        <div className={`${styles.app} container is-widescreen`}>
            <Header />

            <Routes>
                <Route path="/" element={<Layout />}>
                    <Route index element={<Home items={[]} />} />
                </Route>
                <Route
                    path="/library"
                    element={
                        <main className={styles.container}>
                            {!data ? (
                                "There is nothing yet"
                            ) : !hasSelected ? (
                                <>
                                    <MediaList
                                    // items={data}
                                    // handleToggleLike={handleToggleLike}
                                    />
                                </>
                            ) : (
                                <>
                                    <MediaList
                                    // items={data}
                                    // handleToggleLike={handleToggleLike}
                                    />
                                    {/* <SelectedMedia onClickPlay={handlePlay} /> */}
                                </>
                            )}
                        </main>
                    }
                />
                <Route path="/mediaPlayer" element={<Media />} />
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
                            confirmPassword={confirmPassword}
                            setConfirmPassword={setConfirmPassword}
                        />
                    }
                />
            </Routes>
        </div>
    );
}

export default App;
