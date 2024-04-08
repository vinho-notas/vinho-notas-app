import { Menubar } from 'primereact/menubar';

const Header = () => {
  const items = [
    { label: 'Home', icon: 'pi pi-home', url: '/' },
    { label: 'Login', icon: 'pi pi-user', url: '/login' },
    { label: 'Cadastro', icon: 'pi pi-user-plus', 
      items: [
        { label: 'Cadastrar usuário', icon: 'pi pi-user-plus', url: '/user-registration' },
        { label: 'Cadastrar pessoa', icon: 'pi pi-user-plus', url: '/registration' },
        { label: 'Lista de usuários', icon: 'pi pi-users', url: '/users' },
        { label: 'Lista de pessoas', icon: 'pi pi-users', url: '/persons' },
        { label: 'Lista de endereços', icon: 'pi pi-folder-open', url: '/address' },
        { label: 'Lista de estados', icon: 'pi pi-folder-open', url: '/states' },
        { label: 'Lista de países', icon: 'pi pi-folder-open', url: '/countries' }
      ]
  },
    
    { label: 'Vinho', icon: 'pi pi-folder-open',
      items: [
        {label: 'Cadastrar Vinho', icon: 'pi pi-folder-open', url: '/wine' },
        { label: 'Listar vinhos', icon: 'pi pi-folder-open', url: '/wine-list' },
        { label: 'Listar avaliações', icon: 'pi pi-folder-open', url: '/wine-review-list' }
      ]    
    },
    {
      label: 'Degustação', icon: 'pi pi-folder-open',
      items: [
        { label: 'Registrar degustação', icon: 'pi pi-folder-open', url: '/tasting' },
        { label: 'Ficha de degustação', icon: 'pi pi-folder-open', url: '/tasting-card' },        
        { label: 'Listar fichas de degustação', icon: 'pi pi-folder-open', url: '/tasting-list' }
      ]
    }
  ];

  return (
    <div >
      <Menubar model={items} style={{textAlign: 'start'}}/>
    </div>
  )
}

export default Header