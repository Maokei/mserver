import { createAsyncThunk } from '@reduxjs/toolkit';
import { AppDispatch, RootState } from '../store';
import { AuthRequest, AuthResponse } from '../api/types';

const BACKEND_URL: string = 'http://localhost:8080/api/v1/';

export const login = createAsyncThunk<
  AuthResponse,
  AuthRequest,
  {
    dispatch: AppDispatch; // Type for dispatch within the thunk
    state: RootState; // Type for getState within the thunk
    rejectValue: string; // Optional: Type for the rejected action's payload
  }
>('auth/login', async (req, { dispatch, getState, rejectWithValue }) => {
  try {
    const response = await fetch(BACKEND_URL + 'login', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify(req),
    });
    if (!response.ok) {
      return rejectWithValue('Failed to fetch user');
    }
    const data: AuthResponse = await response.json();
    return data;
  } catch (error) {
    console.error(error);
    return rejectWithValue('error');
  }
});
