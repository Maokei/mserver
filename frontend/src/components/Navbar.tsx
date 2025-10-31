import { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { useDispatch } from 'react-redux';
import { logout } from '../store/auth.slice';

interface NavbarProps {
  loggedIn: boolean;
}

const Navbar = ({ loggedIn }: NavbarProps) => {
  const navigate = useNavigate();
  const dispatch = useDispatch();
  const [dropDown, setDropDownState] = useState(false);
  const toggleDropDown = () => setDropDownState(!dropDown);

  const handleClick = () => {
    navigate('/');
  };

  return (
    <header className='w-full bg-white shadow p-4 sticky top-0 z-10'>
      <nav className='flex justify-between items-center'>
        <div className='text-2xl font-bold' onClick={handleClick}>
          MServer
        </div>
        <div className='flex flex-row-reverse gap-4'>
          {loggedIn && (
            <button className='button' onClick={() => dispatch(logout())}>
              Logout
            </button>
          )}
          <button onClick={toggleDropDown}>
            <span className='hidden md:block font-medium'>User</span>
          </button>
        </div>
      </nav>
    </header>
  );
};

export default Navbar;
