import React from 'react';
import { render, fireEvent, screen } from '@testing-library/react';
import "@testing-library/jest-dom";
import ListAddressComponent from './ListAddressComponent';

// Mock do AddressService
jest.mock('../../../service/registration/AddressService', () => ({
    updateAddress: jest.fn(),
    deleteAddress: jest.fn(),
}));

// Mock do useListAddressComponentHook
jest.mock("../../../hooks/registration/useListAddressComponentHook", () => {
    return jest.fn(() => ({
        address: [],
        navigate: jest.fn(),
        fetchAddress: jest.fn(),
    }));
});

describe('ListAddressComponent', () => {
    it('should renders correctly columns', () => {
        render(<ListAddressComponent />);

        expect(screen.getByText('Descrição')).toBeInTheDocument();
        expect(screen.getByText('Número')).toBeInTheDocument();
        expect(screen.getByText('Complemento')).toBeInTheDocument();
        expect(screen.getByText('Bairro')).toBeInTheDocument();
        expect(screen.getByText('CEP')).toBeInTheDocument();
        expect(screen.getByText('Cidade')).toBeInTheDocument();
        expect(screen.getByText('Estado')).toBeInTheDocument();
        expect(screen.getByText('País')).toBeInTheDocument();
    });

    it('should renders correctly Card and DataTable', () => {
        render(<ListAddressComponent />);

        expect(screen.getByTestId('address-table').textContent).toMatch(/Nenhum endereço encontrado./i);
        expect(screen.getByTestId('principal-card').textContent).toMatch(/Lista de endereços/i);
    });

    it('should renders correctly leftToolbarTemplate', () => {
        render(<ListAddressComponent />);

        expect(screen.getByText('Descrição')).toBeInTheDocument();
        expect(screen.getByText('Número')).toBeInTheDocument();
        expect(screen.getByText('Complemento')).toBeInTheDocument();
        expect(screen.getByText('Bairro')).toBeInTheDocument();
        expect(screen.getByText('CEP')).toBeInTheDocument();
        expect(screen.getByText('Cidade')).toBeInTheDocument();
        expect(screen.getByText('Estado')).toBeInTheDocument();
        expect(screen.getByText('País')).toBeInTheDocument();
    });

    it('should renders Buttons', () => {
        render(<ListAddressComponent />);

        expect(screen.getByText('Editar')).toBeInTheDocument();
        expect(screen.getByText('Excluir')).toBeInTheDocument();
    });

    it('should renders rightToolbarTemplate', () => {
        render(<ListAddressComponent />);

        expect(screen.getByText('CSV')).toBeInTheDocument();
    });    

    it('should edit address correctly', () => {
        render(<ListAddressComponent />);

        fireEvent.click(screen.getByText('Editar'));
    });

        it('should delete address correctly', async () => {
         render(<ListAddressComponent />);

        fireEvent.click(screen.getByText('Excluir'));
    });

        it('should paginate and select addresses correctly', () => {
        render(<ListAddressComponent />); 

        fireEvent.change(screen.getByPlaceholderText('Keyword Search'), { target: { value: 'Test' } });
        expect(screen.queryByText('Nenhum endereço encontrado.')).toBeInTheDocument();
        expect(screen.getByText('Nenhum endereço encontrado.')).toBeInTheDocument();
        fireEvent.click(screen.getByText('Editar'));
    });

});

