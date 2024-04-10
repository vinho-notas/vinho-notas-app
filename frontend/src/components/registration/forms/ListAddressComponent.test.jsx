import React from 'react';
import { render, fireEvent } from '@testing-library/react';
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
        const { getByText } = render(<ListAddressComponent />);

        expect(getByText('Descrição')).toBeInTheDocument();
        expect(getByText('Número')).toBeInTheDocument();
        expect(getByText('Complemento')).toBeInTheDocument();
        expect(getByText('Bairro')).toBeInTheDocument();
        expect(getByText('CEP')).toBeInTheDocument();
        expect(getByText('Cidade')).toBeInTheDocument();
        expect(getByText('Estado')).toBeInTheDocument();
        expect(getByText('País')).toBeInTheDocument();
    });

    it('should renders correctly Card and DataTable', () => {
        const { getByTestId } = render(<ListAddressComponent />);

        expect(getByTestId('address-table').textContent).toMatch(/Nenhum endereço encontrado./i);
        expect(getByTestId('principal-card').textContent).toMatch(/Lista de endereços/i);
    });

    it('should renders correctly leftToolbarTemplate', () => {
        const { getByText } = render(<ListAddressComponent />);

        expect(getByText('Descrição')).toBeInTheDocument();
        expect(getByText('Número')).toBeInTheDocument();
        expect(getByText('Complemento')).toBeInTheDocument();
        expect(getByText('Bairro')).toBeInTheDocument();
        expect(getByText('CEP')).toBeInTheDocument();
        expect(getByText('Cidade')).toBeInTheDocument();
        expect(getByText('Estado')).toBeInTheDocument();
        expect(getByText('País')).toBeInTheDocument();
    });

    it('should renders Buttons', () => {
        const { getByText } = render(<ListAddressComponent />);

        expect(getByText('Editar')).toBeInTheDocument();
        expect(getByText('Excluir')).toBeInTheDocument();
    });

    it('should renders rightToolbarTemplate', () => {
        const { getByText } = render(<ListAddressComponent />);

        expect(getByText('CSV')).toBeInTheDocument();
    });    

    it('should edit address correctly', () => {
        const { getByText } = render(<ListAddressComponent />);

        fireEvent.click(getByText('Editar'));
    });

        it('should delete address correctly', async () => {
        const { getByText } = render(<ListAddressComponent />);

        fireEvent.click(getByText('Excluir'));
    });

        it('should paginate and select addresses correctly', () => {
        const { getByText, queryByText, getByPlaceholderText } = render(<ListAddressComponent />); 

        fireEvent.change(getByPlaceholderText('Keyword Search'), { target: { value: 'Test' } });
        expect(queryByText('Nenhum endereço encontrado.')).toBeInTheDocument();
        expect(getByText('Nenhum endereço encontrado.')).toBeInTheDocument();
        fireEvent.click(getByText('Editar'));
    });

});

