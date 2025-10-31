import { Link, useLocation } from 'react-router-dom';

interface SidebarItemProps {
  href: string;
  label: string;
  icon: string;
  isCollapsed: boolean;
}

const SidebarItem = ({ href, label, icon, isCollapsed }: SidebarItemProps) => {
  const location = useLocation();
  const isActive = location.pathname === href;

  return (
    <li className=''>
      <Link
        to={href}
        className={`flex items-center py-2.5 px-4 hover:bg-gray-700 transition-all duration-300
          ${isActive ? 'bg-gray-700' : ''}`}
      >
        <span>
          {label} {icon}
        </span>
      </Link>
    </li>
  );
};

export default SidebarItem;
