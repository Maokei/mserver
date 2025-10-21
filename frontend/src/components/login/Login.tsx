import React, { useState, useRef, ChangeEvent } from 'react';
import ReactPlayer from 'react-player';
//import { interceptor } from '../../intercept/fetch-intercept';
//import useQuery from '../../hooks/UseQuery';

const Login: React.FC = () => {
  const baseUrl: string = 'http://localhost:8080/api/v1/';
  const mediaUrl: string = 'media/';
  const audioList: string[] = [
    'c7192772-0c1c-11ed-861d-0242ac120003',
    'c7192772-0c1c-11ed-861d-0242ac120004',
    'c7192772-0c1c-11ed-861d-0242ac120005',
    'c7192772-0c1c-11ed-861d-0242ac120006',
  ];
  //const [media, setMedia] = useState([]); //TODO propper type
  const [input, setInput] = useState({
    username: 'cat',
    password: 'password',
  });
  const [token, setToken] = useState<string>();
  const videoRef = useRef(null);

  React.useEffect(() => {
    if (videoRef.current) console.log(videoRef);
  }, [videoRef]);

  /*React.useEffect(() => {
    if (token) {
      interceptor();
    }
    }, [token]);*/

  const loginReq = async () => {
    await fetch(baseUrl + 'login', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify(input),
    })
      .then((res) => {
        if (res.ok) {
          return res.json();
        }
        return Promise.reject(res);
      })
      .then((data) => {
        setToken(data);
      });
  };

  const handleInput = (e: ChangeEvent<HTMLInputElement>) => {
    const { name, value } = e.target;
    setInput((prev) => ({
      ...prev,
      [name]: value,
    }));
    console.log(input);
  };

  const handleSubmit = (e: React.FormEvent<HTMLFormElement>) => {
    e.preventDefault();
    if (input.username !== '' && input.password !== '') {
      loginReq();
    } else {
      alert('please provide a valid input');
    }
  };

  const handleEnded = (e: React.ChangeEvent<HTMLVideoElement>) => {
    console.log(e);
    if (videoRef.current) {
      console.log('ended');
    }
  };

  const getBtn = async (e) => {
    const res = await fetch(baseUrl + 'media', {
      method: 'GET',
      headers: {},
    })
      .then((res) => {
        if (res.ok) {
          return res.json();
        }
        return Promise.reject(res);
      })
      .then((data) => {
        return data;
      });
    setMedia(res);
  };

  const getInfo = async (e) => {
    console.log(videoRef.current);
  };

  const renderVideo = () => {
    const fid = 'c7192772-0c1c-11ed-861d-0242ac120001';
    if (token) {
      const media = baseUrl + mediaUrl + `${fid}?token=${token?.token}`;
      return (
        <div>
          <video
            src={baseUrl + mediaUrl + `${fid}?token=${token.token}`}
            width='720px'
            height='480px'
            controls={true}
            preload='none'
            onEnded={handleEnded}
          ></video>
          <ReactPlayer src={media} controls />
        </div>
      );
    }
    return;
  };

  const renderAudio = () => {
    if (token) {
      return (
        <div>
          <audio controls={true} autoPlay={true} preload='metadata' playsInline={true}>
            {audioList.map((id) => (
              <source
                key={id}
                ref={videoRef}
                src={baseUrl + mediaUrl + `${id}?token=${token.token}`}
                type='audio/mp3'
              />
            ))}
          </audio>
        </div>
      );
    }
    return;
  };

  return (
    <>
      <form onSubmit={handleSubmit}>
        <input
          type='text'
          name='username'
          data-testid='username'
          placeholder='Username'
          value={input.username}
          onChange={(e: ChangeEvent<HTMLInputElement>) => handleInput(e)}
        />
        <input
          type='password'
          name='password'
          data-testid='password'
          placeholder='Password'
          value={input.password}
          onChange={(e: ChangeEvent<HTMLInputElement>) => handleInput(e)}
        />
        <button type='submit' data-testid='submitBtn'>
          Login
        </button>
      </form>
      {renderVideo()}
      {renderAudio()}
      {token && <button onClick={getBtn}>Get</button>}
      {token && <button onClick={getInfo}>Info</button>}
    </>
  );
};

export default Login;
