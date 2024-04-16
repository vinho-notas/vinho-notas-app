import { render, screen } from '@testing-library/react';
import Header from './Header';
import "@testing-library/jest-dom";

describe('Header', () => {

  it('renders correctly', () => {
    render(<Header />);

    expect(screen.getByText('Home')).toBeInTheDocument();
    expect(screen.getByText('Login')).toBeInTheDocument();
    expect(screen.getByText('Cadastro')).toBeInTheDocument();
    expect(screen.getByText('Vinho')).toBeInTheDocument();
    expect(screen.getByText('Degustação')).toBeInTheDocument();
  });

  it('renders sub-menu items correctly', () => {
    const { getByText } = render(<Header />);

    expect(getByText('Usuários')).toBeInTheDocument();
    expect(getByText('Pessoas')).toBeInTheDocument();
    expect(getByText('Endereços')).toBeInTheDocument();
    expect(getByText('Vinhos')).toBeInTheDocument();
    expect(getByText('Avaliações')).toBeInTheDocument();
    expect(getByText('Registrar degustação')).toBeInTheDocument();
    expect(getByText('Ficha de degustação')).toBeInTheDocument();
    expect(getByText('Listar fichas de degustação')).toBeInTheDocument();
  });

  it('renders menu icons correctly', () => {
    const { container } = render(<Header />);

    expect(container.querySelector('.pi.pi-home')).toBeInTheDocument();
    expect(container.querySelector('.pi.pi-user')).toBeInTheDocument();
    expect(container.querySelector('.pi.pi-user-plus')).toBeInTheDocument();
    expect(container.querySelector('.pi.pi-folder-open')).toBeInTheDocument();
  });

  it('renders menu urls correctly', () => {
    const { getByText } = render(<Header />);
   
    expect(getByText('Home').closest('a')).toHaveAttribute('href', '/');
    expect(getByText('Login').closest('a')).toHaveAttribute('href', '/login');
    expect(getByText('Usuários').closest('a')).toHaveAttribute('href', '/users');
    expect(getByText('Pessoas').closest('a')).toHaveAttribute('href', '/persons');
    expect(getByText('Endereços').closest('a')).toHaveAttribute('href', '/address');
    expect(getByText('Vinhos').closest('a')).toHaveAttribute('href', '/wine-list');
    expect(getByText('Avaliações').closest('a')).toHaveAttribute('href', '/wine-review-list');
    expect(getByText('Registrar degustação').closest('a')).toHaveAttribute('href', '/tasting');
    expect(getByText('Ficha de degustação').closest('a')).toHaveAttribute('href', '/tasting-card');
    expect(getByText('Listar fichas de degustação').closest('a')).toHaveAttribute('href', '/tasting-list');
  });

});
