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
import { createPerson, updatePerson, deletePerson } from '../../../service/registration/PersonService';

const ListPersonComponent = () => {
    const { persons, navigate, fetchPersons } = useListPersonComponentHook();
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

    const onNewClick = () => {
        navigate('/registration');
    };

    const rightToolbarTemplate = () => {
        return <Button rounded label="CSV" icon="pi pi-upload" className="p-button-help" onClick={exportCSV} raised />;
      }

      const leftToolbarTemplate = () => {
        return (
            <>
            <div className="flex flex-wrap gap-2">
            <Button rounded label="Novo" icon="pi pi-plus" severity="success" onClick={onNewClick} raised />
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
        <Card style={{ marginTop: 10 }} title="Lista de pessoas">  
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
        //         selection={selectedAddress}
        // onSelectionChange={onSelectionChange}
        // onSelectAll={onSelectAllChange}

                tableStyle={{ width: '50rem' }}
                emptyMessage="Nenhum registro encontrado"
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