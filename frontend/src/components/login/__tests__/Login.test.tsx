import { describe, it, expect } from 'vitest';
import { fireEvent, render, screen } from '@testing-library/react';
import Login from '../Login';

describe('Login component', () => {
  it('render and test input', async () => {
    render(<Login />);

    expect(screen.getByTestId('username')).toBeInTheDocument();
    expect(screen.getByTestId('password')).toBeInTheDocument();

    const username = screen.getByTestId('username');
    const loginBtn = screen.getByTestId('submitBtn');

    await fireEvent.change(username, { target: { value: 'dog' } });
    expect(username.value).toBe('dog');
    await fireEvent.change(username, { target: { value: 'cat' } });
    console.log(username.value);
    await fireEvent.click(loginBtn);
  });
});
