import React from 'react';
import { render, screen, fireEvent } from '@testing-library/react';
import "@testing-library/jest-dom";
import ListUserComponent from './ListUserComponent';

//Mock useListUserComponentHook
jest.mock("../../../hooks/registration/useListUserComponentHook", () => {
    return jest.fn(() => ({
        users: [],
        navigate: jest.fn(),
        fetchUsers: jest.fn(),
    }));
});

//Mock UserService
jest.mock('../../../service/registration/UserService', () => ({
    updateUser: jest.fn(),
    deleteUser: jest.fn(),
}));

describe('ListUserComponent', () => {
    it('renders without crashing', () => {
        render(<ListUserComponent />);
        expect(screen.getByText('Nome')).toBeInTheDocument();
        expect(screen.getByText('Email')).toBeInTheDocument();
        expect(screen.getByText('Perfil')).toBeInTheDocument();
    });

    it('calls the updateUser function when the update button is clicked', () => {
        const mockUpdateUser = jest.fn();
        render(<ListUserComponent updateUser={mockUpdateUser} />);

        fireEvent.click(screen.getByTestId('update-button'));
    });

    it('calls the deleteUser function when the delete button is clicked', () => {
        const mockDeleteUser = jest.fn();
        render(<ListUserComponent deleteUser={mockDeleteUser} />);
        fireEvent.click(screen.getByTestId('delete-button'));
    });
});
