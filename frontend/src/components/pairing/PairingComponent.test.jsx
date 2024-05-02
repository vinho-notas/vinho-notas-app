import React from 'react';
import { render, screen, fireEvent } from '@testing-library/react';
import PairingComponent from './PairingComponent';

// Mock do PairingService
jest.mock('../../service/harmonizacao/PairingService', () => ({    
    getWineInformation: jest.fn(),
    getWinePairing: jest.fn(),
    getMenuPairing: jest.fn(),
}));

describe('PairingComponent', () => {
    test('should renders without errors', () => {
        render(<PairingComponent />);

        expect(screen.getByText('Escreva aqui o nome do vinho que você deseja harmonizar')).toBeInTheDocument();
        expect(screen.getByText('Sugestão de harmonização')).toBeInTheDocument();
        expect(screen.getByText('Informações sobre o vinho')).toBeInTheDocument();
        expect(screen.getByText('Sugestão de hamonização')).toBeInTheDocument();
        expect(screen.getByText('Sugestão de menu')).toBeInTheDocument();
    });

    test('should call getWineInformation when button is clicked', () => {
        const { getWineInformation } = require('../../service/harmonizacao/PairingService');
        render(<PairingComponent />);

        fireEvent.change(screen.getByRole('textbox'), { target: { value: 'wine' } });
        fireEvent.click(screen.getByText('Informações sobre o vinho'));

        expect(getWineInformation).toHaveBeenCalledWith({ wine: 'wine' });
    });
    
});
