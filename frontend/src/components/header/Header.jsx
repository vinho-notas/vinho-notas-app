import { Menubar } from 'primereact/menubar';

const Header = () => {
  const items = [
    { label: 'Home', icon: 'pi pi-home', url: '/' },
    { label: 'Login', icon: 'pi pi-user', url: '/login' },
    { label: 'Cadastro', icon: 'pi pi-user-plus', 
      items: [
        { label: 'Usuários', icon: 'pi pi-users', url: '/users' },
        { label: 'Pessoas', icon: 'pi pi-users', url: '/persons' },
        { label: 'Endereços', icon: 'pi pi-folder-open', url: '/address' }
      ]
  },
    
    { label: 'Vinho', icon: 'pi pi-folder-open',
      items: [
        { label: 'Vinhos', icon: 'pi pi-folder-open', url: '/wine-list' },
        { label: 'Avaliações', icon: 'pi pi-folder-open', url: '/wine-review-list' }
      ]    
    },
    {
      label: 'Degustação', icon: 'pi pi-folder-open',
      items: [        
               
        { label: 'Fichas de degustação', icon: 'pi pi-folder-open', url: '/tasting-list' }
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