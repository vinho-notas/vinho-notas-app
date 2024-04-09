import { render } from '@testing-library/react';
import Header from './Header';
import { BrowserRouter } from 'react-router-dom';
import "@testing-library/jest-dom";
import { screen } from '@testing-library/react';

describe('Header', () => {

  it('renders correctly', () => {
    render(
      <BrowserRouter>
        <Header />
      </BrowserRouter>
    )

    // screen.debug(); //imprime os elementos html renderizados
  // Verifica se o texto dos itens do menu está presente no componente renderizado
    expect(screen.getByText('Home')).toBeInTheDocument();
    expect(screen.getByText('Login')).toBeInTheDocument();
    expect(screen.getByText('Cadastro')).toBeInTheDocument();
    expect(screen.getByText('Vinho')).toBeInTheDocument();
    expect(screen.getByText('Degustação')).toBeInTheDocument();
  });

  it('renders sub-menu items correctly', () => {
    const { getByText } = render(<Header />);

    // Verifica se os sub-itens do menu estão presentes no componente renderizado
    expect(getByText('Cadastrar usuário')).toBeInTheDocument();
    expect(getByText('Cadastrar pessoa')).toBeInTheDocument();
    expect(getByText('Lista de usuários')).toBeInTheDocument();
    expect(getByText('Lista de pessoas')).toBeInTheDocument();
    expect(getByText('Lista de endereços')).toBeInTheDocument();
    expect(getByText('Lista de estados')).toBeInTheDocument();
    expect(getByText('Lista de países')).toBeInTheDocument();
    expect(getByText('Cadastrar Vinho')).toBeInTheDocument();
    expect(getByText('Listar vinhos')).toBeInTheDocument();
    expect(getByText('Listar avaliações')).toBeInTheDocument();
    expect(getByText('Registrar degustação')).toBeInTheDocument();
    expect(getByText('Ficha de degustação')).toBeInTheDocument();
    expect(getByText('Listar fichas de degustação')).toBeInTheDocument();
  });

  it('renders menu icons correctly', () => {
    const { container } = render(<Header />);

    // Verifica se os ícones estão presentes no componente renderizado
    expect(container.querySelector('.pi.pi-home')).toBeInTheDocument();
    expect(container.querySelector('.pi.pi-user')).toBeInTheDocument();
    expect(container.querySelector('.pi.pi-user-plus')).toBeInTheDocument();
    expect(container.querySelector('.pi.pi-folder-open')).toBeInTheDocument();
  });

  it('renders menu urls correctly', () => {
    const { getByText } = render(<Header />);

    // Verifica se as URLs estão corretas nos links do menu
    expect(getByText('Home').closest('a')).toHaveAttribute('href', '/');
    expect(getByText('Login').closest('a')).toHaveAttribute('href', '/login');
    expect(getByText('Cadastrar usuário').closest('a')).toHaveAttribute('href', '/user-registration');
    expect(getByText('Cadastrar pessoa').closest('a')).toHaveAttribute('href', '/registration');
    expect(getByText('Lista de usuários').closest('a')).toHaveAttribute('href', '/users');
    expect(getByText('Lista de pessoas').closest('a')).toHaveAttribute('href', '/persons');
    expect(getByText('Lista de endereços').closest('a')).toHaveAttribute('href', '/address');
    expect(getByText('Lista de estados').closest('a')).toHaveAttribute('href', '/states');
    expect(getByText('Lista de países').closest('a')).toHaveAttribute('href', '/countries');
    expect(getByText('Cadastrar Vinho').closest('a')).toHaveAttribute('href', '/wine');
    expect(getByText('Listar vinhos').closest('a')).toHaveAttribute('href', '/wine-list');
    expect(getByText('Listar avaliações').closest('a')).toHaveAttribute('href', '/wine-review-list');
    expect(getByText('Registrar degustação').closest('a')).toHaveAttribute('href', '/tasting');
    expect(getByText('Ficha de degustação').closest('a')).toHaveAttribute('href', '/tasting-card');
    expect(getByText('Listar fichas de degustação').closest('a')).toHaveAttribute('href', '/tasting-list');
  });

});