import { useState, useEffect, useRef } from 'react';
import { FilterMatchMode } from 'primereact/api';
import { InputText } from 'primereact/inputtext';
import { DataTable } from 'primereact/datatable';
import { Column } from 'primereact/column';
import { Card } from 'primereact/card';
import { Toolbar } from 'primereact/toolbar';
import { Button } from 'primereact/button';
import { Dialog } from 'primereact/dialog';
import useListPersonComponentHook from '../../../hooks/registration/useListPersonComponentHook';
import { updatePerson, deletePerson, deleteAllPerson } from '../../../service/registration/PersonService';

const ListPersonComponent = () => {
    const { persons, navigate, fetchPersons } = useListPersonComponentHook();
    const [selectedPerson, setSelectedPerson] = useState(null);
    const [editingPerson, setEditingPerson] = useState(null);
    const [visibleEditDialog, setVisibleEditDialog] = useState(false);
    const [visibleDeleteDialog, setVisibleDeleteDialog] = useState(false);
    const dt = useRef(null);

    const [filters, setFilters] = useState({
        global: { value: null, matchMode: FilterMatchMode.CONTAINS },
        name: { value: null, matchMode: FilterMatchMode.CONTAINS },
        document: { value: null, matchMode: FilterMatchMode.CONTAINS },
        birthDate: { value: null, matchMode: FilterMatchMode.CONTAINS }
    });

    const columns = [
        { field: 'name', header: 'Nome', sortable: true, filterField: 'name' },
        { field: 'document', header: 'Documento', sortable: true, filterField: 'document' },
        { field: 'birthDate', header: 'Data de Nascimento', sortable: true, filterField: 'birthDate' }
    ];

    const [visibleColumns, setVisibleColumns] = useState([]);

    const exportCSV = () => {
        dt.current.exportCSV();
    };

    const [loading, setLoading] = useState(true);
    const [globalFilterValue, setGlobalFilterValue] = useState('');
    const [expandedRows, setExpandedRows] = useState(null);

    const allowExpansion = (persons) => {
        return persons.address ? true : false;
    };

    const onSelectionChange = (e) => {
        setSelectedPerson(e.value);
    };

    const onSelectAllChange = (e) => {
        const _selectedPerson = e.checked ? persons.map(scale => scale) : null;
        if (_selectedPerson) {
            setSelectedPerson(_selectedPerson);
        } else {
            setSelectedPerson(null);
        }
    };

    const onNewClick = () => {
        navigate('/registration');
    };

    const onEditClick = () => {
        if (selectedPerson && selectedPerson.length === 1) {
            setEditingPerson(selectedPerson[0]);
            setVisibleEditDialog(true);
        } else {
            alert('Selecione uma pessoa para editar.');
        }
    };

    const saveEditedPerson = async () => {
        try {
            const { id, name, document, birthDate } = editingPerson;
            const simplifiedPerson = {
                id,
                name,
                document,
                birthDate,
            };

            await updatePerson(id, simplifiedPerson);
            setVisibleEditDialog(false);
            await fetchPersons();
            navigate("/persons");
        } catch (error) {
            console.log(error);
        }
    };


    const onDeleteClick = () => {
        if (selectedPerson && selectedPerson.length > 0) {
            setVisibleDeleteDialog(true);
        } else {
            alert('Selecione uma pessoa para excluir.');
        }
    };

    const confirmDeletePerson = async () => {
        try {
            if (selectedPerson.length === 1) {
                await deletePerson(selectedPerson[0].id);
            } else if (selectedPerson.length > 1) {
                const personsIds = selectedPerson.map(person => person.id);
                await deleteAllPerson(personsIds);
            }

            setVisibleDeleteDialog(false);
            setSelectedPerson(null);
            await fetchPersons();
        } catch (error) {
            console.log(error);
        }
    };


    const rightToolbarTemplate = () => {
        return <Button rounded label="CSV" icon="pi pi-upload" className="p-button-help" onClick={exportCSV} raised style={{ borderRadius: '20px' }}/>;
    }

    const leftToolbarTemplate = () => {
        return (
            <>
                <Dialog header="Editar Pessoa" visible={visibleEditDialog} style={{ width: '50vw' }} modal onHide={() => setVisibleEditDialog(false)}>
                    <div className="p-fluid">
                        <label htmlFor="name" className="p-col-12 p-md-2">Nome</label>
                        <div className="p-col-12 p-md-10">
                            <InputText id="name" value={editingPerson?.name} onChange={(e) => setEditingPerson({ ...editingPerson, name: e.target.value })} />
                        </div>
                    </div>
                    <div className="p-fluid">
                        <label htmlFor="document" className="p-col-12 p-md-2">Documento</label>
                        <div className="p-col-12 p-md-10">
                            <InputText id="document" value={editingPerson?.document} onChange={(e) => setEditingPerson({ ...editingPerson, document: e.target.value })} />
                        </div>
                    </div>
                    <div className="p-fluid">
                        <label htmlFor="birthDate" className="p-col-12 p-md-2">Data de Nascimento</label>
                        <div className="p-col-12 p-md-10">
                            <InputText id="birthDate" value={editingPerson?.birthDate} onChange={(e) => setEditingPerson({ ...editingPerson, birthDate: e.target.value })} />
                        </div>
                    </div>
                    <div className="flex flex-wrap gap-2 mt-4">
                        <Button label="Cancelar" icon="pi pi-times" onClick={() => setVisibleEditDialog(false)} className="p-button-danger" style={{ borderRadius: '20px' }}/>
                        <Button label="Salvar" icon="pi pi-check" className="p-button-success" onClick={saveEditedPerson} style={{ borderRadius: '20px' }}/>
                    </div>
                </Dialog>
                <Dialog header="Excluir Pessoa" visible={visibleDeleteDialog} style={{ width: '50vw' }} modal onHide={() => setVisibleDeleteDialog(false)}>
                    <div className="p-fluid">
                        <h5>Você deseja excluir a(s) pessoa(s) selecionada(s)?</h5>
                    </div>
                    <div className="flex flex-wrap gap-2 mt-4">
                        <Button label="Cancelar" icon="pi pi-times" onClick={() => setVisibleDeleteDialog(false)} className="p-button-danger" style={{ borderRadius: '20px' }}/>
                        <Button label="Confirmar" icon="pi pi-check" className="p-button-success" onClick={confirmDeletePerson} style={{ borderRadius: '20px' }}/>
                    </div>
                </Dialog>
                <div className="flex flex-wrap gap-2">
                    <Button rounded label="Novo" icon="pi pi-plus" severity="success" onClick={onNewClick} raised style={{ borderRadius: '20px' }}/>
                    <Button rounded label="Editar" icon="pi pi-pencil" severity="secondary" onClick={onEditClick} disabled={!selectedPerson || selectedPerson.length !== 1} raised style={{ borderRadius: '20px' }}/>
                    <Button rounded label="Excluir" icon="pi pi-trash" severity="danger" onClick={onDeleteClick} disabled={!selectedPerson || selectedPerson.length === 0} raised style={{ borderRadius: '20px' }}/>
                </div>
            </>

        );
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

    const rowExpansionTemplate = (persons) => {
        return (
            <>
                <div className="card">
                    <h5><strong>Endereço de {persons.name} </strong></h5>
                    <DataTable value={[persons.address]}>
                        <Column field="addressDescription" header="Logradouro" sortable></Column>
                        <Column field="addressNumber" header="Número" sortable></Column>
                        <Column field="complement" header="Complemento" sortable></Column>
                        <Column field="district" header="Bairro" sortable></Column>
                        <Column field="zipCode" header="CEP" sortable></Column>
                        <Column field="city" header="Cidade" sortable></Column>
                        <Column field="uf.stateName" header="Estado" sortable></Column>
                        <Column field="country.countryName" header="País" sortable></Column>
                        <Column field="phoneNumber" header="Telefone" sortable></Column>
                    </DataTable>
                </div>
            </>
        );
    };

    return (
        <Card data-testid="principal-card" style={{ marginTop: 10 }} title="Lista de pessoas">
            <Toolbar className="mb-4" start={leftToolbarTemplate} end={rightToolbarTemplate}></Toolbar>
            <DataTable
                value={persons}
                expandedRows={expandedRows}
                onRowToggle={(e) => setExpandedRows(e.data)}
                rowExpansionTemplate={rowExpansionTemplate}
                resizableColumns
                columnResizeMode="expand"
                paginator
                rows={10}
                rowsPerPageOptions={[10, 20, 30, 50]}
                loading={loading}
                filters={filters}
                globalFilterFields={['name', 'document', 'birthDate']}
                header={header}
                showGridlines
                selectionMode="multiple"
                selection={selectedPerson}
                onSelectionChange={onSelectionChange}
                onSelectAll={onSelectAllChange}
                tableStyle={{ width: '50rem' }}
                emptyMessage="Nenhum registro encontrado"
                data-testid="person-table"
                ref={dt}
            >
                <Column expander={allowExpansion} style={{ width: '5rem' }} />
                <Column selectionMode="multiple" headerStyle={{ width: '3rem' }} />
                {visibleColumns.map((col) => (
                    <Column key={col.field} field={col.field} header={col.header} sortable filterField={col.field} />
                ))}
            </DataTable>
        </Card>
    )
}

export default ListPersonComponent;