import React from 'react';
import { render, fireEvent, screen } from '@testing-library/react';
import "@testing-library/jest-dom";
import ListPersonComponent from './ListPersonComponent';

//Mock useListPersonComponentHook
jest.mock("../../../hooks/registration/useListPersonComponentHook", () => {
    return jest.fn(() => ({
        persons: [],
        navigate: jest.fn(),
        fetchPersons: jest.fn(),
    }));
});

//Mock PersonService
jest.mock('../../../service/registration/PersonService', () => ({
    updatePerson: jest.fn(),
    deletePerson: jest.fn(),
}));

describe('ListPersonComponent', () => {
    it('should renders correctly columns', () => {
        render(<ListPersonComponent />);

        expect(screen.getByText('Nome')).toBeInTheDocument();
        expect(screen.getByText('Documento')).toBeInTheDocument();
        expect(screen.getByText('Data de Nascimento')).toBeInTheDocument();
    });

    it('should renders correctly leftToolbarTemplate button', () => {
        render(<ListPersonComponent />);

        expect(screen.getByRole('button', { name: 'Novo' })).toBeInTheDocument();
        expect(screen.getByRole('button', { name: 'Editar' })).toBeInTheDocument();
        expect(screen.getByRole('button', { name: 'Excluir' })).toBeInTheDocument();
    });

    it('should renders correctly Card and DataTable', () => {
        render(<ListPersonComponent />);

        expect(screen.getByTestId('person-table').textContent).toMatch(/Nenhum registro encontrado/i);
        expect(screen.getByTestId('principal-card').textContent).toMatch(/Lista de pessoas/i);
        
    });

    it('should renders correctly CSV', () => {
        render(<ListPersonComponent />);

        expect(screen.getByRole('button', { name: 'CSV' })).toBeInTheDocument();
        expect(screen.getByText('CSV')).toBeInTheDocument();
    });

    it('should edit and delete correctly', () => {
        render(<ListPersonComponent />);

        fireEvent.click(screen.getByText('Editar'));
        fireEvent.click(screen.getByText('Excluir'));
        fireEvent.change(screen.getByPlaceholderText('Keyword Search'), { target: { value: 'Test' } });
    });

});
