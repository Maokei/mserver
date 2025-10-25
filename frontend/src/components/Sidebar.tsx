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
      <button onClick={toggleSidebar} data-testid='sidebar-btn'>
        Toggle
      </button>
      <nav>
        <ul>
          {sidebarItems.map((item) => (
            <SidebarItem
              key={item.href}
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
