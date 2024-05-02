import React from 'react';
import { render, screen, fireEvent } from '@testing-library/react';
import "@testing-library/jest-dom";
import ListWineComponent from './ListWineComponent';

//Mock useListWineComponentHook
jest.mock("../../../hooks/wine/useListWineComponentHook", () => {
    return jest.fn(() => ({
        wines: [],
        navigate: jest.fn(),
        fetchWines: jest.fn(),
    }));
});

//Mock PointScaleService
jest.mock('../../../service/review/PointScaleService', () => ({
    createPointScale: jest.fn(),
}));

//Mock WineService
jest.mock('../../../service/wine/WineService', () => ({
    updateWine: jest.fn(),
    deleteWine: jest.fn(),
}));


describe('ListWineComponent', () => {
    it('should renders without error', () => {
        render(<ListWineComponent />);
        expect(screen.getByTestId('card-component')).toBeInTheDocument();
        expect(screen.getByTestId('datatable-component')).toBeInTheDocument();
    });

    it('should renders columns in the datatable', () => {
        render(<ListWineComponent />);
        expect(screen.getAllByText(/Vinho/i)).textContent;
        expect(screen.getAllByText(/Local de compra/i)).textContent;
        expect(screen.getAllByText(/Preço/i)).textContent;
        expect(screen.getAllByText(/Data de compra/i)).textContent;
        expect(screen.getAllByText(/Classificação/i)).textContent;
        expect(screen.getAllByText(/Tipo/i)).textContent;
        expect(screen.getAllByText(/Graduação Alcoólica/i)).textContent;
        expect(screen.getAllByText(/Volume em ML/i)).textContent;
        expect(screen.getAllByText(/Uvas/i)).textContent;
        expect(screen.getAllByText(/Produtor/i)).textContent;
        expect(screen.getAllByText(/Temperatura de serviço/i)).textContent;
        expect(screen.getAllByText(/Safra/i)).textContent;
        expect(screen.getAllByText(/Tempo de guarda/i)).textContent;
        expect(screen.getAllByText(/País/i)).textContent;
        expect(screen.getAllByText(/Região/i)).textContent;
        expect(screen.getAllByText(/Maturação/i)).textContent;
        expect(screen.getAllByText(/Harmonização/i)).textContent;

    });

    it('should renders a multiselect component', () => {
        render(<ListWineComponent />);
        expect(screen.getByTestId('multiselect-component')).toBeInTheDocument();

    });

    it('should renders a toolbar component', () => {
        render(<ListWineComponent />);
        expect(screen.getByTestId('toolbar-component')).toBeInTheDocument();
    });

    it('should renders a button component', () => {
        render(<ListWineComponent />);
        expect(screen.getByTestId('button-component-novo')).toBeInTheDocument();
        expect(screen.getByTestId('button-component-editar')).toBeInTheDocument();
        expect(screen.getByTestId('button-component-excluir')).toBeInTheDocument();
        expect(screen.getByTestId('button-component-avaliar')).toBeInTheDocument();

        fireEvent.click(screen.getByTestId('button-component-novo'));
        fireEvent.click(screen.getByTestId('button-component-editar'));
        fireEvent.click(screen.getByTestId('button-component-excluir'));
        fireEvent.click(screen.getByTestId('button-component-avaliar'));
    });

});
