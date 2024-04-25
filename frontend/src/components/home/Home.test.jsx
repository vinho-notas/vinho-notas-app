import { render, screen } from '@testing-library/react';
import "@testing-library/jest-dom";
import Home from './Home';

describe('Home', () => {
    it('should renders correctly', () => {
        render(<Home />);
        
        expect(screen.getByText('Sejam bem-vindos ao Vinho Notas!')).toBeInTheDocument();        
    });

});
