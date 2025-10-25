import { useSelector } from 'react-redux';
import { NavLink, Outlet } from 'react-router-dom';

const ProtectedRoute = () => {
  const { userToken } = useSelector((state) => state?.auth);

  if (!userToken) {
    return (
      <div>
        <h1>Unauthorized</h1>
        <span>
          <NavLink to='/login'>Login</NavLink> to access
        </span>
      </div>
    );
  }

  return <Outlet />;
};

export default ProtectedRoute;
