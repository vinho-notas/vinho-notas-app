import React from 'react';
import { render, screen } from '@testing-library/react';
import "@testing-library/jest-dom";
import ListCountryComponent from './ListCountryComponent';

// Mock do useListCountryComponentHook
jest.mock("../../../hooks/registration/useListCountryComponentHook", () => {
    return jest.fn(() => ({
        country: [],
        navigate: jest.fn(),
        fetchCountry: jest.fn(),
    }));
});

describe('ListCountryComponent', () => {
    test('renders without error', () => {
        render(<ListCountryComponent />);

        expect(screen.getByText('Lista de países')).toBeInTheDocument();       
        expect(screen.getByText('País')).toBeInTheDocument();
        expect(screen.getByText('Continente')).toBeInTheDocument();
        
        expect(screen.getByText('Países')).toBeInTheDocument();

    });

});
