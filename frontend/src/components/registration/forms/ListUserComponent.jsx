import { useState, useEffect } from 'react';
import { FilterMatchMode } from 'primereact/api';
import { InputText } from 'primereact/inputtext';
import { DataTable } from 'primereact/datatable';
import { Column } from 'primereact/column';
import useListUserComponentHook from '../../../hooks/registration/useListUserComponentHook';
import EnumProfile from '../../../utils/enums/EnumProfile';

const ListUserComponent = () => {
    const { users } = useListUserComponentHook();
    const profile = Object.values(EnumProfile);    

    const [filters, setFilters] = useState({
        global: { value: null, matchMode: FilterMatchMode.CONTAINS },
        'person.name': { value: null, matchMode: FilterMatchMode.CONTAINS },
        enumProfile: { value: null, matchMode: FilterMatchMode.CONTAINS },
        email: { value: null, matchMode: FilterMatchMode.CONTAINS }
    });

    const [loading, setLoading] = useState(true);

    const [globalFilterValue, setGlobalFilterValue] = useState('');

    useEffect(() => {
        setLoading(false);
    }, []);

    const onGlobalFilterChange = (e) => {
        const value = e.target.value;
        let _filters = { ...filters };
        _filters['global'].value = value;
        setFilters(_filters);
        setGlobalFilterValue(value);
    };

    const renderHeader = () => {
        return (
            <div className="flex justify-content-end">
                <span className="p-input-icon-left">
                    <i className="pi pi-search" />
                    <InputText value={globalFilterValue} onChange={onGlobalFilterChange} placeholder="Keyword Search" />
                </span>
            </div>
        );
    };

    const header = renderHeader();

  return (
    <div className='card'>
        <h5><strong>Usuários</strong></h5>
        <DataTable
                value={users}
                paginator
                rows={10}
                rowsPerPageOptions={[10, 20, 30, 50]}
                loading={loading}
                filters={filters}
                globalFilterFields={['person.name', 'enumProfile', 'email']}
                header={header}
                tableStyle={{ width: '50rem' }}
                emptyMessage="Nenhum registro encontrado"
            >                
                <Column
                    field='person.name'
                    header='Nome'
                    sortable
                    filterField='person.name'
                />
                <Column
                    field='enumProfile'
                    header='Perfil'
                    sortable
                    filterField='enumProfile'
                />
                {Object.values(EnumProfile).map((type, index) => (
                  <option key={index} value={type}>{type}</option>
                ))}

                <Column
                    field='email'
                    header='Email'
                    sortable
                    filterField='email'
                />

            </DataTable>
    </div>
  )
}

export default ListUserComponent