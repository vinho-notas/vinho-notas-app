import React from 'react';
import { render, fireEvent, waitFor, screen } from '@testing-library/react';
import "@testing-library/jest-dom";
import Login from './Login';

describe('Login', () => {

    it('should renders correctly', () => {
        render(<Login />);

        expect(screen.getByText('Register')).toBeInTheDocument();
        expect(screen.getByText('Email*')).toBeInTheDocument();
        expect(screen.getByText('Password*')).toBeInTheDocument();
        expect(screen.getByText('Concordo com os termos e condições*')).toBeInTheDocument();
        expect(screen.getByText('Submit')).toBeInTheDocument();
    });

    it('submits the form with valid data', async () => {
        const { getByLabelText, getByText, getByTestId } = render(<Login />);

        fireEvent.change(getByLabelText(/email/i), { target: { value: 'example@email.com' } });
        fireEvent.change(getByTestId('password'), { target: { value: 'password123' } });
        fireEvent.click(getByLabelText(/concordo com os termos e condições/i));

        fireEvent.click(getByText(/submit/i));

        await waitFor(() => {
            expect(getByText(/register/i)).toBeInTheDocument();
        });
    });

    it('validates the form with invalid data', async () => {
        const { getByLabelText, getByText } = render(<Login />);

        fireEvent.click(getByText(/submit/i));

        await waitFor(() => {
            expect(getByText(/o email deve ser informado/i)).toBeInTheDocument();
            expect(getByText(/a senha deve ser informada/i)).toBeInTheDocument();
            expect(getByText(/concordo com os termos e condições/i)).toBeInTheDocument();
        });
    });

});
