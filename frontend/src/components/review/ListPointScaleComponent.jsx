import { useState, useEffect } from 'react';
import { FilterMatchMode } from 'primereact/api';
import { InputText } from 'primereact/inputtext';
import { DataTable } from 'primereact/datatable';
import { Column } from 'primereact/column';
import useListPointScaleComponentHook from '../../hooks/review/usePointScaleComponentHook';

const ListPointScaleComponent = () => {
    const { pointScales } = useListPointScaleComponentHook();
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
            <h5><strong>Avaliações</strong></h5>
            <DataTable
                value={pointScales}
                paginator
                rows={10}
                rowsPerPageOptions={[10, 20, 30, 50]}
                loading={loading}
                filters={filters}
                globalFilterFields={['whatTasted', 'whenTasted', 'whatSaw', 'whatAromas', 'whatFlavors', 'whatOpinion', 'pointScale']}
                header={header}
                tableStyle={{ minWidth: '50rem' }}
                emptyMessage="Nenhuma avaliação encontrada."
            >

                <Column
                    field='whatTasted'
                    header='O que foi degustado'
                    sortable
                    filterField='whatTasted'
                ></Column>
                <Column
                    field='whenTasted'
                    header='Quando foi degustado'
                    sortable
                    filterField='whenTasted'
                ></Column>
                <Column
                    field='whatSaw'
                    header='Aspectos visuais'
                    sortable
                    filterField='whatSaw'
                ></Column>
                <Column
                    field='whatAromas'
                    header='Aromas'
                    sortable
                    filterField='whatAromas'
                ></Column>
                <Column
                    field='whatFlavors'
                    header='Sabores'
                    sortable
                    filterField='whatFlavors'
                ></Column>
                <Column
                    field='whatOpinion'
                    header='Opinião'
                    sortable
                    filterField='whatOpinion'
                ></Column>
                <Column
                    field='pointScale'
                    header='Avaliação final'
                    sortable
                    filterField='pointScale'
                ></Column>

            </DataTable>
        </div>
    )
}

export default ListPointScaleComponent