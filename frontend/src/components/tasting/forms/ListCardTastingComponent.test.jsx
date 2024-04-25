import React from 'react';
import { render, screen } from '@testing-library/react';
import "@testing-library/jest-dom";
import ListCardTastingComponent from './ListTastingCardComponent';

//Mock useListTastingCardComponentHook
jest.mock('../../../hooks/tasting/useListTastingCardComponentHook', () => {
    return jest.fn(() => ({
        tastingCards: [],
        navigate: jest.fn(),
        fetchTastingCards: jest.fn(),
    }));
});

//Mock TastingCardService
jest.mock('../../../service/tasting/TastingCardService', () => ({
    deleteTastingCard: jest.fn(),
    updateTastingCard: jest.fn(),
    deleteAllTastingCards: jest.fn(),
}));

describe('ListCardTastingComponent', () => {

    it('should renders the list of card tastings', () => {
        render(<ListCardTastingComponent />);

        expect(screen.getAllByText(/Vinho degustado/)).textContent;
        expect(screen.getAllByText(/Data da degustação/)).textContent;
        expect(screen.getAllByText(/Safra/)).textContent;
        expect(screen.getAllByText(/Uvas/)).textContent;
        expect(screen.getAllByText(/País/)).textContent;
        expect(screen.getAllByText(/Região/)).textContent;
        expect(screen.getAllByText(/Tipo de degustação/)).textContent;
        expect(screen.getAllByText(/Clareza/)).textContent;
        expect(screen.getAllByText(/Brilho/)).textContent;
        expect(screen.getAllByText(/Viscosidade/)).textContent;
        expect(screen.getAllByText(/Tinto/)).textContent;
        expect(screen.getAllByText(/Branco/)).textContent;
        expect(screen.getAllByText(/Rosé/)).textContent;
        expect(screen.getAllByText(/Classificação inspeção visual/)).textContent;
        expect(screen.getAllByText(/Intensidade/)).textContent;
        expect(screen.getAllByText(/Persistência olfativa/)).textContent;        
        expect(screen.getAllByText(/Qualidade/)).textContent;
        expect(screen.getAllByText(/Frutados/)).textContent;
        expect(screen.getAllByText(/Frutas secas/)).textContent;        
        expect(screen.getAllByText(/Florais/)).textContent;
        expect(screen.getAllByText(/Vegetais e herbáceos/)).textContent;
        expect(screen.getAllByText(/Minerais/)).textContent;
        expect(screen.getAllByText(/Especiarias/)).textContent;
        expect(screen.getAllByText(/Animais/)).textContent;
        expect(screen.getAllByText(/Empireumáticos/)).textContent;
        expect(screen.getAllByText(/Madeira/)).textContent;
        expect(screen.getAllByText(/Químicos e etéreos/)).textContent;
        expect(screen.getAllByText(/Lácteos/)).textContent;
        expect(screen.getAllByText(/Adocicados/)).textContent;
        expect(screen.getAllByText(/Classificação olfativa/)).textContent;
        expect(screen.getAllByText(/Corpo/)).textContent;
        expect(screen.getAllByText(/Doçura/)).textContent;
        expect(screen.getAllByText(/Tanino/)).textContent;
        expect(screen.getAllByText(/Acidez/)).textContent;
        expect(screen.getAllByText(/Álcool/)).textContent;
        expect(screen.getAllByText(/Persistência gustatória/)).textContent;
        expect(screen.getAllByText(/Maturidade/)).textContent;
        expect(screen.getAllByText(/Tipicidade/)).textContent;
        expect(screen.getAllByText(/Classificação gustatória/)).textContent;
        expect(screen.getAllByText(/Opinião/)).textContent;
        expect(screen.getAllByText(/Escala de pontuação/)).textContent;
    });

});
