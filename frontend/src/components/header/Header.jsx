import { TabMenu } from 'primereact/tabmenu';

const Header = () => {

  const items = [
    { label: 'Home', icon: 'pi pi-home', url: '/' },
    { label: 'Login', icon: 'pi pi-user', url: '/login' },
    { label: 'Cadastro', icon: 'pi pi-user-plus', url: '/registration' },
    { label: 'Vinho', icon: 'pi pi-folder-open', url: '/wine' },
    { label: 'Avaliação de vinho', icon: 'pi pi-folder-open', url: '/wine-review' },
    { label: 'Degustação', icon: 'pi pi-folder-open', url: '/tasting' },
    { label: 'Ficha de degustação', icon: 'pi pi-folder-open', url: '/tasting-card' },
    { label: 'Consultas', icon: 'pi pi-folder-open', url: '/search-wine' },
    { label: 'Inspeção visual', icon: 'pi pi-folder-open', url: '/visual-inspection' },
    { label: 'Inspeção olfativa', icon: 'pi pi-folder-open', url: '/olfactory-inspection' },
    { label: 'Inspeção gustativa', icon: 'pi pi-folder-open', url: '/gustatory-inspection' }
  ];

  return (
    <div >
      <TabMenu model={items} />
    </div>
  )
}

export default Header