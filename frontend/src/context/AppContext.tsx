import * as React from 'react';

type ApplicationState = {
  token: string | null;
};

type AppAction = { type: 'login' } | { type: 'logout' };

type AppContextType = {
  state: ApplicationState;
  dispatch: React.Dispatch<AppAction>;
};

const initialState: ApplicationState = { token: null };

function reducer(state: ApplicationState, action: AppAction): ApplicationState {
  switch (action.type) {
    case 'login':
      return { token: state.token };
    case 'logout':
      return { token: null };
    default:
      throw new Error();
  }
}

export const AppContext = React.createContext<AppContextType>({
  state: initialState,
  dispatch: () => {},
});

export const AppProvider: React.FC<{ children: React.ReactNode }> = ({ children }) => {
  const [state, dispatch] = React.useReducer(reducer, initialState);
  //const login = async () => { }
  const ctx: AppContextType = { state, dispatch };
  return <AppContext.Provider value={ctx}>{children}</AppContext.Provider>;
};
