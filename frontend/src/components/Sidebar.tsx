import SidebarItem from './SidebarItem';

const Sidebar = ({ isCollapsed, toggleSidebar }) => {
  const sidebarItems = [
    { href: 'one', label: 'one', icon: 'one' },
    { href: 'two', label: 'two', icon: 'two' },
    { href: 'media', label: 'Media', icon: 'media' },
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
