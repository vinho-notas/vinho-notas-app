import { render, screen } from '@testing-library/react';
import "@testing-library/jest-dom";
import Footer from './Footer';
import { FaRegArrowAltCircleRight } from 'react-icons/fa';

describe('Footer', () => {
    it('renders correctly', () => {
        render(<Footer />);
       
        expect(screen.getByText('Sobre')).toBeInTheDocument();
        expect(screen.getByText('Fale conosco')).toBeInTheDocument();
        expect(screen.getByText('Linkedin')).toBeInTheDocument();
        expect(screen.getByText('Vinho Notas')).toBeInTheDocument();        
        expect(screen.getByText(/All Right Reserved\./)).toBeInTheDocument();
    });

    it('renders footer links correctly', () => {
        render(<Footer />);
        const companyLinks = [
            { label: 'Sobre', icon: FaRegArrowAltCircleRight, Link: 'about' },
            { label: 'Fale conosco', icon: FaRegArrowAltCircleRight, Link: 'mailto:vanderlei.master@gmail.com' },
            { label: 'Linkedin', icon: FaRegArrowAltCircleRight, Link: 'https://www.linkedin.com/in/vanderlei-kleinschmidt-a1557731/'}
          ]; 

          companyLinks.forEach((link, index) => {
            expect(screen.getByText(link.label)).toBeInTheDocument();
            expect(screen.getByText(link.label).closest('a')).toHaveAttribute('href', link.Link);
          });
        
    });

});
