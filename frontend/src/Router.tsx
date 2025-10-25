import { createBrowserRouter, RouterProvider } from 'react-router-dom';
import Layout from './components/Layout';
import Home from './pages/home/Home';
import Error from './pages/error/Error';
import Login from './components/login/Login';
import Media from './pages/media/Media';
import Profile from './pages/profile/Profile';
import ProtectedRoute from './ProtectedRoute';

const router = createBrowserRouter([
  {
    path: '/',
    element: <Layout />,
    errorElement: <Error />,
    children: [
      { path: '/login', element: <Login /> },
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

/*const public = createBrowserRouter([
  {
    path: '/',
    element: <Layout />,
    errorElement: <Error />,
    children: [
      { index: true, element: <Home /> },
      { path: '/login', element: <Login /> },
    ],
  },
]);*/

export const Router = () => <RouterProvider router={router} />;
