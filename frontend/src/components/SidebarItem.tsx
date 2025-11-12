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

  const getIcon = (icon: string) => {
    switch (icon) {
      case 'home':
        return (
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
              d='M11.293 3.293a1 1 0 0 1 1.414 0l6 6 2 2a1 1 0 0 1-1.414 1.414L19 12.414V19a2 2 0 0 1-2 2h-3a1 1 0 0 1-1-1v-3h-2v3a1 1 0 0 1-1 1H7a2 2 0 0 1-2-2v-6.586l-.293.293a1 1 0 0 1-1.414-1.414l2-2 6-6Z'
              clip-rule='evenodd'
            />
          </svg>
        );
      case 'media':
        return (
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
              d='M19.003 3A2 2 0 0 1 21 5v2h-2V5.414L17.414 7h-2.828l2-2h-2.172l-2 2H9.586l2-2H9.414l-2 2H3V5a2 2 0 0 1 2-2h14.003ZM3 9v10a2 2 0 0 0 2 2h14a2 2 0 0 0 2-2V9H3Zm2-2.414L6.586 5H5v1.586Zm4.553 4.52a1 1 0 0 1 1.047.094l4 3a1 1 0 0 1 0 1.6l-4 3A1 1 0 0 1 9 18v-6a1 1 0 0 1 .553-.894Z'
              clip-rule='evenodd'
            />
          </svg>
        );
      case 'profile':
        return (
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
              d='M12 20a7.966 7.966 0 0 1-5.002-1.756l.002.001v-.683c0-1.794 1.492-3.25 3.333-3.25h3.334c1.84 0 3.333 1.456 3.333 3.25v.683A7.966 7.966 0 0 1 12 20ZM2 12C2 6.477 6.477 2 12 2s10 4.477 10 10c0 5.5-4.44 9.963-9.932 10h-.138C6.438 21.962 2 17.5 2 12Zm10-5c-1.84 0-3.333 1.455-3.333 3.25S10.159 13.5 12 13.5c1.84 0 3.333-1.455 3.333-3.25S13.841 7 12 7Z'
              clip-rule='evenodd'
            />
          </svg>
        );
      default:
        return icon;
    }
  };

  return (
    <li className=''>
      <Link
        to={href}
        className={`flex items-center py-2.5 px-4 hover:bg-gray-700 transition-all duration-300
          ${isActive ? 'bg-gray-700' : ''}`}
      >
        <div className='flex'>
          <div className='mr-6'>{getIcon(icon)}</div> {!isCollapsed && label}
        </div>
      </Link>
    </li>
  );
};

export default SidebarItem;
