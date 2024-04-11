import React from 'react';
import { render, screen, fireEvent } from '@testing-library/react';
import "@testing-library/jest-dom";
import ListPointScaleComponent from './ListPointScaleComponent';

//Mock useListPointScaleComponentHook
jest.mock("../../hooks/review/usePointScaleComponentHook", () => {
    return jest.fn(() => ({
        pointScales: [],
        navigate: jest.fn(),
        fetchPointScales: jest.fn(),
    }));
});

//Mock PointScaleService
jest.mock('../../service/review/PointScaleService', () => ({
    deletePointScale: jest.fn(),
    updatePointScale: jest.fn(),
}));


describe('ListPointScaleComponent', () => {
    it('should renders the component', () => {
        render(<ListPointScaleComponent />);

        expect(screen.getByText('O que foi degustado')).toBeInTheDocument();
        expect(screen.getByText('Quando foi degustado')).toBeInTheDocument();
        expect(screen.getByText('Aspectos visuais')).toBeInTheDocument();
        expect(screen.getByText('Aromas')).toBeInTheDocument();
        expect(screen.getByText('Sabores')).toBeInTheDocument();
        expect(screen.getByText('Opinião')).toBeInTheDocument();
        expect(screen.getByText('Avaliação final')).toBeInTheDocument();
    });

    it('should displays a list of point scales', () => {
        const mockPointScales = [
            { id: 1, name: 'Scale 1', value: 5 },
            { id: 2, name: 'Scale 2', value: 10 },
        ];
        render(<ListPointScaleComponent pointScales={mockPointScales} />);
    });

    it('should deletes a point scale', () => {
        const mockPointScales = [
            { id: 1, name: 'Scale 1', value: 5 },
            { id: 2, name: 'Scale 2', value: 10 },
        ];
        const mockDeletePointScale = jest.fn();
        render(
            <ListPointScaleComponent
                pointScales={mockPointScales}
                deletePointScale={mockDeletePointScale}
            />
        );
        const deleteButton = screen.getByText('Excluir');
        fireEvent.click(deleteButton);
    });


    it('deletes selected point scales and fetches updated point scales', async () => {
        const mockSelectedPointScales = [
            { id: 1, name: 'Scale 1', value: 5 },
            { id: 2, name: 'Scale 2', value: 10 },
        ];
        const mockDeletePointScale = jest.fn();
        const mockFetchPointScales = jest.fn();

        render(
            <ListPointScaleComponent
                selectedPointScales={mockSelectedPointScales}
                deletePointScale={mockDeletePointScale}
                fetchPointScales={mockFetchPointScales}
            />
        );
    });


    it('logs an error if deleting point scales fails', async () => {
        const mockSelectedPointScales = [
            { id: 1, name: 'Scale 1', value: 5 },
            { id: 2, name: 'Scale 2', value: 10 },
        ];
        const mockDeletePointScale = jest.fn(() => {
            throw new Error('Delete failed');
        });
        console.log = jest.fn();

        render(
            <ListPointScaleComponent
                selectedPointScales={mockSelectedPointScales}
                deletePointScale={mockDeletePointScale}
            />
        );
    });
});
