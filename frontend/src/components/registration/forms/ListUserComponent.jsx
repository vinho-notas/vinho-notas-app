import { useState, useEffect, useRef } from 'react';
import { FilterMatchMode } from 'primereact/api';
import { InputText } from 'primereact/inputtext';
import { DataTable } from 'primereact/datatable';
import { Column } from 'primereact/column';
import { Card } from 'primereact/card';
import { Toolbar } from 'primereact/toolbar';
import { Button } from 'primereact/button';
import { Dialog } from 'primereact/dialog';
import { Dropdown } from 'primereact/dropdown';
import useListUserComponentHook from '../../../hooks/registration/useListUserComponentHook';
import EnumProfile from '../../../utils/enums/EnumProfile';
import { updateUser, deleteUser } from '../../../service/registration/UserService';

const ListUserComponent = () => {
    const { users, navigate, fetchUsers } = useListUserComponentHook();
    const dt = useRef(null);
    const [selectedUser, setSelectedUser] = useState(null);
    const [visibleEditDialog, setVisibleEditDialog] = useState(false);
    const [editingUser, setEditingUser] = useState(null);
    const [visibleDeleteDialog, setVisibleDeleteDialog] = useState(false);
    const profile = Object.values(EnumProfile);


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

    const onNewClick = () => {
        navigate('/user-registration');
    };

    const onEditClick = () => {
        if (selectedUser && selectedUser.length === 1) {
            setEditingUser(selectedUser[0]);
            setVisibleEditDialog(true);
        } else {
            alert('Selecione um usuário para editar.');
        }
    };

    const saveEditedUser = async () => {
        try {
            await updateUser(editingUser.id, editingUser);
            setVisibleEditDialog(false);
            await fetchUsers();
            navigate("/users")
        } catch (error) {
            console.log(error);
        }
    };

    const onDeleteClick = () => {
        if (selectedUser && selectedUser.length > 0) {
            setVisibleDeleteDialog(true);
        } else {
            alert('Selecione um usuário para excluir.');
        }
    };

    const confirmDeleteUser = async () => {
        try {
            const userIds = selectedUser.map(user => user.id);
            await deleteUser(userIds);
            setVisibleDeleteDialog(false);
            setSelectedUser(null);
            await fetchUsers();
        } catch (error) {
            console.log(error);
        }
    };

    const exportCSV = () => {
        dt.current.exportCSV();
    };

    const leftToolbarTemplate = () => {
        return (
            <>
                <Dialog header="Editar Usuário" visible={visibleEditDialog} style={{ width: '50vw' }} modal onHide={() => setVisibleEditDialog(false)}>
                    <div className='p-fluid'>
                        <label htmlFor="name" className="p-col-12 p-md-2">Nome</label>
                        <div className="p-col-12 p-md-10">
                            <InputText id="name" value={editingUser?.person.name} onChange={(e) => setEditingUser({ ...editingUser, name: e.target.value })} />
                        </div>
                    </div>
                    <div className='p-fluid'>
                        <label htmlFor="email" className="p-col-12 p-md-2">Email</label>
                        <div className="p-col-12 p-md-10">
                            <InputText id="email" value={editingUser?.email} onChange={(e) => setEditingUser({ ...editingUser, email: e.target.value })} />
                        </div>
                    </div>
                    <div className='p-fluid'>
                        <label htmlFor="enumProfile" className="p-col-12 p-md-2">Perfil</label>
                        <Dropdown id="pointScale" value={editingUser?.profile || ''} options={profile} onChange={(e) => setEditingUser({ ...editingUser, enumProfile: e.target.value })} placeholder="Selecione um perfil" />
                    </div>
                    <div className="flex justify-content-end gap-2">
                        <Button label="Cancelar" icon="pi pi-times" onClick={() => setVisibleEditDialog(false)} className="p-button-danger" />
                        <Button label="Salvar" icon="pi pi-check" className="p-button-success" onClick={saveEditedUser} />
                    </div>
                </Dialog>
                <Dialog header="Excluir Usuário" visible={visibleDeleteDialog} style={{ width: '50vw' }} modal onHide={() => setVisibleDeleteDialog(false)}>
                    <div className="p-fluid">
                        <h5>Você deseja excluir o(s) usuário(s) selecionado(s)?</h5>
                    </div>
                    <div className="flex flex-wrap gap-2 mt-4">
                        <Button label="Cancelar" icon="pi pi-times" onClick={() => setVisibleDeleteDialog(false)} className="p-button-danger" />
                        <Button label="Confirmar" icon="pi pi-check" className="p-button-success" onClick={confirmDeleteUser} />
                    </div>
                </Dialog>
                <div className="flex flex-wrap gap-2">
                <Button rounded label="Novo" icon="pi pi-plus" severity="success" onClick={onNewClick} raised />
                    <Button data-testid="update-button" rounded label="Editar" icon="pi pi-pencil" severity="secondary" onClick={onEditClick} disabled={!selectedUser || selectedUser.length !== 1}
                        raised />
                    <Button data-testid="delete-button" rounded label="Excluir" icon="pi pi-trash" severity="danger" onClick={onDeleteClick} disabled={!selectedUser || selectedUser.length === 0} raised />
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
                data-testid="user-list"
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

export default ListUserComponent;