import { useState, useEffect } from 'react';
import { FilterMatchMode } from 'primereact/api';
import { InputText } from 'primereact/inputtext';
import { DataTable } from 'primereact/datatable';
import { Column } from 'primereact/column';
import useListPersonComponentHook from '../../../hooks/registration/useListPersonComponentHook';

const ListPersonComponent = () => {
    const { persons } = useListPersonComponentHook();

    const [filters, setFilters] = useState({
        global: { value: null, matchMode: FilterMatchMode.CONTAINS },
        name: { value: null, matchMode: FilterMatchMode.CONTAINS },
        document: { value: null, matchMode: FilterMatchMode.CONTAINS },
        birthDate: { value: null, matchMode: FilterMatchMode.CONTAINS }
    });

    const [loading, setLoading] = useState(true);

    const [globalFilterValue, setGlobalFilterValue] = useState('');

    const [expandedRows, setExpandedRows] = useState(null);

    const allowExpansion = (persons) => {
        return persons.address ? true : false;
    };

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
        <div className='card'>
            <h5><strong>Pessoas</strong></h5>
            <DataTable
                value={persons}
                expandedRows={expandedRows}
                onRowToggle={(e) => setExpandedRows(e.data)}
                rowExpansionTemplate={rowExpansionTemplate}
                paginator
                rows={10}
                rowsPerPageOptions={[10, 20, 30, 50]}
                loading={loading}
                filters={filters}
                globalFilterFields={['name', 'document', 'birthDate']}
                header={header}
                tableStyle={{ width: '50rem' }}
                emptyMessage="Nenhum registro encontrado"
            >
                <Column expander={allowExpansion} style={{ width: '5rem' }} />
                <Column
                    field='name'
                    header='Nome'
                    sortable
                    filterField='name'
                />
                <Column
                    field='document'
                    header='Documento'
                    sortable
                    filterField='document'
                />
                <Column
                    field='birthDate'
                    header='Data de Nascimento'
                    sortable
                    filterField='birthDate'
                />
            </DataTable>
        </div>
    )
}

export default ListPersonComponent;