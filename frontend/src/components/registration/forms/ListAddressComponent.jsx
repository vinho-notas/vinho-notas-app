import { useState, useEffect, useRef } from 'react';
import { FilterMatchMode } from 'primereact/api';
import { InputText } from 'primereact/inputtext';
import { DataTable } from 'primereact/datatable';
import { Column } from 'primereact/column';
import { Card } from 'primereact/card';
import { Toolbar } from 'primereact/toolbar';
import { Button } from 'primereact/button';
import { Dialog } from 'primereact/dialog';
import useListAddressComponentHook from "../../../hooks/registration/useListAddressComponentHook";

const ListAddressComponent = () => {
  const { address } = useListAddressComponentHook();
  const [selectedAddress, setSelectedAddress] = useState(null);
  const [visibleEditDialog, setVisibleEditDialog] = useState(false);
  const [visibleDeleteDialog, setVisibleDeleteDialog] = useState(false);
  const [editingAddress, setEditingAddress] = useState(null);
  const dt = useRef(null);
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

  const columns = [
    { field: 'addressDescription', header: 'Descrição', sortable: true, filterField: 'addressDescription' },
    { field: 'addressNumber', header: 'Número', sortable: true, filterField: 'addressNumber' },
    { field: 'complement', header: 'Complemento', sortable: true, filterField: 'complement' },
    { field: 'district', header: 'Bairro', sortable: true, filterField: 'district' },
    { field: 'zipCode', header: 'CEP', sortable: true, filterField: 'zipCode' },
    { field: 'city', header: 'Cidade', sortable: true, filterField: 'city' },
    { field: 'uf.stateName', header: 'Estado', sortable: true, filterField: 'uf.stateName' },
    { field: 'country.countryName', header: 'País', sortable: true, filterField: 'country.countryName' }
  ];

  const [visibleColumns, setVisibleColumns] = useState([]);

  const exportCSV = () => {
    dt.current.exportCSV();
  };

  const onEditClick = () => {
    if (selectedAddress && selectedAddress.length === 1) {
        setEditingAddress(selectedAddress[0]);
        setVisibleEditDialog(true);
    } else {
        alert('Selecione um vinho para editar.');
    }
};

const onDeleteClick = async () => {
  if (selectedAddress && selectedAddress.length > 0) {
      setVisibleDeleteDialog(true);
  } else {
      alert('Selecione um vinho para excluir.');
  }
};

  const leftToolbarTemplate = () => {
    return (
      <>
        <div className="flex flex-wrap gap-2">
          <Button rounded label="Editar" icon="pi pi-pencil" severity="secondary" onClick={onEditClick} disabled={!selectedAddress || selectedAddress.length !== 1} raised />
          <Button rounded label="Excluir" icon="pi pi-trash" severity="danger" onClick={onDeleteClick} disabled={!selectedAddress || selectedAddress.length === 0} raised />
        </div>
      </>

    );
  };

  const rightToolbarTemplate = () => {
    return <Button rounded label="CSV" icon="pi pi-upload" className="p-button-help" onClick={exportCSV} raised />;
  }

  const onSelectionChange = (e) => {
    setSelectedAddress(e.value);
  };

  const onSelectAllChange = (e) => {
    const _selectedAddress = e.checked ? address.map(scale => scale) : null;
    if (_selectedAddress) {
      setSelectedAddress(_selectedAddress);
    } else {
      setSelectedAddress(null);
    }
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
    <Card style={{ marginTop: 10 }} title="Lista de endereços">
      <Toolbar className="mb-4" start={leftToolbarTemplate} end={rightToolbarTemplate}></Toolbar>
      <DataTable
        value={address}
        paginator
        rows={10}
        rowsPerPageOptions={[10, 20, 30, 50]}
        loading={loading}
        filters={filters}
        globalFilterFields={['addressDescription', 'addressNumber', 'complement', 'district', 'zipCode', 'city', 'uf.stateName', 'country.countryName', 'phoneNumber']}
        header={header}
        showGridlines
        selectionMode="multiple"
        selection={selectedAddress}
        onSelectionChange={onSelectionChange}
        onSelectAll={onSelectAllChange}
        tableStyle={{ minWidth: '50rem' }}
        emptyMessage="Nenhum endereço encontrado."
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

export default ListAddressComponent