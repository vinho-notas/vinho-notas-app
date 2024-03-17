import { useState, useEffect } from 'react';
import { FilterMatchMode } from 'primereact/api';
import { DataTable } from 'primereact/datatable';
import { Column } from 'primereact/column';
import { InputText } from 'primereact/inputtext';
import { Card } from 'primereact/card';
import useLIstStateComponentHook from '../../../hooks/registration/useLIstStateComponentHook';

const ListStateComponent = () => {
    const { state } = useLIstStateComponentHook();

    const [filters, setFilters] = useState({
        global: { value: null, matchMode: FilterMatchMode.CONTAINS },
        stateName: { value: null, matchMode: FilterMatchMode.CONTAINS },
        uf: { value: null, matchMode: FilterMatchMode.CONTAINS }
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
        <Card style={{ marginTop: 10 }} title="Lista de estados">
            <DataTable
                value={state}
                paginator
                rows={10}
                rowsPerPageOptions={[10, 20, 30, 50]}
                loading={loading}
                tableStyle={{ minWidth: '50rem' }}
                filters={filters}
                globalFilterFields={['stateName', 'uf']}
                header={header}
                resizableColumns
                showGridlines
                emptyMessage="Nenhum estado encontrado."
            >
                <Column field="stateName" header="Estado" sortable filterField='stateName'></Column>
                <Column field="uf" header="UF" sortable filterField='uf'></Column>
            </DataTable>
        </Card>
    )
}

export default ListStateComponent