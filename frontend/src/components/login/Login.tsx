import React, { useState, ChangeEvent } from 'react';
import { useDispatch, useSelector } from 'react-redux';
import { useNavigate } from 'react-router-dom';
import { login } from '../../store/auth.actions';
import { AuthRequest } from '../../api/types';
import { Canvas } from '@react-three/fiber';
import { Html } from '@react-three/drei';

//import { interceptor } from '../../intercept/fetch-intercept';
//import useQuery from '../../hooks/UseQuery';

const Login: React.FC = () => {
  const navigate = useNavigate();
  const dispatch = useDispatch();
  const { userToken } = useSelector((state) => state?.auth);

  const [input, setInput] = useState({
    username: 'cat',
    password: 'password',
  });

  /*React.useEffect(() => {
    if (token) {
      interceptor();
    }
    }, [token]);*/

  React.useEffect(() => {
    if (userToken) navigate('/');
  }, [userToken]);

  const loginReq = async () => {
    const req: AuthRequest = input;
    dispatch(login(req));
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

  //function Box(props: ThreeElements['mesh']) {
  function Box() {
    const hovered: boolean = false;
    return (
      <mesh scale={1}>
        <boxGeometry args={[1, 1, 1]} />
        <meshStandardMaterial color={hovered ? 'hotpink' : 'orange'} />
        <Html occlude distanceFactor={1.5} position={[0, 0, 0.51]} transform>
          <span>3D form</span>
          <hr />
          <form onSubmit={handleSubmit}>
            <input
              type='text'
              name='username'
              data-testid='username'
              placeholder='Username'
              value={input.username}
              onChange={(e: ChangeEvent<HTMLInputElement>) => handleInput(e)}
            />
            <hr />
            <input
              type='password'
              name='password'
              data-testid='password'
              placeholder='Password'
              value={input.password}
              onChange={(e: ChangeEvent<HTMLInputElement>) => handleInput(e)}
            />
            <hr />
            <button
              type='submit'
              data-testid='submitBtn'
              className='text-white bg-blue-700 hover:bg-blue-800 focus:ring-4 focus:ring-blue-300 font-medium rounded-lg text-sm px-5 py-2.5 me-2 mb-2 dark:bg-blue-600 dark:hover:bg-blue-700 focus:outline-none dark:focus:ring-blue-800'
            >
              Login
            </button>
          </form>
        </Html>
      </mesh>
    );
  }

  return (
    <>
      <Canvas>
        <ambientLight intensity={Math.PI / 2} />
        <spotLight
          position={[10, 10, 10]}
          angle={0.15}
          penumbra={1}
          decay={0}
          intensity={Math.PI}
        />
        <pointLight position={[-10, -10, -10]} decay={0} intensity={Math.PI} />
        {Box()}
      </Canvas>
      <form onSubmit={handleSubmit}>
        <input
          type='text'
          name='username'
          data-testid='username'
          placeholder='Username'
          className='bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500'
          value={input.username}
          onChange={(e: ChangeEvent<HTMLInputElement>) => handleInput(e)}
        />
        <input
          type='password'
          name='password'
          data-testid='password'
          placeholder='Password'
          className='bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500'
          value={input.password}
          onChange={(e: ChangeEvent<HTMLInputElement>) => handleInput(e)}
        />
        <button
          type='submit'
          data-testid='submitBtn'
          className='text-white bg-blue-700 hover:bg-blue-800 focus:ring-4 focus:ring-blue-300 font-medium rounded-lg text-sm px-5 py-2.5 me-2 mb-2 dark:bg-blue-600 dark:hover:bg-blue-700 focus:outline-none dark:focus:ring-blue-800'
        >
          Login
        </button>
      </form>
    </>
  );
};

export default Login;
