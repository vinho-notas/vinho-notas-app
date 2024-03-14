import { Card } from 'primereact/card';
import { useState, useEffect, useRef } from 'react';
import { FilterMatchMode } from 'primereact/api';
import { InputText } from 'primereact/inputtext';
import { DataTable } from 'primereact/datatable';
import { Column } from 'primereact/column';
import { MultiSelect } from 'primereact/multiselect';
import { Toolbar } from 'primereact/toolbar';
import { Button } from 'primereact/button';
import useListWineComponentHook from '../../../hooks/wine/useListWineComponentHook';

const ListWineComponent = () => {
    const { wines } = useListWineComponentHook();
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

    const [selectedWines, setSelectedWines] = useState(null);

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
            <div className="flex flex-wrap gap-2">
                <Button rounded label="Novo" icon="pi pi-plus" severity="success" onClick={''} raised />
                <Button rounded label="Editar" icon="pi pi-pencil" severity="secondary" onClick={''} raised />
                <Button rounded label="Excluir" icon="pi pi-trash" severity="danger" onClick={''} disabled={''} raised />
            </div>
        );
    };

    const rightToolbarTemplate = () => {
        return <Button rounded label="CSV" icon="pi pi-upload" className="p-button-help" onClick={exportCSV} raised />;
    };

    const header = renderHeader();

    return (
        <>
            <Card style={{ marginTop: 10 }} title="Lista de Vinhos" >
                {/* <Card.Header as="h5" >Lista de Vinhos</Card.Header> */}
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