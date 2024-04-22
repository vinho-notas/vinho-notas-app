import { useState, useEffect, useRef } from 'react';
import { FilterMatchMode } from 'primereact/api';
import { InputText } from 'primereact/inputtext';
import { DataTable } from 'primereact/datatable';
import { Column } from 'primereact/column';
import { Card } from 'primereact/card';
import { Toolbar } from 'primereact/toolbar';
import { Button } from 'primereact/button';
import { Dialog } from 'primereact/dialog';
import { Dropdown } from 'primereact/dropdown';
import useListPointScaleComponentHook from '../../hooks/review/usePointScaleComponentHook';
import { deletePointScale, updatePointScale, deleteAllPointScale } from '../../service/review/PointScaleService';
import EnumPointScale from '../../utils/enums/EnumPointScale';

const ListPointScaleComponent = () => {

    //hooks
    const { pointScales, navigate, fetchPointScales } = useListPointScaleComponentHook();
    const [selectedPointScales, setSelectedPointScales] = useState(null);
    const [editingPointScales, setEditingPointScales] = useState(null);
    const [visibleEditDialog, setVisibleEditDialog] = useState(false);
    const [visibleDeleteDialog, setVisibleDeleteDialog] = useState(false);
    const dt = useRef(null);
    const [filters, setFilters] = useState({
        global: { value: null, matchMode: FilterMatchMode.CONTAINS },
        whatTasted: { value: null, matchMode: FilterMatchMode.CONTAINS },
        whenTasted: { value: null, matchMode: FilterMatchMode.CONTAINS },
        whatSaw: { value: null, matchMode: FilterMatchMode.CONTAINS },
        whatAromas: { value: null, matchMode: FilterMatchMode.CONTAINS },
        whatFlavors: { value: null, matchMode: FilterMatchMode.CONTAINS },
        whatOpinion: { value: null, matchMode: FilterMatchMode.CONTAINS },
        pointScale: { value: null, matchMode: FilterMatchMode.CONTAINS }
    });


    //constants
    const pointScale = Object.values(EnumPointScale);
    const columns = [
        { field: 'whatTasted', header: 'O que foi degustado', sortable: true, filterField: 'whatTasted' },
        { field: 'whenTasted', header: 'Quando foi degustado', sortable: true, filterField: 'whenTasted' },
        { field: 'whatSaw', header: 'Aspectos visuais', sortable: true, filterField: 'whatSaw' },
        { field: 'whatAromas', header: 'Aromas', sortable: true, filterField: 'whatAromas' },
        { field: 'whatFlavors', header: 'Sabores', sortable: true, filterField: 'whatFlavors' },
        { field: 'whatOpinion', header: 'Opinião', sortable: true, filterField: 'whatOpinion' },
        { field: 'pointScale', header: 'Avaliação final', sortable: true, filterField: 'pointScale' }
    ]
    const [visibleColumns, setVisibleColumns] = useState([]);

    const [loading, setLoading] = useState(true);
    const [globalFilterValue, setGlobalFilterValue] = useState('');

    const exportCSV = () => {
        dt.current.exportCSV();
    };


    //handlers    
    const onEditClick = () => {
        if (selectedPointScales && selectedPointScales.length === 1) {
            setEditingPointScales(selectedPointScales[0]);
            setVisibleEditDialog(true);
        } else {
            alert('Selecione um vinho para editar.');
        }
    };

    const onDeleteClick = async () => {
        if (selectedPointScales && selectedPointScales.length > 0) {
            setVisibleDeleteDialog(true);
        } else {
            alert('Selecione um vinho para excluir.');
        }
    };

    const confirmDeletePointScales = async () => {
        try {
            if (selectedPointScales.length === 1) {
                await deletePointScale(selectedPointScales[0].id);
            } else if (selectedPointScales.length > 1) {
                const pointScalesIds = selectedPointScales.map(scale => scale.id);
                await deleteAllPointScale(pointScalesIds);
            }

            setVisibleDeleteDialog(false);
            setSelectedPointScales(null);
            await fetchPointScales();
        } catch (error) {
            console.log(error);
        }
    };

    const saveEditedPointScale = async () => {
        try {
            await updatePointScale(editingPointScales.id, editingPointScales);
            setVisibleEditDialog(false);
            await fetchPointScales();
            navigate("/wine-review-list")
        } catch (error) {
            console.log(error);
        }

    };

    const leftToolbarTemplate = () => {
        return (
            <>            
                <Dialog visible={visibleEditDialog} onHide={() => setVisibleEditDialog(false)} header="Editar avaliação" modal>
                    <div className="p-fluid">
                        <div className="p-field">
                            <label htmlFor="name">O que foi degustado</label>
                            <InputText id="name" value={editingPointScales?.whatTasted || ''} onChange={(e) => setEditingPointScales({ ...editingPointScales, whatTasted: e.target.value })} />
                        </div>
                    </div>
                    <div className="p-fluid">
                        <div className="p-field">
                            <label htmlFor="name">Quando foi degustado</label>
                            <InputText id="name" value={editingPointScales?.whenTasted || ''} onChange={(e) => setEditingPointScales({ ...editingPointScales, whenTasted: e.target.value })} />
                        </div>
                    </div>
                    <div className="p-fluid">
                        <div className="p-field">
                            <label htmlFor="name">Aspectos visuais</label>
                            <InputText id="name" value={editingPointScales?.whatSaw || ''} onChange={(e) => setEditingPointScales({ ...editingPointScales, whatSaw: e.target.value })} />
                        </div>
                    </div>
                    <div className="p-fluid">
                        <div className="p-field">
                            <label htmlFor="name">Aromas</label>
                            <InputText id="name" value={editingPointScales?.whatAromas || ''} onChange={(e) => setEditingPointScales({ ...editingPointScales, whatAromas: e.target.value })} />
                        </div>
                    </div>
                    <div className="p-fluid">
                        <div className="p-field">
                            <label htmlFor="name">Sabores</label>
                            <InputText id="name" value={editingPointScales?.whatFlavors || ''} onChange={(e) => setEditingPointScales({ ...editingPointScales, whatFlavors: e.target.value })} />
                        </div>
                    </div>
                    <div className="p-fluid">
                        <div className="p-field">
                            <label htmlFor="name">Opinião</label>
                            <InputText id="name" value={editingPointScales?.whatOpinion || ''} onChange={(e) => setEditingPointScales({ ...editingPointScales, whatOpinion: e.target.value })} />
                        </div>
                    </div>
                    <div className="p-fluid">
                        <div className="p-field">
                            <label htmlFor="name">Avaliação final</label>
                            <Dropdown id="pointScale" value={editingPointScales?.pointScale || ''} options={pointScale} onChange={(e) => setEditingPointScales({ ...editingPointScales, pointScale: e.target.value })} />                     
                        </div>
                    </div>
                    <div className="flex flex-wrap gap-2 mt-4">
                        <Button label="Cancelar" icon="pi pi-times" onClick={() => setVisibleEditDialog(false)} className="p-button-danger" />
                        <Button label="Salvar" icon="pi pi-check" onClick={saveEditedPointScale} className="p-button-success" />
                    </div>


                </Dialog>
                <Dialog visible={visibleDeleteDialog} onHide={() => setVisibleDeleteDialog(false)} header="Excluir avaliações" modal>
                    <div className="flex justify-content-center">
                        <p>Tem certeza que deseja excluir as avaliações selecionadas?</p>
                    </div>
                    <div className="flex flex-wrap gap-2 mt-4">
                        <Button label="Cancelar" icon="pi pi-times" onClick={() => setVisibleDeleteDialog(false)} className="p-button-danger" />
                        <Button label="Confirmar" icon="pi pi-check" onClick={confirmDeletePointScales} className="p-button-success" />
                    </div>
                </Dialog>

                <div className="flex flex-wrap gap-2">
                    <Button rounded label="Editar" icon="pi pi-pencil" severity="secondary" onClick={onEditClick} disabled={!selectedPointScales || selectedPointScales.length !== 1} raised />
                    <Button rounded label="Excluir" icon="pi pi-trash" severity="danger" onClick={onDeleteClick} disabled={!selectedPointScales || selectedPointScales.length === 0} raised />
                </div>
            </>
        )
    };

    const rightToolbarTemplate = () => {
        return <Button rounded label="CSV" icon="pi pi-upload" className="p-button-help" onClick={exportCSV} raised />;
    };

    const onSelectionChange = (e) => {
        setSelectedPointScales(e.value);
    };

    const onSelectAllChange = (e) => {
        const _selectedPointScales = e.checked ? pointScales.map(scale => scale) : null;
        if (_selectedPointScales) {
            setSelectedPointScales(_selectedPointScales);
        } else {
            setSelectedPointScales(null);
        }
    };

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

    //effects
    useEffect(() => {
        setLoading(false);
        setVisibleColumns(columns);
    }, []);

    //Render

    return (
        <Card style={{ marginTop: 10 }} title="Lista de avaliações">
            <Toolbar className="mb-4" start={leftToolbarTemplate} end={rightToolbarTemplate}></Toolbar>
            <DataTable
                value={pointScales}
                paginator
                rows={10}
                rowsPerPageOptions={[10, 20, 30, 50]}
                loading={loading}
                filters={filters}
                globalFilterFields={['whatTasted', 'whenTasted', 'whatSaw', 'whatAromas', 'whatFlavors', 'whatOpinion', 'pointScale']}
                header={header}
                showGridlines
                tableStyle={{ minWidth: '50rem' }}
                emptyMessage="Nenhuma avaliação encontrada."
                selectionMode="multiple"
                selection={selectedPointScales}
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
    )
}

export default ListPointScaleComponent