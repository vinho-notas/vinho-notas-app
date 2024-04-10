import React from 'react';
import { render, screen } from '@testing-library/react';
import "@testing-library/jest-dom";
import ListStateComponent from './ListStateComponent';

// Mock do useListStateComponentHook
jest.mock("../../../hooks/registration/useListStateComponentHook", () => {
    return jest.fn(() => ({
        state: [],
        navigate: jest.fn(),
        fetchState: jest.fn(),
    }));
});

describe('ListStateComponent', () => {
    test('renders without error', () => {
        render(<ListStateComponent />);

        expect(screen.getByText('Lista de estados')).toBeInTheDocument();
        expect(screen.getByText('Estado')).toBeInTheDocument();
        expect(screen.getByText('UF')).toBeInTheDocument();

    });
});