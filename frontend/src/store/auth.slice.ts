import { createSlice } from '@reduxjs/toolkit';
import { login } from './auth.actions';

const userToken = localStorage.getItem('userToken') ? localStorage.getItem('userToken') : null;

export interface AuthState {
  loading: boolean;
  userToken: string | null;
  userInfo: object | null;
  error: string | null;
  success: boolean;
}

const initialState: AuthState = {
  loading: false,
  userToken: userToken,
  userInfo: null,
  error: null,
  success: false,
};

export const authSlice = createSlice({
  name: 'auth',
  initialState,
  reducers: {
    setToken: (state) => {
      state.userToken = 'fake token';
    },
    logout: (state) => {
      state.userToken = '';
      localStorage.removeItem('auth');
    },
  },
  extraReducers: (builder) => {
    builder.addCase(login.fulfilled, (state, action) => {
      state.userToken = action.payload.token;
      state.loading = false;
      state.success = true;
    });
    builder.addCase(login.pending, (state) => {
      state.loading = true;
    });
    builder.addCase(login.rejected, (state, action) => {
      state.error = action.error + '';
      state.loading = false;
      state.success = false;
    });
  },
});

export default authSlice.reducer;
