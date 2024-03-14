import { Menubar } from 'primereact/menubar';

const Header = () => {
  const items = [
    { label: 'Home', icon: 'pi pi-home', url: '/' },
    { label: 'Login', icon: 'pi pi-user', url: '/login' },
    { label: 'Cadastro', icon: 'pi pi-user-plus', 
      items: [
        { label: 'Cadastrar', icon: 'pi pi-user-plus', url: '/registration' },
        { label: 'Usuários', icon: 'pi pi-users', url: '/users' },
        { label: 'Pessoas', icon: 'pi pi-users', url: '/persons' },
        { label: 'Endereços', icon: 'pi pi-folder-open', url: '/address' },
        { label: 'Estados', icon: 'pi pi-folder-open', url: '/states' },
        { label: 'Países', icon: 'pi pi-folder-open', url: '/countries' }
      ]
  },
    
    { label: 'Vinho', icon: 'pi pi-folder-open',
      items: [
        {label: 'Cadastrar Vinho', icon: 'pi pi-folder-open', url: '/wine' },
        { label: 'Listar vinhos', icon: 'pi pi-folder-open', url: '/wine-list' }
      ]    
    },
    { label: 'Avaliação', icon: 'pi pi-folder-open',
      items: [
        { label: 'Avaliar vinho', icon: 'pi pi-folder-open', url: '/wine-review' },
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
      <Menubar model={items} />
    </div>
  )
}

export default Header