import { useState, useEffect } from 'react';
import { FilterMatchMode } from 'primereact/api';
import { InputText } from 'primereact/inputtext';
import { DataTable } from 'primereact/datatable';
import { Column } from 'primereact/column';
import { Card } from 'primereact/card';
import useListCountryComponentHook from '../../../hooks/registration/useListCountryComponentHook'

const ListCountryComponent = () => {
  const { country } = useListCountryComponentHook();
  const [filters, setFilters] = useState({
    global: { value: null, matchMode: FilterMatchMode.CONTAINS },
    countryName: { value: null, matchMode: FilterMatchMode.CONTAINS },
    continentName: { value: null, matchMode: FilterMatchMode.CONTAINS }
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
    <Card style={{ marginTop: 10 }} title="Lista de países" >
      <h5><strong>Países</strong></h5>
      <DataTable
        value={country}
        paginator
        rows={10}
        rowsPerPageOptions={[10, 20, 30, 50]}
        loading={loading}
        filters={filters}
        globalFilterFields={['countryName', 'continentName']}
        header={header}
        resizableColumns
        showGridlines
        tableStyle={{ minWidth: '50rem' }}
        emptyMessage="Nenhum país encontrado."
      >
        <Column field="countryName" header="País" sortable filterField='countryName'></Column>
        <Column field="continentName" header="Continente" sortable filterField='continentName'></Column>
      </DataTable>
    </Card>
  )
}

export default ListCountryComponent