import './App.css';
import { Router } from './router/Router';
import AppProvider from './store/AppProvider';

function App() {
  return (
    <AppProvider>
      <Router />
    </AppProvider>
  );
}

export default App;
