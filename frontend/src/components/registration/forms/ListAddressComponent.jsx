import { useState, useEffect } from 'react';
import { FilterMatchMode } from 'primereact/api';
import { InputText } from 'primereact/inputtext';
import { DataTable } from 'primereact/datatable';
import { Column } from 'primereact/column';
import useListAddressComponentHook from "../../../hooks/registration/useListAddressComponentHook";

const ListAddressComponent = () => {
  const { address } = useListAddressComponentHook();
  const [filters, setFilters] = useState({
    global: { value: null, matchMode: FilterMatchMode.CONTAINS },
    addressDescription: { value: null, matchMode: FilterMatchMode.CONTAINS },
    addressNumber: { value: null, matchMode: FilterMatchMode.CONTAINS },
    complement: { value: null, matchMode: FilterMatchMode.CONTAINS },
    district: { value: null, matchMode: FilterMatchMode.CONTAINS },
    zipCode: { value: null, matchMode: FilterMatchMode.CONTAINS },
    city: { value: null, matchMode: FilterMatchMode.CONTAINS },
    'uf.stateName': { value: null, matchMode: FilterMatchMode.CONTAINS },
    'country.countryName': { value: null, matchMode: FilterMatchMode.CONTAINS },
    phoneNumber: { value: null, matchMode: FilterMatchMode.CONTAINS }
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
      <h5><strong>Endereços</strong></h5>
      <DataTable
        value={address}
        paginator
        rows={10}
        rowsPerPageOptions={[10, 20, 30, 50]}
        loading={loading}
        filters={filters}
        globalFilterFields={['addressDescription', 'addressNumber', 'complement', 'district', 'zipCode', 'city', 'uf.stateName', 'country.countryName', 'phoneNumber']}
        header={header}
        tableStyle={{ minWidth: '50rem' }}
        emptyMessage="Nenhum endereço encontrado."
      >
        <Column
          field='addressDescription'
          header='Descrição'
          sortable
          filterField='addressDescription'
        ></Column>
        <Column
          field='addressNumber'
          header='Número'
          sortable
          filterField='addressNumber'
        ></Column>
        <Column
          field='complement'
          header='Complemento'
          sortable
          filterField='complement'
        ></Column>
        <Column
          field='district'
          header='Bairro'
          sortable
          filterField='district'
        ></Column>
        <Column
          field='zipCode'
          header='CEP'
          sortable
          filterField='zipCode'
        ></Column>
        <Column
          field='city'
          header='Cidade'
          sortable
          filterField='city'
        ></Column>
        <Column
          field='uf.stateName'
          header='Estado'
          sortable
          filterField='uf.stateName'
        ></Column>
        <Column
          field='country.countryName'
          header='País'
          sortable
          filterField='country.countryName'
        ></Column>
      </DataTable>
    </div>
  )
}

export default ListAddressComponent