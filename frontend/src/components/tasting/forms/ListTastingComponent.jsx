import { useState, useEffect } from 'react';
import { FilterMatchMode } from 'primereact/api';
import { InputText } from 'primereact/inputtext';
import { DataTable } from 'primereact/datatable';
import { Column } from 'primereact/column';
import useListTastingComponentHook from '../../../hooks/tasting/useTastingComponentHook';
import useListTastingCardComponentHook from '../../../hooks/tasting/useTastingCardComponentHook';
import useAromasComponentHook from '../../../hooks/tasting/useAromasComponentHook';
import useGustatoryInspectionComponentHook from '../../../hooks/tasting/useGustatoryInspectionComponentHook';
import useOlfactoryInspectionComponentHook from '../../../hooks/tasting/useOlfactoryInspectionComponentHook';
import useVisualInspectionComponentHook from '../../../hooks/tasting/useVisualInspectionComponentHook';

const ListTastingComponent = () => {

    const [expandedRows, setExpandedRows] = useState(null);
    const allowExpansion = (tasting) => {
        return tasting.tastingCards ? true : false;
    }

    const { tastings } = useListTastingComponentHook();
    const { tastingCards } = useListTastingCardComponentHook();
    const { aromas } = useAromasComponentHook();
    const { gustatoryInspections } = useGustatoryInspectionComponentHook();
    const { olfactoryInspections } = useOlfactoryInspectionComponentHook();
    const { visualInspections } = useVisualInspectionComponentHook();

    const [filters, setFilters] = useState({
        global: { value: null, matchMode: FilterMatchMode.CONTAINS },
        tastingData: { value: null, matchMode: FilterMatchMode.CONTAINS },
        tastingType: { value: null, matchMode: FilterMatchMode.CONTAINS },
        wineTasted: { value: null, matchMode: FilterMatchMode.CONTAINS },
        harvest: { value: null, matchMode: FilterMatchMode.CONTAINS },
        grapes: { value: null, matchMode: FilterMatchMode.CONTAINS },
        country: { value: null, matchMode: FilterMatchMode.CONTAINS },
        region: { value: null, matchMode: FilterMatchMode.CONTAINS }
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

    const rowExpansionTemplate = (tasting) => {
        const combinedData = olfactoryInspections.map((inspection, index) => ({
            ...inspection,
            ...aromas[index]
        }));
    
        return (
            <>
                <div className='card'>
                    <h5>Ficha de inspeção visual</h5>
                    <DataTable
                        value={visualInspections}
                        paginator
                        rows={10}
                        rowsPerPageOptions={[10, 20, 30, 50]}
                        loading={loading}
                        tableStyle={{ minWidth: '50rem' }}
                        emptyMessage="Nenhuma degustação encontrada."
                    >
                        <Column field='clarity' header='Clareza' sortable filterField='clarity'></Column>
                        <Column field='brightness' header='Brilho' sortable filterField='brightness'></Column>
                        <Column field='viscosity' header='Viscosidade' sortable filterField='viscosity'></Column>
                        <Column field='colorRed' header='Coloração' sortable filterField='colorRed'></Column>
                        <Column field='colorWhite' header='Coloração' sortable filterField='colorWhite'></Column>
                        <Column field='colorRose' header='Coloração' sortable filterField='colorRose'></Column>
                        <Column field='classification' header='Classificação' sortable filterField='classification'></Column>
                    </DataTable>
                </div>

                <div className='card'>
                    <h5>Ficha de inspeção olfativa</h5>
                    <DataTable
                        value={combinedData}
                        paginator
                        rows={10}
                        rowsPerPageOptions={[10, 20, 30, 50]}
                        loading={loading}
                        tableStyle={{ minWidth: '50rem' }}
                        emptyMessage="Nenhuma degustação encontrada."
                    >
                        <Column field='intensity' header='Intensidade' sortable filterField='intensity'></Column>
                        <Column field='persistence' header='Persistência' sortable filterField='persistence'></Column>
                        <Column field='quality' header='Qualidade' sortable filterField='quality'></Column>
                        <Column field='classification' header='Classificação' sortable filterField='classification'></Column>
                        <Column field='fruity' header='Frutados' sortable filterField='fruity'></Column>
                        <Column field='dryFruits' header='Frutas secas' sortable filterField='dryFruits'></Column>
                        <Column field='florals' header='Florais' sortable filterField='florals'></Column>
                        <Column field='vegetablesAndHerbs' header='Vegetais e herbáceos' sortable filterField='vegetablesAndHerbs'></Column>
                        <Column field='minerals' header='Minerais' sortable filterField='minerals'></Column>
                        <Column field='spices' header='Especiarias' sortable filterField='spices'></Column>
                        <Column field='animals' header='Animais' sortable filterField='animals'></Column>
                        <Column field='empireumatics' header='Empireumáticos' sortable filterField='empireumatics'></Column>
                        <Column field='wood' header='Madeira' sortable filterField='wood'></Column>
                        <Column field='chemicals' header='Químicos e etéreos' sortable filterField='chemicals'></Column>
                        <Column field='lacteal' header='Lácteos' sortable filterField='lacteal'></Column>
                        <Column field='sweets' header='Adocicados' sortable filterField='sweets'></Column>
                    </DataTable>
                </div>                

                <div className='card'>
                    <h5>Ficha de inspeção gustativa</h5>
                    <DataTable
                        value={gustatoryInspections}
                        paginator
                        rows={10}
                        rowsPerPageOptions={[10, 20, 30, 50]}
                        loading={loading}
                        tableStyle={{ minWidth: '50rem' }}
                        emptyMessage="Nenhuma degustação encontrada."
                    >
                        <Column field='body' header='Corpo' sortable filterField='body'></Column>
                        <Column field='sweetness' header='Doçura' sortable filterField='sweetness'></Column>
                        <Column field='tannin' header='Tanino' sortable filterField='tannin'></Column>
                        <Column field='classification' header='Classificação' sortable filterField='classification'></Column>
                        <Column field='acidity' header='Acidez' sortable filterField='acidity'></Column>
                        <Column field='alcohol' header='Álcool' sortable filterField='alcohol'></Column>
                        <Column field='persistence' header='Persistência' sortable filterField='persistence'></Column>
                        <Column field='maturity' header='Maturidade' sortable filterField='maturity'></Column>
                        <Column field='typicality' header='Tipicidade' sortable filterField='typicality'></Column>
                    </DataTable>
                </div>
            </>
        );
    };

    const combinedtastingCards = tastingCards.map((tastingCard, index) => ({
        ...tastingCard,
        ...tastings[index]
    }));

    return (
        <>
            <div className='card'>
                <h5><strong>Degustações</strong></h5>
                <DataTable
                    value={combinedtastingCards}
                    expandedRows={expandedRows}
                    onRowToggle={(e) => setExpandedRows(e.data)}
                    rowExpansionTemplate={rowExpansionTemplate}
                    dataKey="id"
                    paginator
                    rows={10}
                    rowsPerPageOptions={[10, 20, 30, 50]}
                    loading={loading}
                    filters={filters}
                    globalFilterFields={["tastingData", "tastingType"]}
                    header={header}
                    tableStyle={{ minWidth: '50rem' }}
                    emptyMessage="Nenhuma degustação encontrada."
                >
                    <Column expander={allowExpansion} style={{ width: '5rem' }} />
                    <Column field='tastingData' header='Data da degustação' sortable filterField='tastingData'></Column>
                    <Column field='tastingType' header='Tipo de degustação' sortable filterField='tastingType'></Column>                    
                    <Column field='wineTasted' header='Vinho degustado' sortable filterField='wineTasted'></Column>
                    <Column field='harvest' header='Safra' sortable filterField='harvest'></Column>
                    <Column field='grapes' header='Uvas' sortable filterField='grapes'></Column>
                    <Column field='country' header='País' sortable filterField='country'></Column>
                    <Column field='region' header='Região' sortable filterField='region'></Column>
                </DataTable>
            </div>
        </>
    )
}

export default ListTastingComponent;
