import SidebarItem from './SidebarItem';

interface SidebarProps {
  isCollapsed: boolean;
  toggleSidebar: () => void;
}

const Sidebar = ({ isCollapsed, toggleSidebar }: SidebarProps) => {
  const sidebarItems = [
    { href: '/', label: 'Home', icon: 'home' },
    { href: 'media', label: 'Media', icon: 'media' },
    { href: 'profile', label: 'Profile', icon: 'profile' },
  ];

  return (
    <aside
      className={`${isCollapsed ? 'w-14' : 'w-64'} h-full bg-gray-800 transition-all duration-300`}
    >
      <button onClick={toggleSidebar} data-testid='sidebar-btn' className='m-4'>
        {isCollapsed ? (
          <svg
            className='w-6 h-6 text-gray-800 dark:text-white'
            aria-hidden='true'
            xmlns='http://www.w3.org/2000/svg'
            width='24'
            height='24'
            fill='currentColor'
            viewBox='0 0 24 24'
          >
            <path d='M13 21h6c1.1046 0 2-.8954 2-2V5c0-1.10457-.8954-2-2-2h-6v18Z' />
            <path
              fill-rule='evenodd'
              d='M11 3H5c-1.10457 0-2 .89543-2 2v14c0 1.1046.89543 2 2 2h6V3Zm-5.70711 7.7071c-.39052-.3905-.39052-1.02368 0-1.41421.39053-.39052 1.02369-.39052 1.41422 0l1.99994 1.99991c.39052.3906.39052 1.0237 0 1.4142l-1.99994 2c-.39053.3905-1.02369.3905-1.41422 0-.39052-.3905-.39052-1.0237 0-1.4142l1.29284-1.2929-1.29284-1.2928Z'
              clip-rule='evenodd'
            />
          </svg>
        ) : (
          <svg
            className='w-6 h-6 text-gray-800 dark:text-white'
            aria-hidden='true'
            xmlns='http://www.w3.org/2000/svg'
            width='24'
            height='24'
            fill='currentColor'
            viewBox='0 0 24 24'
          >
            <path
              fill-rule='evenodd'
              d='M10 4H4c-1.10457 0-2 .89543-2 2v12c0 1.1046.89543 2 2 2h6V4ZM7.79283 9.29289c.39053.39053.39053 1.02371 0 1.41421L6.5 11.9999l1.29283 1.2929c.39053.3905.39053 1.0237 0 1.4142-.39052.3905-1.02368.3905-1.41421 0l-1.99994-2c-.39052-.3905-.39052-1.0236 0-1.4142l1.99994-1.99991c.39053-.39052 1.02369-.39052 1.41421 0Z'
              clip-rule='evenodd'
            />
            <path d='M12 20h8c1.1046 0 2-.8954 2-2V6c0-1.10457-.8954-2-2-2h-8v16Z' />
          </svg>
        )}
      </button>
      <nav>
        <ul>
          {sidebarItems.map((item, index) => (
            <SidebarItem
              key={index}
              href={item.href}
              label={item.label}
              icon={item.icon}
              isCollapsed={isCollapsed}
            />
          ))}
        </ul>
      </nav>
    </aside>
  );
};

export default Sidebar;
