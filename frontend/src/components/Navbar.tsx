import { useState } from 'react';
import { useNavigate } from 'react-router-dom';

const Navbar = () => {
  const navigate = useNavigate();
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
        <button onClick={toggleDropDown}>
          <span className='hidden md:block font-medium'>User</span>
        </button>
      </nav>
    </header>
  );
};
export default Navbar;
