import React from 'react';
import { render, screen, fireEvent } from '@testing-library/react';
import "@testing-library/jest-dom";
import UserRegistration from './UserRegistration';
import { BrowserRouter } from 'react-router-dom';

//Mock useUserComponentHook
jest.mock("../../hooks/registration/useUserComponentHook", () => {
    return jest.fn(() => ({
        personId: jest.fn(), setPersonId: jest.fn(),
        enumProfile: jest.fn(), setEnumProfile: jest.fn(),
        email: jest.fn(), setEmail: jest.fn(),
        password: jest.fn(), setPassword: jest.fn(),
        dthreg: jest.fn(), setDthreg: jest.fn(),
        userreg: jest.fn(), setUserreg: jest.fn(),
        dthalt: jest.fn(), setDthalt: jest.fn(),
        useralt: jest.fn(), setUseralt: jest.fn(),
        person: jest.fn(), setPerson: jest.fn(),
        saveUser: jest.fn(),
    }));
});

//Mock useListPersonComponentHook
jest.mock("../../hooks/registration/useListPersonComponentHook", () => {
    return jest.fn(() => ({
        persons: [],
        navigate: jest.fn(),
        fetchPersons: jest.fn(),
    }));
});


describe('UserRegistration', () => {
    it('should renders the component', () => {
        render(
            <>
                <BrowserRouter>
                    <UserRegistration />
                </BrowserRouter>
            </>
        );
        expect(screen.getByText('Cadastro de Usuários')).toBeInTheDocument();
        expect(screen.getByText('Pessoa')).toBeInTheDocument();
        expect(screen.getByText('Perfil')).toBeInTheDocument();
        expect(screen.getByText('Email')).toBeInTheDocument();
        expect(screen.getByText('Senha')).toBeInTheDocument();
        expect(screen.getByText('Limpar Formulário')).toBeInTheDocument();
        expect(screen.getByText('Confirmar')).toBeInTheDocument();
    });

    it('should handle form submission', async () => {
        render(
            <>
            <BrowserRouter>
                <UserRegistration />
            </BrowserRouter>
        </>
        );

        fireEvent.change(screen.getByTestId('label-pessoa'));
        fireEvent.change(screen.getByTestId('label-perfil'));
        fireEvent.change(screen.getByTestId('label-email'));
        fireEvent.change(screen.getByTestId('label-senha'));

        fireEvent.submit(screen.getByTestId('user-registration-form'));
    });
});