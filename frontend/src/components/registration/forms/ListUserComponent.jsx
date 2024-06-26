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
import { Toast } from 'primereact/toast';
import useListUserComponentHook from '../../../hooks/registration/useListUserComponentHook';
import EnumProfile from '../../../utils/enums/EnumProfile';
import { updateUser, deleteUser, deleteAllUser } from '../../../service/registration/UserService';

const ListUserComponent = () => {
    const { users, navigate, fetchUsers } = useListUserComponentHook();
    const dt = useRef(null);
    const [selectedUser, setSelectedUser] = useState(null);
    const [visibleEditDialog, setVisibleEditDialog] = useState(false);
    const [editingUser, setEditingUser] = useState(null);
    const [visibleDeleteDialog, setVisibleDeleteDialog] = useState(false);
    const profile = Object.values(EnumProfile);
    const editToast = useRef(null);
    const deleteToast = useRef(null);

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
            const userToEdit = selectedUser[0];
            setEditingUser(userToEdit);
            setVisibleEditDialog(true);
        } else {
            alert('Selecione um usuário para editar.');
        }
    };

    const saveEditedUser = async () => {
        try {
            const { id, person, enumProfile, email } = editingUser;
            const simplifiedUser = {
                id,
                personName: person.name,
                enumProfile,
                email,
            };

            await updateUser(id, simplifiedUser);
            editToast.current.show({ severity: 'success', summary: 'Sucesso', detail: 'Usuário(a) atualizado(a) com sucesso.', life: 3000 });
            setVisibleEditDialog(false);
            setSelectedUser(null);
            await fetchUsers();
            navigate("/users");
        } catch (error) {
            editToast.current.show({ severity: 'error', summary: 'Error', detail: error.response.data.message, life: 3000 });
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
            if (selectedUser.length === 1) {
                await deleteUser(selectedUser[0].id);
            } else if (selectedUser.length > 1) {
                const usersIds = selectedUser.map(user => user.id);
                await deleteAllUser(usersIds);
            }

            setVisibleDeleteDialog(false);
            setSelectedUser(null);
            await fetchUsers();
            deleteToast.current.show({ severity: 'success', summary: 'Sucesso', detail: 'Usuário(a)(s) excluído(a)(s) com sucesso.', life: 3000 });
        } catch (error) {
            deleteToast.current.show({ severity: 'error', summary: 'Error', detail: error.response.data.message, life: 3000 });
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
                        <Dropdown
                            id="pointScale"
                            value={editingUser?.enumProfile || ''}
                            options={profile}
                            onChange={(e) => setEditingUser({ ...editingUser, enumProfile: e.value })}
                            placeholder="Selecione um perfil"
                        />
                    </div>
                    <div className="flex justify-content-end gap-2">
                        <Button label="Cancelar" icon="pi pi-times" onClick={() => setVisibleEditDialog(false)} className="p-button-danger" style={{ borderRadius: '20px' }} />
                        <Button label="Salvar" icon="pi pi-check" className="p-button-success" onClick={saveEditedUser} style={{ borderRadius: '20px' }} />
                    </div>
                </Dialog>
                <Dialog header="Excluir Usuário" visible={visibleDeleteDialog} style={{ width: '50vw' }} modal onHide={() => setVisibleDeleteDialog(false)}>
                    <div className="p-fluid">
                        <h5>Você deseja excluir o(s) usuário(s) selecionado(s)?</h5>
                    </div>
                    <div className="flex flex-wrap gap-2 mt-4">
                        <Button label="Cancelar" icon="pi pi-times" onClick={() => setVisibleDeleteDialog(false)} className="p-button-danger" style={{ borderRadius: '20px' }} />
                        <Button label="Confirmar" icon="pi pi-check" className="p-button-success" onClick={confirmDeleteUser} style={{ borderRadius: '20px' }} />
                    </div>
                </Dialog>
                <div className="flex flex-wrap gap-2">
                    <Button rounded label="Novo" icon="pi pi-plus" severity="success" onClick={onNewClick} raised style={{ borderRadius: '20px' }} />
                    <Button data-testid="update-button" rounded label="Editar" icon="pi pi-pencil" severity="secondary" onClick={onEditClick} disabled={!selectedUser || selectedUser.length !== 1}
                        raised style={{ borderRadius: '20px' }} />
                    <Button data-testid="delete-button" rounded label="Excluir" icon="pi pi-trash" severity="danger" onClick={onDeleteClick} disabled={!selectedUser || selectedUser.length === 0} raised style={{ borderRadius: '20px' }} />
                </div>
            </>
        )
    };

    const rightToolbarTemplate = () => {
        return <Button rounded label="CSV" icon="pi pi-upload" className="p-button-help" onClick={exportCSV} raised style={{ borderRadius: '20px' }} />;
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
            <Toast ref={editToast} />
            <Toast ref={deleteToast} />
        </Card>
    )
}

export default ListUserComponent;