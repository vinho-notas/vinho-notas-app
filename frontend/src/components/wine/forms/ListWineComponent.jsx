import { useState, useEffect } from 'react';
import { FilterMatchMode } from 'primereact/api';
import { InputText } from 'primereact/inputtext';
import { DataTable } from 'primereact/datatable';
import { Column } from 'primereact/column';
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
            <h5><strong>Vinhos</strong></h5>
            <DataTable
            value={wines}
            paginator
            rows={10}
            rowsPerPageOptions={[10, 20, 30, 50]}
            loading={loading}
            filters={filters}
            globalFilterFields={['name', 'price', 'purchaseLocation', 'purchaseDate', 'wineType', 'wineClassification', 'alcoholContent', 'volumeMl', 'grape', 'winery', 'serviceTemperature', 'harvest', 'country', 'guardTime', 'region', 'maturation', 'harmonization']}
            header={header}
            tableStyle={{ minWidth: '50rem' }}
            emptyMessage="Nenhum vinho encontrado."
            >
                <Column field='name' header='Vinho' sortable filterField='name' ></Column>
                <Column field='price' header='Preço de compra' sortable filterField='price' ></Column>
                <Column field='purchaseLocation' header='Local de compra' sortable filterField='purchaseLocation' ></Column>
                <Column field='purchaseDate' header='Data de compra' sortable filterField='purchaseDate' ></Column>
                <Column field='wineType' header='Tipo' sortable filterField='wineType' ></Column>
                <Column field='wineClassification' header='Classificação' sortable filterField='wineClassification' ></Column>
                <Column field='alcoholContent' header='Graduação Alcoólica' sortable filterField='alcoholContent' ></Column>
                <Column field='volumeMl' header='Volume em ML' sortable filterField='volumeMl' ></Column>
                <Column field='grape' header='Uvas' sortable filterField='grape' ></Column>
                <Column field='winery' header='Produtor' sortable filterField='winery' ></Column>
                <Column field='serviceTemperature' header='Temperatura de serviço' sortable filterField='serviceTemperature' ></Column>
                <Column field='harvest' header='Safra' sortable filterField='harvest' ></Column>
                <Column field='guardTime' header='Tempo de guarda' sortable filterField='guardTime' ></Column>
                <Column field='country' header='País' sortable filterField='country' ></Column>
                <Column field='region' header='Região' sortable filterField='region' ></Column>
                <Column field='maturation' header='Maturação' sortable filterField='maturation' ></Column>
                <Column field='harmonization' header='Harmonização' sortable filterField='harmonization' ></Column>

            </DataTable>

        </div>
    )

};

export default ListWineComponent