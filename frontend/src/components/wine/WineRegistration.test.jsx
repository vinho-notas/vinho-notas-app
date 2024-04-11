import React from 'react';
import { render, screen, fireEvent } from '@testing-library/react';
import WineRegistration from './WineRegistration';

//Mock useWineComponentHook
jest.mock("../../hooks/wine/useWineComponentHook", () => {
    return jest.fn(() => ({
        name: jest.fn(), setName: jest.fn(),
        price: jest.fn(), setPrice: jest.fn(),
        purchaseLocation: jest.fn(), setPurchaseLocation: jest.fn(),
        purchaseDate: jest.fn(), setPurchaseDate: jest.fn(),
        wineType: jest.fn(), setWineType: jest.fn(),
        wineClassification: jest.fn(), setWineClassification: jest.fn(),
        alcoholContent: jest.fn(), setAlcoholContent: jest.fn(),
        volumeMl: jest.fn(), setVolumeMl: jest.fn(),
        grape: jest.fn(), setGrape: jest.fn(),
        winery: jest.fn(), setWinery: jest.fn(),
        serviceTemperature: jest.fn(), setServiceTemperature: jest.fn(),
        harvest: jest.fn(), setHarvest: jest.fn(),
        country: jest.fn(), setCountry: jest.fn(),
        guardTime: jest.fn(), setGuardTime: jest.fn(),
        region: jest.fn(), setRegion: jest.fn(),
        maturation: jest.fn(), setMaturation: jest.fn(),
        harmonization: jest.fn(), setHarmonization: jest.fn(),
        dthreg: jest.fn(), userreg: jest.fn(), dthalt: jest.fn(), useralt: jest.fn(),
        saveWine: jest.fn(),
    }));
});

describe('WineRegistration', () => {
    it('should renders without crashing', () => {
        render(<WineRegistration />);
    });

    it('should displays the form fields', () => {
        render(<WineRegistration />);
        
        expect(screen.getByText('Cadastro de Vinho')).toBeInTheDocument();
        expect(screen.getByText('Rótulo')).toBeInTheDocument();
        expect(screen.getByText('Preço')).toBeInTheDocument();
        expect(screen.getByText('Local de compra')).toBeInTheDocument();
        expect(screen.getByText('Data de compra')).toBeInTheDocument();
        expect(screen.getByText('Tipo de vinho')).toBeInTheDocument();
        expect(screen.getByText('Selectione o tipo de vinho')).toBeInTheDocument();
        expect(screen.getByText('Vinho Tinto')).toBeInTheDocument();
        expect(screen.getByText('Vinho Branco')).toBeInTheDocument();
        expect(screen.getByText('Vinho Rose')).toBeInTheDocument();
        expect(screen.getByText('Vinho Espumante')).toBeInTheDocument();
        expect(screen.getByText('Vinho Fortificado')).toBeInTheDocument();
        expect(screen.getByText('Vinho de Safra')).toBeInTheDocument();
        expect(screen.getByText('Vinho Assemblage')).toBeInTheDocument();
        expect(screen.getByText('Vinho Orgânico')).toBeInTheDocument();
        expect(screen.getByText('Classificação do vinho')).toBeInTheDocument();
        expect(screen.getByText('Selectione a classificação do vinho')).toBeInTheDocument();
        expect(screen.getByText('Vinho Seco')).toBeInTheDocument();
        expect(screen.getByText('Vinho Meio Seco')).toBeInTheDocument();
        expect(screen.getByText('Vinho Demi Sec')).toBeInTheDocument();
        expect(screen.getByText('Vinho de Colheita Tardia')).toBeInTheDocument();
        expect(screen.getByText('Vinho Gelado')).toBeInTheDocument();
        expect(screen.getByText('Graduação alcoólica')).toBeInTheDocument();
        expect(screen.getByText('Volume da garrafa (ml)')).toBeInTheDocument();
        expect(screen.getByText('Uva')).toBeInTheDocument();
        expect(screen.getByText('Produtor')).toBeInTheDocument();
        expect(screen.getByText('Temperatura de serviço')).toBeInTheDocument();
        expect(screen.getByText('Safra')).toBeInTheDocument();
        expect(screen.getByText('País de origem')).toBeInTheDocument();
        expect(screen.getByText('Tempo de guarda')).toBeInTheDocument();
        expect(screen.getByText('Região')).toBeInTheDocument();
        expect(screen.getByText('Maturação')).toBeInTheDocument();
        expect(screen.getByText('Harmonização')).toBeInTheDocument();
        expect(screen.getByText('Cadastrar')).toBeInTheDocument();
    });

    it('should updates the state when form fields are changed', () => {
        render(<WineRegistration />);
        const nameInput = screen.getByTestId('form-control-name');

        fireEvent.change(nameInput, { target: { value: '' } });

        expect(nameInput.value).toBe('');        
    });

    it('should calls the submit function when the form is submitted', () => {
        const handleSubmit = jest.fn();
        render(
            <WineRegistration onSubmit={handleSubmit} />
        );
        const nameInput = screen.getByTestId('form-control-name');
        const submitButton = screen.getByText('Cadastrar');

        fireEvent.change(nameInput, { target: { value: 'New Wine' } });
        fireEvent.click(submitButton);
    });
});
