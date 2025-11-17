import { createBrowserRouter, RouterProvider } from 'react-router-dom';
import Layout from '../components/Layout';
import Home from '../pages/home/Home';
import Error from '../pages/error/Error';
import Login from '../components/login/Login';
import Media from '../pages/media/Media';
import Profile from '../pages/profile/Profile';
import Register from '../pages/register/Register';
import ProtectedRoute from './ProtectedRoute';

const router = createBrowserRouter([
  {
    path: '/',
    element: <Layout />,
    errorElement: <Error />,
    children: [
      { path: '/login', element: <Login /> },
      { path: '/register', element: <Register /> },
      {
        element: <ProtectedRoute />,
        children: [
          { index: true, element: <Home /> },
          { path: '/profile', element: <Profile /> },
          { path: '/media', element: <Media /> },
        ],
      },
    ],
  },
]);

export const Router = () => <RouterProvider router={router} />;
