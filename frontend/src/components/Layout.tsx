import { useState } from 'react';
import { Outlet } from 'react-router-dom';
import Navbar from './Navbar';
import Sidebar from './Sidebar';
import { useSelector } from 'react-redux';

const Layout = () => {
  const [isSidebarCollapsed, setIsSidebarCollapsed] = useState(false);
  const { userToken } = useSelector((state) => state?.auth);

  const toggleSidebar = () => {
    setIsSidebarCollapsed(!isSidebarCollapsed);
  };

  if (!userToken) {
    return (
      <div className='h-screen flex justify-center items-center'>
        <Outlet />
      </div>
    );
  }

  return (
    <div className='h-screen flex flex-col'>
      <Navbar loggedIn={userToken} />
      <div className='flex grow overflow-hidden'>
        {/* Sidebar */}
        {<Sidebar isCollapsed={isSidebarCollapsed} toggleSidebar={toggleSidebar} />}

        {/* Main Content */}
        <div
          className={`flex grow p-4 transition-all duration-300 ${isSidebarCollapsed ? 'ml-14' : 'ml-4'}`}
        >
          <Outlet />
        </div>
      </div>
    </div>
  );
};

export default Layout;
