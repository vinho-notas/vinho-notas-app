import { Card } from 'primereact/card';
import { useState, useEffect, useRef } from 'react';
import { FilterMatchMode } from 'primereact/api';
import { InputText } from 'primereact/inputtext';
import { DataTable } from 'primereact/datatable';
import { Column } from 'primereact/column';
import { MultiSelect } from 'primereact/multiselect';
import { Toolbar } from 'primereact/toolbar';
import { Button } from 'primereact/button';
import { Dialog } from 'primereact/dialog';
import useListWineComponentHook from '../../../hooks/wine/useListWineComponentHook';
import { updateWine, deleteWine } from '../../../service/wine/WineService';

const ListWineComponent = () => {
    const { wines, navigate, fetchWines } = useListWineComponentHook();
    const [selectedWines, setSelectedWines] = useState(null);
    const [editingWine, setEditingWine] = useState(null);
    const [visibleDialog, setVisibleDialog] = useState(false);
    const [visibleDeleteDialog, setVisibleDeleteDialog] = useState(false);
    const [visiblePointScaleDialog, setVisiblePointScaleDialog] = useState(false);

    const [filters, setFilters] = useState({
        global: { value: null, matchMode: FilterMatchMode.CONTAINS },
        name: { value: null, matchMode: FilterMatchMode.CONTAINS },
        price: { value: null, matchMode: FilterMatchMode.CONTAINS },
        purchaseLocation: { value: null, matchMode: FilterMatchMode.CONTAINS },
        purchaseDate: { value: null, matchMode: FilterMatchMode.CONTAINS },
        wineType: { value: null, matchMode: FilterMatchMode.CONTAINS },
        wineClassification: { value: null, matchMode: FilterMatchMode.CONTAINS },
        alcoholContent: { value: null, matchMode: FilterMatchMode.CONTAINS },
        volumeMl: { value: null, matchMode: FilterMatchMode.CONTAINS },
        grape: { value: null, matchMode: FilterMatchMode.CONTAINS },
        winery: { value: null, matchMode: FilterMatchMode.CONTAINS },
        serviceTemperature: { value: null, matchMode: FilterMatchMode.CONTAINS },
        harvest: { value: null, matchMode: FilterMatchMode.CONTAINS },
        country: { value: null, matchMode: FilterMatchMode.CONTAINS },
        guardTime: { value: null, matchMode: FilterMatchMode.CONTAINS },
        region: { value: null, matchMode: FilterMatchMode.CONTAINS },
        maturation: { value: null, matchMode: FilterMatchMode.CONTAINS },
        harmonization: { value: null, matchMode: FilterMatchMode.CONTAINS }
    });

    const [loading, setLoading] = useState(true);
    const [globalFilterValue, setGlobalFilterValue] = useState('');
    const dt = useRef(null);

    const onEditClick = () => {
        if (selectedWines && selectedWines.length === 1) {
            setEditingWine(selectedWines[0]);
            setVisibleDialog(true);
        } else {
            alert('Selecione um vinho para editar.');
        }
    };

    const saveEditedWine = async () => {
        try {
            await updateWine(editingWine.id, editingWine);
            setVisibleDialog(false);
            await fetchWines();
        } catch (error) {
            console.log(error);
        }
    };

    const onDeleteClick = async () => {
        if (selectedWines && selectedWines.length > 0) {
            setVisibleDeleteDialog(true);
        } else {
            alert('Selecione um vinho para excluir.');
        }
    };

    const confirmDeleteWines = async () => {
        try {
            const wineIds = selectedWines.map((wine) => wine.id);
            await deleteWine(wineIds);
            setVisibleDeleteDialog(false);
            setSelectedWines(null);
            await fetchWines();
        } catch (error) {
            console.log(error);
        }
    };

    const onNewClick = () => {
        navigate('/wine');
    };

    const onNewPointScale = () => {
            navigate('/wine-review');        
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
            <>
                <div className="flex justify-content-end">
                    <MultiSelect value={visibleColumns} options={columns} optionLabel="header" onChange={onColumnToggle} className="w-full sm:w-20rem" display="chip" />
                    <span className="p-input-icon-left">
                        <i className="pi pi-search" />
                        <InputText value={globalFilterValue} onChange={onGlobalFilterChange} placeholder="Keyword Search" />
                    </span>
                </div>
            </>
        );
    };

    const columns = [
        { field: 'name', header: 'Vinho' },
        { field: 'price', header: 'Preço de compra' },
        { field: 'purchaseLocation', header: 'Local de compra' },
        { field: 'purchaseDate', header: 'Data de compra' },
        { field: 'wineType', header: 'Tipo' },
        { field: 'wineClassification', header: 'Classificação' },
        { field: 'alcoholContent', header: 'Graduação Alcoólica' },
        { field: 'volumeMl', header: 'Volume em ML' },
        { field: 'grape', header: 'Uvas' },
        { field: 'winery', header: 'Produtor' },
        { field: 'serviceTemperature', header: 'Temperatura de serviço' },
        { field: 'harvest', header: 'Safra' },
        { field: 'guardTime', header: 'Tempo de guarda' },
        { field: 'country', header: 'País' },
        { field: 'region', header: 'Região' },
        { field: 'maturation', header: 'Maturação' },
        { field: 'harmonization', header: 'Harmonização' }
    ]

    const [visibleColumns, setVisibleColumns] = useState(columns);
    const onColumnToggle = (event) => {
        let selectedColumns = event.value;
        let orderedSelectedColumns = columns.filter((col) => selectedColumns.some((sCol) => sCol.field === col.field));

        setVisibleColumns(orderedSelectedColumns);
    };

    const onSelectionChange = (e) => {
        setSelectedWines(e.value);
    };

    const onSelectAllChange = (e) => {
        const _selectedWines = e.checked ? wines.map(wine => wine) : null;
        if (_selectedWines) {
            setSelectedWines(_selectedWines);
        } else {
            setSelectedWines(null);
        }
    };

    const exportCSV = () => {
        dt.current.exportCSV();
    };

    const leftToolbarTemplate = () => {
        return (
            <>
                <Dialog visible={visibleDeleteDialog} onHide={() => setVisibleDeleteDialog(false)} header="Excluir Vinhos" modal>
                    <div className="p-dialog-content">
                        <p>Deseja realmente excluir os vinhos selecionados?</p>
                    </div>
                    <div className="p-dialog-footer">
                        <Button label="Cancelar" icon="pi pi-times" className="p-button-text" onClick={() => setVisibleDeleteDialog(false)} />
                        <Button label="Confirmar" icon="pi pi-check" className="p-button-danger" onClick={confirmDeleteWines} />
                    </div>
                </Dialog>

                <Dialog visible={visibleDialog} onHide={() => setVisibleDialog(false)} header="Editar Vinho" modal>
                    <div className="p-fluid">
                        <div className="p-field">
                            <label htmlFor="name">Nome</label>
                            <InputText id="name" value={editingWine?.name || ''} onChange={(e) => setEditingWine({ ...editingWine, name: e.target.value })} />
                        </div>
                        <div className="p-field">
                            <label htmlFor="price">Preço de compra</label>
                            <InputText id="price" value={editingWine?.price || ''} onChange={(e) => setEditingWine({ ...editingWine, price: e.target.value })} />
                        </div>
                        <div className="p-field">
                            <label htmlFor="purchaseLocation">Local de compra</label>
                            <InputText id="purchaseLocation" value={editingWine?.purchaseLocation || ''} onChange={(e) => setEditingWine({ ...editingWine, purchaseLocation: e.target.value })} />
                        </div>
                        <div className="p-field">
                            <label htmlFor="purchaseDate">Data de compra</label>
                            <InputText id="purchaseDate" value={editingWine?.purchaseDate || ''} onChange={(e) => setEditingWine({ ...editingWine, purchaseDate: e.target.value })} />
                        </div>
                        <div className="p-field">
                            <label htmlFor="wineType">Tipo</label>
                            <InputText id="wineType" value={editingWine?.wineType || ''} onChange={(e) => setEditingWine({ ...editingWine, wineType: e.target.value })} />
                        </div>
                        <div className="p-field">
                            <label htmlFor="wineClassification">Classificação</label>
                            <InputText id="wineClassification" value={editingWine?.wineClassification || ''} onChange={(e) => setEditingWine({ ...editingWine, wineClassification: e.target.value })} />
                        </div>
                        <div className="p-field">
                            <label htmlFor="alcoholContent">Graduação Alcoólica</label>
                            <InputText id="alcoholContent" value={editingWine?.alcoholContent || ''} onChange={(e) => setEditingWine({ ...editingWine, alcoholContent: e.target.value })} />
                        </div>
                        <div className="p-field">
                            <label htmlFor="volumeMl">Volume em ML</label>
                            <InputText id="volumeMl" value={editingWine?.volumeMl || ''} onChange={(e) => setEditingWine({ ...editingWine, volumeMl: e.target.value })} />
                        </div>
                        <div className="p-field">
                            <label htmlFor="grape">Uvas</label>
                            <InputText id="grape" value={editingWine?.grape || ''} onChange={(e) => setEditingWine({ ...editingWine, grape: e.target.value })} />
                        </div>
                        <div className="p-field">
                            <label htmlFor="winery">Produtor</label>
                            <InputText id="winery" value={editingWine?.winery || ''} onChange={(e) => setEditingWine({ ...editingWine, winery: e.target.value })} />
                        </div>
                        <div className="p-field">
                            <label htmlFor="serviceTemperature">Temperatura de serviço</label>
                            <InputText id="serviceTemperature" value={editingWine?.serviceTemperature || ''} onChange={(e) => setEditingWine({ ...editingWine, serviceTemperature: e.target.value })} />
                        </div>
                        <div className="p-field">
                            <label htmlFor="harvest">Safra</label>
                            <InputText id="harvest" value={editingWine?.harvest || ''} onChange={(e) => setEditingWine({ ...editingWine, harvest: e.target.value })} />
                        </div>
                        <div className="p-field">
                            <label htmlFor="country">País</label>
                            <InputText id="country" value={editingWine?.country || ''} onChange={(e) => setEditingWine({ ...editingWine, country: e.target.value })} />
                        </div>
                        <div className="p-field">
                            <label htmlFor="guardTime">Tempo de guarda</label>
                            <InputText id="guardTime" value={editingWine?.guardTime || ''} onChange={(e) => setEditingWine({ ...editingWine, guardTime: e.target.value })} />
                        </div>
                        <div className="p-field">
                            <label htmlFor="region">Região</label>
                            <InputText id="region" value={editingWine?.region || ''} onChange={(e) => setEditingWine({ ...editingWine, region: e.target.value })} />
                        </div>
                        <div className="p-field">
                            <label htmlFor="maturation">Maturação</label>
                            <InputText id="maturation" value={editingWine?.maturation || ''} onChange={(e) => setEditingWine({ ...editingWine, maturation: e.target.value })} />
                        </div>
                        <div className="p-field">
                            <label htmlFor="harmonization">Harmonização</label>
                            <InputText id="harmonization" value={editingWine?.harmonization || ''} onChange={(e) => setEditingWine({ ...editingWine, harmonization: e.target.value })} />
                        </div>
                    </div>
                    <div className="p-d-flex p-jc-between">
                        <Button label="Cancelar" icon="pi pi-times" className="p-button-danger" onClick={() => setVisibleDialog(false)} />
                        <Button label="Salvar" icon="pi pi-check" className="p-button-success" onClick={saveEditedWine} />
                    </div>
                </Dialog>
                <div className="flex flex-wrap gap-2">
                    <Button rounded label="Novo" icon="pi pi-plus" severity="success" onClick={onNewClick} raised />
                    <Button rounded label="Editar" icon="pi pi-pencil" severity="secondary" onClick={onEditClick} disabled={!selectedWines || selectedWines.length !== 1} raised />
                    <Button rounded label="Excluir" icon="pi pi-trash" severity="danger" onClick={onDeleteClick} disabled={!selectedWines || selectedWines.length === 0} raised />
                    <Button rounded label="Avaliar" icon="pi pi-star" className="p-button-help" onClick={onNewPointScale} disabled={!selectedWines || selectedWines.length === 0} raised />
                </div>
            </>
        );
    };

    const rightToolbarTemplate = () => {
        return <Button rounded label="CSV" icon="pi pi-upload" className="p-button-help" onClick={exportCSV} raised />;
    };

    const header = renderHeader();

    return (
        <>
            <Card style={{ marginTop: 10 }} title="Lista de Vinhos" >
                <Toolbar className="mb-4" start={leftToolbarTemplate} end={rightToolbarTemplate}></Toolbar>
                <DataTable
                    value={wines}
                    paginator
                    rows={10}
                    rowsPerPageOptions={[10, 20, 30, 50]}
                    loading={loading}
                    filters={filters}
                    globalFilterFields={['name', 'price', 'purchaseLocation', 'purchaseDate', 'wineType', 'wineClassification', 'alcoholContent', 'volumeMl', 'grape', 'winery', 'serviceTemperature', 'harvest', 'country', 'guardTime', 'region', 'maturation', 'harmonization']}
                    header={header}
                    resizableColumns
                    showGridlines
                    tableStyle={{ minWidth: '50rem' }}
                    emptyMessage="Nenhum vinho encontrado."
                    selectionMode="multiple"
                    selection={selectedWines}
                    onSelectionChange={onSelectionChange}
                    onSelectAll={onSelectAllChange}
                    ref={dt}
                >
                    <Column selectionMode="multiple" headerStyle={{ width: '3rem' }} />
                    {visibleColumns.map((col) => (
                        <Column key={col.field} field={col.field} header={col.header} sortable filterField={col.field} />
                    ))}
                </DataTable>

            </Card>
        </>
    )
};

export default ListWineComponent