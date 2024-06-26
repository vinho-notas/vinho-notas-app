import { useState, useEffect, useRef } from 'react';
import { FilterMatchMode } from 'primereact/api';
import { InputText } from 'primereact/inputtext';
import { DataTable } from 'primereact/datatable';
import { Column } from 'primereact/column';
import { Card } from 'primereact/card';
import { Toolbar } from 'primereact/toolbar';
import { Button } from 'primereact/button';
import { Dialog } from 'primereact/dialog';
import { Toast } from 'primereact/toast';
import { InputMask } from "primereact/inputmask";
import { ProgressSpinner } from 'primereact/progressspinner';
import { updateAddress, deleteAddress, deleteAllAddress } from '../../../service/registration/AddressService';
import useListAddressComponentHook from "../../../hooks/registration/useListAddressComponentHook";

const ListAddressComponent = () => {
  const { address, navigate, fetchAddress, loading, setLoading } = useListAddressComponentHook();
  const [selectedAddress, setSelectedAddress] = useState(null);
  const [visibleEditDialog, setVisibleEditDialog] = useState(false);
  const [visibleDeleteDialog, setVisibleDeleteDialog] = useState(false);
  const [editingAddress, setEditingAddress] = useState(null);
  const dt = useRef(null);
  const editToast = useRef(null);
  const deleteToast = useRef(null);

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

  // const [loading, setLoading] = useState(true);
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
      alert('Selecione um endereço para editar.');
    }
  };

  const saveEditedAddress = async () => {
    try {
      const { uf: { stateName: state }, country: { countryName: country }, ...addressData } = editingAddress;
      const simplifiedAddress = {
        ...addressData,
        state,
        country,
      };

      await updateAddress(editingAddress.id, simplifiedAddress);
      editToast.current.show({ severity: 'success', summary: 'Sucesso', detail: 'Endereço atualizado com sucesso.', life: 3000 });
      setVisibleEditDialog(false);
      setSelectedAddress(null);
      await fetchAddress();
      navigate("/address");
    } catch (error) {
      editToast.current.show({ severity: 'error', summary: 'Error', detail: error.response.data.message, life: 3000 });
    }
  };

  const onDeleteClick = async () => {
    if (selectedAddress && selectedAddress.length > 0) {
      setVisibleDeleteDialog(true);
    } else {
      alert('Selecione um endereço para excluir.');
    }
  };

  const confirmDeleteAddress = async () => {
    try {
      if (selectedAddress.length === 1) {
        await deleteAddress(selectedAddress[0].id);
      } else if (selectedAddress.length > 1) {
        const addressIds = selectedAddress.map(address => address.id);
        await deleteAllAddress(addressIds);
      }

      setVisibleDeleteDialog(false);
      setSelectedAddress(null);
      await fetchAddress();
      deleteToast.current.show({ severity: 'success', summary: 'Sucesso', detail: 'Endereço(s) excluído(s) com sucesso.', life: 3000 });
    } catch (error) {
      deleteToast.current.show({ severity: 'error', summary: 'Error', detail: error.response.data.message, life: 3000 });
    }
  };

  const leftToolbarTemplate = () => {
    return (
      <>
        <Dialog header="Editar Endereço" visible={visibleEditDialog} style={{ width: '50vw' }} modal onHide={() => setVisibleEditDialog(false)}>
          <div className="p-fluid">
            <label htmlFor="addressDescription" className="p-col-12 p-md-2">Descrição</label>
            <div className="p-col-12 p-md-10">
              <InputText id="addressDescription" value={editingAddress?.addressDescription || ''} onChange={(e) => setEditingAddress({ ...editingAddress, addressDescription: e.target.value })} />
            </div>
          </div>
          <div className="p-fluid">
            <div className="p-field p-grid">
              <label htmlFor="addressNumber" className="p-col-12 p-md-2">Número</label>
              <InputText id="addressNumber" value={editingAddress?.addressNumber || ''} onChange={(e) => setEditingAddress({ ...editingAddress, addressNumber: e.target.value })} />
            </div>
          </div>
          <div className="p-fluid">
            <div className="p-field p-grid">
              <label htmlFor="complement" className="p-col-12 p-md-2">Complemento</label>
              <InputText id="complement" value={editingAddress?.complement || ''} onChange={(e) => setEditingAddress({ ...editingAddress, complement: e.target.value })} />
            </div>
          </div>
          <div className="p-fluid">
            <div className="p-field p-grid">
              <label htmlFor="district" className="p-col-12 p-md-2">Bairro</label>
              <InputText id="district" value={editingAddress?.district || ''} onChange={(e) => setEditingAddress({ ...editingAddress, district: e.target.value })} />
            </div>
          </div>
          <div className="p-fluid">
            <div className="p-field p-grid">
              <label htmlFor="zipCode" className="p-col-12 p-md-2">CEP</label>
              <InputMask id="zipCode" value={editingAddress?.zipCode || ''} onChange={(e) => setEditingAddress({ ...editingAddress, zipCode: e.target.value })} mask="99999-999" />
            </div>
          </div>
          <div className="p-fluid">
            <div className="p-field p-grid">
              <label htmlFor="city" className="p-col-12 p-md-2">Cidade</label>
              <InputText id="city" value={editingAddress?.city || ''} onChange={(e) => setEditingAddress({ ...editingAddress, city: e.target.value })} />
            </div>
          </div>
          <div className="p-fluid">
            <div className="p-field p-grid">
              <label htmlFor="uf.stateName" className="p-col-12 p-md-2">Estado</label>
              <InputText id="uf.stateName" value={editingAddress?.uf?.stateName || ''} onChange={(e) => setEditingAddress({ ...editingAddress, uf: { stateName: e.target.value } })} />
            </div>
          </div>
          <div className="p-fluid">
            <div className="p-field p-grid">
              <label htmlFor="country.countryName" className="p-col-12 p-md-2">País</label>
              <InputText id="country.countryName" value={editingAddress?.country?.countryName || ''} onChange={(e) => setEditingAddress({ ...editingAddress, country: { countryName: e.target.value } })} />
            </div>
          </div>
          <div className="p-fluid">
            <div className="p-field p-grid">
              <label htmlFor="phoneNumber" className="p-col-12 p-md-2">Telefone</label>
              <InputText id="phoneNumber" value={editingAddress?.phoneNumber || ''} onChange={(e) => setEditingAddress({ ...editingAddress, phoneNumber: e.target.value })} />
            </div>
          </div>
          <div className="flex flex-wrap gap-2 mt-4">
            <Button label="Cancelar" icon="pi pi-times" onClick={() => setVisibleEditDialog(false)} className="p-button-danger" style={{ borderRadius: '20px' }} />
            <Button label="Salvar" icon="pi pi-check" className="p-button-success" onClick={saveEditedAddress} style={{ borderRadius: '20px' }} />
          </div>
        </Dialog>

        <Dialog header="Excluir Endereço" visible={visibleDeleteDialog} style={{ width: '50vw' }} modal onHide={() => setVisibleDeleteDialog(false)}>
          <div className="p-fluid">
            <p>Você deseja excluir o(s) endereço(s) selecionado(s)?</p>
          </div>
          <div className="flex flex-wrap gap-2 mt-4">
            <Button label="Cancelar" icon="pi pi-times" onClick={() => setVisibleDeleteDialog(false)} className="p-button-danger" style={{ borderRadius: '20px' }} />
            <Button label="Confirmar" icon="pi pi-check" className="p-button-success" onClick={confirmDeleteAddress} style={{ borderRadius: '20px' }} />
          </div>
        </Dialog>

        <div className="flex flex-wrap gap-2">
          <Button rounded label="Editar" icon="pi pi-pencil" severity="secondary" onClick={onEditClick} disabled={!selectedAddress || selectedAddress.length !== 1} raised style={{ borderRadius: '20px' }} />
          <Button rounded label="Excluir" icon="pi pi-trash" severity="danger" onClick={onDeleteClick} disabled={!selectedAddress || selectedAddress.length === 0} raised style={{ borderRadius: '20px' }} />
        </div>
      </>

    );
  };

  const rightToolbarTemplate = () => {
    return <Button rounded label="CSV" icon="pi pi-upload" className="p-button-help" onClick={exportCSV} raised style={{ borderRadius: '20px' }} />;
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
    if (!loading) {
      setVisibleColumns(columns);
    }
  }, [loading]);

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
    <Card style={{ marginTop: 10 }} title="Lista de endereços" data-testid="principal-card">
      <Toolbar className="mb-4" start={leftToolbarTemplate} end={rightToolbarTemplate}></Toolbar>
      {loading ? (
        <div className="text-center">
          <ProgressSpinner style={{ width: '50px', height: '50px' }} strokeWidth="8" animationDuration=".5s" />
        </div>
      ) : (
        <DataTable
          value={address}
          paginator
          rows={10}
          rowsPerPageOptions={[10, 20, 30, 50]}
          loading={loading}
          filters={filters}
          resizableColumns
          columnResizeMode="expand"
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
          data-testid="address-table"
        >
          <Column selectionMode="multiple" headerStyle={{ width: '3rem' }} />
          {visibleColumns.map((col) => (
            <Column key={col.field} field={col.field} header={col.header} sortable filterField={col.field} />
          ))}
        </DataTable>
      )}
      <Toast ref={editToast} />
      <Toast ref={deleteToast} />
    </Card>
  )
  
}

export default ListAddressComponent