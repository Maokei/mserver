import { useState } from 'react';
import { Outlet } from 'react-router-dom';
import Navbar from './Navbar';
import Sidebar from './Sidebar';

const Layout = () => {
  const [isSidebarCollapsed, setIsSidebarCollapsed] = useState(false);

  const toggleSidebar = () => {
    setIsSidebarCollapsed(!isSidebarCollapsed);
  };

  return (
    <div className='h-screen flex flex-col'>
      <Navbar />
      <div className='flex flex-grow overflow-hidden'>
        {/* Sidebar */}
        <Sidebar isCollapsed={isSidebarCollapsed} toggleSidebar={toggleSidebar} />

        {/* Main Content */}
        <div
          className={`flex-grow p-4 transition-all duration-300 ${isSidebarCollapsed ? 'ml-14' : 'ml-64'}`}
        >
          <Outlet />
        </div>
      </div>
    </div>
  );
};

export default Layout;
