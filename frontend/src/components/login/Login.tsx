import React, { useState, ChangeEvent } from 'react';
import { useDispatch, useSelector } from 'react-redux';
import { useNavigate } from 'react-router-dom';
import { login } from '../../store/auth.actions';
import { AuthRequest } from '../../api/types';
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
    </>
  );
};

export default Login;
