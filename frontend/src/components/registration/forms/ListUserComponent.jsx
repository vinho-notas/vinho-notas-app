import { useState, useEffect, useRef } from 'react';
import { FilterMatchMode } from 'primereact/api';
import { InputText } from 'primereact/inputtext';
import { DataTable } from 'primereact/datatable';
import { Column } from 'primereact/column';
import { Card } from 'primereact/card';
import { Toolbar } from 'primereact/toolbar';
import { Button } from 'primereact/button';
import { Dialog } from 'primereact/dialog';
import useListUserComponentHook from '../../../hooks/registration/useListUserComponentHook';
// import EnumProfile from '../../../utils/enums/EnumProfile';
import { updateUser } from '../../../service/registration/UserService';

const ListUserComponent = () => {
    const { users } = useListUserComponentHook();
    const dt = useRef(null);
    const [selectedUser, setSelectedUser] = useState(null);
    const [filters, setFilters] = useState({
        global: { value: null, matchMode: FilterMatchMode.CONTAINS },
        'person.name': { value: null, matchMode: FilterMatchMode.CONTAINS },
        enumProfile: { value: null, matchMode: FilterMatchMode.CONTAINS },
        email: { value: null, matchMode: FilterMatchMode.CONTAINS }
    });

    const columns = [
        { field: 'person.name', header: 'Nome' },
        { field: 'enumProfile', header: 'Perfil' },
        { field: 'email', header: 'Email' }
    ];

    const [loading, setLoading] = useState(true);
    const [globalFilterValue, setGlobalFilterValue] = useState('');
    const [visibleColumns, setVisibleColumns] = useState([]);

    const onSelectionChange = (e) => {
        setSelectedUser(e.value);
    };

    const onSelectAllChange = (e) => {
        const _selectedUser = e.checked ? users.map(scale => scale) : null;
        if (_selectedUser) {
            setSelectedUser(_selectedUser);
        } else {
            setSelectedUser(null);
        }
    };

    const exportCSV = () => {
        dt.current.exportCSV();
    };

    const leftToolbarTemplate = () => {
        return (
            <>
                <div className="flex flex-wrap gap-2">
                    <Button rounded label="Editar" icon="pi pi-pencil" severity="secondary" onClick={'onEditClick'} disabled={!selectedUser || selectedUser.length !== 1} raised />
                </div>
            </>

        )

    };

    const rightToolbarTemplate = () => {
        return <Button rounded label="CSV" icon="pi pi-upload" className="p-button-help" onClick={exportCSV} raised />;
    };

    useEffect(() => {
        setLoading(false);
        setVisibleColumns(columns);
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
        <Card style={{ marginTop: 10 }} title="Lista de usuários">
            <Toolbar className="mb-4" start={leftToolbarTemplate} end={rightToolbarTemplate}></Toolbar>
            <DataTable
                value={users}
                resizableColumns
                columnResizeMode="expand"
                paginator
                rows={10}
                rowsPerPageOptions={[10, 20, 30, 50]}
                loading={loading}
                filters={filters}
                globalFilterFields={['person.name', 'enumProfile', 'email']}
                header={header}
                showGridlines
                tableStyle={{ width: '50rem' }}
                selectionMode="multiple"
                selection={selectedUser}
                onSelectionChange={onSelectionChange}
                onSelectAll={onSelectAllChange}
                emptyMessage="Nenhum registro encontrado"
                ref={dt}
            >
                <Column selectionMode="multiple" headerStyle={{ width: '3rem' }} />
                {visibleColumns.map((col) => (
                    <Column key={col.field} field={col.field} header={col.header} sortable filterField={col.field} />
                ))}
            </DataTable>
        </Card>
    )
}

export default ListUserComponent
{/* {Object.values(EnumProfile).map((type, index) => (
  <option key={index} value={type}>{type}</option>
))} */}