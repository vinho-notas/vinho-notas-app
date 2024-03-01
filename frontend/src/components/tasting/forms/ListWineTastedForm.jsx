import { useState } from 'react';
import { DataTable } from 'primereact/datatable';
import { Column } from 'primereact/column';

import tastings from '../../../data/tasting_data_mock.json';

const ListWineTastedForm = () => {    
    const [expandedRows, setExpandedRows] = useState(null);
    const allowExpansion = (tasting) => {
        return tasting.tastingCards ? true : false;
    };

    const rowExpansionTemplate = (tasting) => {
        return (
            <>
            <div className="card">
                <h5><strong>Ficha de inspeção visual de {tasting.tastingCards.wineTasted} </strong></h5>
                <DataTable value={[tasting.tastingCards]}>
                    <Column field="visualInspection.clarity" header="Claridade" sortable></Column>
                    <Column field="visualInspection.brightness" header="Brilho" sortable></Column>
                    <Column field="visualInspection.viscosity" header="Viscosidade" sortable></Column>
                    <Column field="visualInspection.colorRed" header="Coloração" sortable></Column>
                    <Column field="visualInspection.colorWhite" header="Coloração" sortable></Column>
                    <Column field="visualInspection.colorRose" header="Coloração" sortable></Column>
                    <Column field="visualInspection.classification" header="Classificação" sortable></Column>                    
                </DataTable>
            </div>

            <div className="card">
                <h5><strong>Ficha de inspeção olfativa de {tasting.tastingCards.wineTasted} </strong></h5>
                <DataTable value={[tasting.tastingCards]}>
                    <Column field="olfactoryInspection.intensity" header="Intensidade" sortable></Column>
                    <Column field="olfactoryInspection.persistence" header="Persistência" sortable></Column>
                    <Column field="olfactoryInspection.quality" header="Qualidade" sortable></Column>
                    <Column field="olfactoryInspection.aromas.fruity" header="Frutados" sortable></Column>
                    <Column field="olfactoryInspection.aromas.dryFruits" header="Frutas secas" sortable></Column>
                    <Column field="olfactoryInspection.aromas.florals" header="Florais" sortable></Column>
                    <Column field="olfactoryInspection.aromas.vegetablesAndHerbs" header="Vegetais e herbáceos" sortable></Column>
                    <Column field="olfactoryInspection.aromas.minerals" header="Minerais" sortable></Column>
                    <Column field="olfactoryInspection.aromas.spices" header="Especiarias" sortable></Column>
                    <Column field="olfactoryInspection.aromas.animals" header="Animais" sortable></Column>
                    <Column field="olfactoryInspection.aromas.empireumatics" header="Empireumáticos" sortable></Column>
                    <Column field="olfactoryInspection.aromas.wood" header="Madeira" sortable></Column>
                    <Column field="olfactoryInspection.aromas.chemicals" header="Químicos e etéreos" sortable></Column>
                    <Column field="olfactoryInspection.aromas.lacteal" header="Lácteos" sortable></Column>
                    <Column field="olfactoryInspection.aromas.sweets" header="Adocicados" sortable></Column>
                    <Column field="olfactoryInspection.classification" header="Classificação" sortable></Column>                    
                </DataTable>
            </div>

            <div className="card">
                <h5><strong>Ficha de inspeção gustativa de {tasting.tastingCards.wineTasted} </strong></h5>
                <DataTable value={[tasting.tastingCards]}>
                    <Column field="gustatoryInspection.body" header="Corpo" sortable></Column>
                    <Column field="gustatoryInspection.sweetness" header="Doçura" sortable></Column>
                    <Column field="gustatoryInspection.tannin" header="Tanino" sortable></Column>
                    <Column field="gustatoryInspection.acidity" header="Acidez" sortable></Column>
                    <Column field="gustatoryInspection.alcohol" header="Álcool" sortable></Column>
                    <Column field="gustatoryInspection.persistence" header="Persistência" sortable></Column>
                    <Column field="gustatoryInspection.maturity" header="Maturidade" sortable></Column>
                    <Column field="gustatoryInspection.typicality" header="tipicidade" sortable></Column>                    
                    <Column field="gustatoryInspection.classification" header="Classificação" sortable></Column>                    
                </DataTable>
            </div>
            </>
        );
    };

    return (       
            <div className="card">
            <DataTable
                value={tastings}
                expandedRows={expandedRows}
                onRowToggle={(e) => setExpandedRows(e.data)}
                rowExpansionTemplate={rowExpansionTemplate}
                dataKey="id"
                paginator
                rows={10}
                rowsPerPageOptions={[10, 20, 30, 50]}
                tableStyle={{ minWidth: '50rem' }}
            >
                <Column expander={allowExpansion} style={{ width: '5rem' }} />
                <Column field="tastingData" header="Data"></Column>
                <Column field="tastingType" header="Tipo de degustação"></Column>
                <Column field="tastingCards.wineTasted" header="Vinho degustado"></Column>
                <Column field="tastingCards.harvest" header="Safra"></Column>
                <Column field="tastingCards.grapes" header="Uvas"></Column>
                <Column field="tastingCards.country" header="País"></Column>
                <Column field="tastingCards.region" header="Região"></Column>
                <Column field="tastingCards.opinion" header="Opinião"></Column>
                <Column field="tastingCards.pointScale" header="Avaliação geral"></Column>
            </DataTable>
        </div>
    )
}

export default ListWineTastedForm