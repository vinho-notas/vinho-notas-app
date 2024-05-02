import { render, screen } from '@testing-library/react';
import Header from './Header';
import "@testing-library/jest-dom";

describe('Header', () => {

  it('should renders correctly', () => {
    render(<Header />);

    expect(screen.getByText('Home')).toBeInTheDocument();
    expect(screen.getByText('Cadastro')).toBeInTheDocument();
    expect(screen.getByText('Vinho')).toBeInTheDocument();
    expect(screen.getByText('Degustação')).toBeInTheDocument();
    expect(screen.getByText('Harmonização')).toBeInTheDocument();
  });

  it('should renders sub-menu items correctly', () => {
    const { getByText } = render(<Header />);

    expect(getByText('Usuários')).toBeInTheDocument();
    expect(getByText('Pessoas')).toBeInTheDocument();
    expect(getByText('Endereços')).toBeInTheDocument();
    expect(getByText('Vinhos')).toBeInTheDocument();
    expect(getByText('Avaliações')).toBeInTheDocument();    
    expect(getByText('Fichas de degustação')).toBeInTheDocument();    
  });

  it('should renders menu icons correctly', () => {
    const { container } = render(<Header />);

    expect(container.querySelector('.pi.pi-home')).toBeInTheDocument();
    expect(container.querySelector('.pi.pi-users')).toBeInTheDocument();
    expect(container.querySelector('.pi.pi-user-plus')).toBeInTheDocument();
    expect(container.querySelector('.pi.pi-folder-open')).toBeInTheDocument();
  });

  it('should renders menu urls correctly', () => {
    const { getByText } = render(<Header />);
   
    expect(getByText('Home').closest('a')).toHaveAttribute('href', '/home');    
    expect(getByText('Usuários').closest('a')).toHaveAttribute('href', '/users');
    expect(getByText('Pessoas').closest('a')).toHaveAttribute('href', '/persons');
    expect(getByText('Endereços').closest('a')).toHaveAttribute('href', '/address');
    expect(getByText('Vinhos').closest('a')).toHaveAttribute('href', '/wine-list');
    expect(getByText('Avaliações').closest('a')).toHaveAttribute('href', '/wine-review-list');
    expect(getByText('Fichas de degustação').closest('a')).toHaveAttribute('href', '/tasting-list');
    expect(getByText('Harmonização').closest('a')).toHaveAttribute('href', '/pairing-list');
    
  });

});
