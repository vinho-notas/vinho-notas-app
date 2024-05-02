import React from 'react';
import { render, screen, fireEvent } from '@testing-library/react';
import "@testing-library/jest-dom";
import PersonRegistration from './PersonRegistration';
import { BrowserRouter } from 'react-router-dom';

//Mock usePersonComponentHook
jest.mock("../../hooks/registration/usePersonComponentHook", () => {
    return jest.fn(() => ({
        name: jest.fn(), setName: jest.fn(),
        document: jest.fn(), setDocument: jest.fn(),
        birthDate: jest.fn(), setBirthDate: jest.fn(),
        address: jest.fn(), setAddress: jest.fn(),
        dthreg: jest.fn(), setDthreg: jest.fn(),
        userreg: jest.fn(), setUserreg: jest.fn(),
        dthalt: jest.fn(), setDthalt: jest.fn(),
        useralt: jest.fn(), setUseralt: jest.fn(),
        savePerson: jest.fn(),
    }));
});

//Mock useListStateComponentHook
jest.mock("../../hooks/registration/useListStateComponentHook", () => {
    return jest.fn(() => ({
        state: [],
        navigate: jest.fn(),
        fetchState: jest.fn(),
    }));
});

//Mock useListCountryComponentHook
jest.mock("../../hooks/registration/useListCountryComponentHook", () => {
    return jest.fn(() => ({
        country: [],
        navigate: jest.fn(),
        fetchCountry: jest.fn(),
    }));
});

describe('PersonRegistration', () => {
    test('renders the component', () => {
        render(
            <>
                <BrowserRouter>
                    <PersonRegistration />
                </BrowserRouter>
            </>
        );

        expect(screen.getByText('Cadastro de Pessoa')).toBeInTheDocument();
        expect(screen.getByText('Nome')).toBeInTheDocument();
        expect(screen.getByText('CPF')).toBeInTheDocument();
        expect(screen.getByText('Data de Nascimento')).toBeInTheDocument();
        expect(screen.getByText('Cadastro de Endereço')).toBeInTheDocument();
        expect(screen.getByText('Endereço')).toBeInTheDocument();
        expect(screen.getByText('Número')).toBeInTheDocument();
        expect(screen.getByText('Complemento')).toBeInTheDocument();
        expect(screen.getByText('Bairro')).toBeInTheDocument();
        expect(screen.getByText('CEP')).toBeInTheDocument();
        expect(screen.getByText('Cidade')).toBeInTheDocument();
        expect(screen.getByText('UF')).toBeInTheDocument();
        expect(screen.getByText('País')).toBeInTheDocument();
        expect(screen.getByText('Telefone')).toBeInTheDocument();
        expect(screen.getByText('Limpar Formulário')).toBeInTheDocument();
        expect(screen.getByText('Confirmar')).toBeInTheDocument();
    });

    test('submits the form', () => {
        render(
            <>
                <BrowserRouter>
                    <PersonRegistration />
                </BrowserRouter>
            </>
        );

        const submitButton = screen.getByText('Confirmar');
        fireEvent.click(submitButton);

        const cleanButton = screen.getByText('Limpar Formulário');
        fireEvent.click(submitButton);
    });
});
