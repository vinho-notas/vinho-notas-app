import { render, screen } from '@testing-library/react';
import "@testing-library/jest-dom";
import Home from './Home';

describe('Home', () => {
    it('renders correctly', () => {
        render(<Home />);
        
        expect(screen.getByText('Hello, world!')).toBeInTheDocument();
        expect(screen.getByText('This is a simple hero unit, a simple jumbotron-style component for calling extra attention to featured content or information.')).toBeInTheDocument();
        expect(screen.getByText('It uses utility classes for typography and spacing to space content out within the larger container.')).toBeInTheDocument();
        expect(screen.getByText('Learn more')).toBeInTheDocument();
    });

    it('renders button correctly', () => {
        render(<Home />);
        expect(screen.getByText('Learn more').closest('a')).toHaveAttribute('href', '#');
    });

});
