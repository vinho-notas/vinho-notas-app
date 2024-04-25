import React, { useState, useEffect, useRef } from 'react';
import { Card } from 'primereact/card';
import { DataTable } from 'primereact/datatable';
import { Column } from 'primereact/column';
import { MultiSelect } from 'primereact/multiselect';
import { Dropdown } from 'primereact/dropdown';
import { Toolbar } from 'primereact/toolbar';
import { Button } from 'primereact/button';
import { Dialog } from 'primereact/dialog';
import { InputText } from 'primereact/inputtext';
import { FilterMatchMode } from 'primereact/api';
import { Calendar } from 'primereact/calendar';
import useListTastingCardComponentHook from '../../../hooks/tasting/useListTastingCardComponentHook';
import { updateTastingCard, deleteTastingCard, deleteAllTastingCards } from '../../../service/tasting/TastingCardService';
import EnumTastingType from '../../../utils/enums/EnumTastingType';
import EnumClarityType from '../../../utils/enums/EnumClarityType';
import EnumBrightnessType from '../../../utils/enums/EnumBrightnessType';
import EnumViscosityType from '../../../utils/enums/EnumViscosityType';
import EnumRedColorType from '../../../utils/enums/EnumRedColorType';
import EnumWhiteColorType from '../../../utils/enums/EnumWhiteColorType';
import EnumRoseColorType from '../../../utils/enums/EnumRoseColorType';
import EnumClassificationType from '../../../utils/enums/EnumClassificationType';
import EnumIntensityType from '../../../utils/enums/EnumIntensityType';
import EnumPersistenceType from '../../../utils/enums/EnumPersistenceType';
import EnumQualityType from '../../../utils/enums/EnumQualityType';
import EnumFruityType from '../../../utils/enums/EnumFruityType';
import EnumDryFruitsType from '../../../utils/enums/EnumDryFruitsType';
import EnumFloralsType from '../../../utils/enums/EnumFloralsType';
import EnumMineralsType from '../../../utils/enums/EnumMineralsType';
import EnumVegetablesAndHerbsType from '../../../utils/enums/EnumVegetablesAndHerbsType';
import EnumSpicesType from '../../../utils/enums/EnumSpicesType';
import EnumAnimalsType from '../../../utils/enums/EnumAnimalsType';
import EnumEmpireumaticsType from '../../../utils/enums/EnumEmpireumaticsType';
import EnumWoodType from '../../../utils/enums/EnumWoodType';
import EnumChemicalsAndEtherealType from '../../../utils/enums/EnumChemicalsAndEtherealType';
import EnumLactealType from '../../../utils/enums/EnumLactealType';
import EnumSweetsType from '../../../utils/enums/EnumSweetsType ';
import EnumBodyType from '../../../utils/enums/EnumBodyType';
import EnumSweetnessType from '../../../utils/enums/EnumSweetnessType';
import EnumTanninType from '../../../utils/enums/EnumTanninType';
import EnumAcidityType from '../../../utils/enums/EnumAcidityType';
import EnumAlcoholType from '../../../utils/enums/EnumAlcoholType';
import EnumMaturityType from '../../../utils/enums/EnumMaturityType';
import EnumTypicalityType from '../../../utils/enums/EnumTypicalityType';
import EnumPointScale from '../../../utils/enums/EnumPointScale';

const ListCardTastingComponent = () => {

    const { tastingCards, navigate, fetchTastingCards } = useListTastingCardComponentHook();
    const [selectedTastingCards, setSelectedTastingCards] = useState(null);
    const [editingTastingCards, setEditingTastingCards] = useState(null);
    const [visibleEditDialog, setVisibleEditDialog] = useState(false);
    const [visibleDeleteDialog, setVisibleDeleteDialog] = useState(false);
    const [loading, setLoading] = useState(true);
    const [globalFilterValue, setGlobalFilterValue] = useState('');
    const [visibleColumns, setVisibleColumns] = useState([]);
    const dt = useRef(null);
    const enumTastingType = Object.values(EnumTastingType);
    const enumClarityType = Object.values(EnumClarityType);
    const enumBrightnessType = Object.values(EnumBrightnessType);
    const enumViscosityType = Object.values(EnumViscosityType);
    const enumRedColorType = Object.values(EnumRedColorType);
    const enumWhiteColorType = Object.values(EnumWhiteColorType);
    const enumRoseColorType = Object.values(EnumRoseColorType);
    const enumClassificationType = Object.values(EnumClassificationType);
    const enumIntensityType = Object.values(EnumIntensityType);
    const enumPersistenceType = Object.values(EnumPersistenceType);
    const enumQualityType = Object.values(EnumQualityType);
    const enumFruityType = Object.values(EnumFruityType);
    const enumDryFruitsType = Object.values(EnumDryFruitsType);
    const enumFloralsType = Object.values(EnumFloralsType);
    const enumVegetablesAndHerbsType = Object.values(EnumVegetablesAndHerbsType);
    const enumMineralsType = Object.values(EnumMineralsType);
    const enumSpicesType = Object.values(EnumSpicesType);
    const enumAnimalsType = Object.values(EnumAnimalsType);
    const enumEmpireumaticsType = Object.values(EnumEmpireumaticsType);
    const enumWoodType = Object.values(EnumWoodType);
    const enumChemicalsAndEtherealType = Object.values(EnumChemicalsAndEtherealType);
    const enumLactealType = Object.values(EnumLactealType);
    const enumSweetsType = Object.values(EnumSweetsType);
    const enumBodyType = Object.values(EnumBodyType);
    const enumSweetnessType = Object.values(EnumSweetnessType);
    const enumTanninType = Object.values(EnumTanninType);
    const enumAcidityType = Object.values(EnumAcidityType);
    const enumAlcoholType = Object.values(EnumAlcoholType);
    const enumMaturityType = Object.values(EnumMaturityType);
    const enumTypicalityType = Object.values(EnumTypicalityType);
    const enumPointScale = Object.values(EnumPointScale);

    const [filters, setFilters] = useState({
        global: { value: null, matchMode: FilterMatchMode.CONTAINS },
        tastingData: { value: null, matchMode: FilterMatchMode.CONTAINS },
        wineTasted: { value: null, matchMode: FilterMatchMode.CONTAINS },
        harvest: { value: null, matchMode: FilterMatchMode.CONTAINS },
        grapes: { value: null, matchMode: FilterMatchMode.CONTAINS },
        country: { value: null, matchMode: FilterMatchMode.CONTAINS },
        region: { value: null, matchMode: FilterMatchMode.CONTAINS },
        tastingType: { value: null, matchMode: FilterMatchMode.CONTAINS },
        clarity: { value: null, matchMode: FilterMatchMode.CONTAINS },
        brightness: { value: null, matchMode: FilterMatchMode.CONTAINS },
        viscosity: { value: null, matchMode: FilterMatchMode.CONTAINS },
        colorRed: { value: null, matchMode: FilterMatchMode.CONTAINS },
        colorWhite: { value: null, matchMode: FilterMatchMode.CONTAINS },
        colorRose: { value: null, matchMode: FilterMatchMode.CONTAINS },
        visualInspectionClassification: { value: null, matchMode: FilterMatchMode.CONTAINS },
        intensity: { value: null, matchMode: FilterMatchMode.CONTAINS },
        olfactoryInspectionPersistence: { value: null, matchMode: FilterMatchMode.CONTAINS },
        quality: { value: null, matchMode: FilterMatchMode.CONTAINS },
        fruity: { value: null, matchMode: FilterMatchMode.CONTAINS },
        dryFruits: { value: null, matchMode: FilterMatchMode.CONTAINS },
        florals: { value: null, matchMode: FilterMatchMode.CONTAINS },
        vegetablesAndHerbs: { value: null, matchMode: FilterMatchMode.CONTAINS },
        minerals: { value: null, matchMode: FilterMatchMode.CONTAINS },
        spices: { value: null, matchMode: FilterMatchMode.CONTAINS },
        animals: { value: null, matchMode: FilterMatchMode.CONTAINS },
        empireumatics: { value: null, matchMode: FilterMatchMode.CONTAINS },
        wood: { value: null, matchMode: FilterMatchMode.CONTAINS },
        chemicals: { value: null, matchMode: FilterMatchMode.CONTAINS },
        lacteal: { value: null, matchMode: FilterMatchMode.CONTAINS },
        sweets: { value: null, matchMode: FilterMatchMode.CONTAINS },
        olfactoryInspectionClassification: { value: null, matchMode: FilterMatchMode.CONTAINS },
        body: { value: null, matchMode: FilterMatchMode.CONTAINS },
        sweetness: { value: null, matchMode: FilterMatchMode.CONTAINS },
        tannin: { value: null, matchMode: FilterMatchMode.CONTAINS },
        acidity: { value: null, matchMode: FilterMatchMode.CONTAINS },
        alcohol: { value: null, matchMode: FilterMatchMode.CONTAINS },
        gustatoryInspectionPersistence: { value: null, matchMode: FilterMatchMode.CONTAINS },
        maturity: { value: null, matchMode: FilterMatchMode.CONTAINS },
        typicality: { value: null, matchMode: FilterMatchMode.CONTAINS },
        gustatoryInspectionClassification: { value: null, matchMode: FilterMatchMode.CONTAINS },
        opinion: { value: null, matchMode: FilterMatchMode.CONTAINS },
        pointScale: { value: null, matchMode: FilterMatchMode.CONTAINS }
    });

    const columns = [
        { field: 'wineTasted', header: 'Vinho degustado' },
        { field: 'harvest', header: 'Safra' },
        { field: 'grapes', header: 'Uvas' },
        { field: 'country', header: 'País' },
        { field: 'region', header: 'Região' },
        { field: 'tastingType', header: 'Tipo de degustação' },
        { field: 'clarity', header: 'Clareza' },
        { field: 'brightness', header: 'Brilho' },
        { field: 'viscosity', header: 'Viscosidade' },
        { field: 'colorRed', header: 'Tinto' },
        { field: 'colorWhite', header: 'Branco' },
        { field: 'colorRose', header: 'Rosé' },
        { field: 'visualInspectionClassification', header: 'Classificação inspeção visual' },
        { field: 'intensity', header: 'Intensidade' },
        { field: 'olfactoryInspectionPersistence', header: 'Persistência olfativa' },
        { field: 'quality', header: 'Qualidade' },
        { field: 'fruity', header: 'Frutados' },
        { field: 'dryFruits', header: 'Frutas secas' },
        { field: 'florals', header: 'Florais' },
        { field: 'vegetablesAndHerbs', header: 'Vegetais e herbáceos' },
        { field: 'minerals', header: 'Minerais' },
        { field: 'spices', header: 'Especiarias' },
        { field: 'animals', header: 'Animais' },
        { field: 'empireumatics', header: 'Empireumáticos' },
        { field: 'wood', header: 'Madeira' },
        { field: 'chemicals', header: 'Químicos e etéreos' },
        { field: 'lacteal', header: 'Lácteos' },
        { field: 'sweets', header: 'Adocicados' },
        { field: 'olfactoryInspectionClassification', header: 'Classificação olfativa' },
        { field: 'body', header: 'Corpo' },
        { field: 'sweetness', header: 'Doçura' },
        { field: 'tannin', header: 'Tanino' },
        { field: 'acidity', header: 'Acidez' },
        { field: 'alcohol', header: 'Álcool' },
        { field: 'gustatoryInspectionPersistence', header: 'Persistência gustatória' },
        { field: 'maturity', header: 'Maturidade' },
        { field: 'typicality', header: 'Tipicidade' },
        { field: 'gustatoryInspectionClassification', header: 'Classificação gustatória' },
        { field: 'opinion', header: 'Opinião' },
        { field: 'pointScale', header: 'Escala de pontuação' }
    ]

    const onNewClick = () => {
        navigate('/tasting-card');
    };

    const onEditClick = () => {
        if (selectedTastingCards && selectedTastingCards.length === 1) {
            setEditingTastingCards(selectedTastingCards[0]);
            setVisibleEditDialog(true);
        } else {
            alert('Selecione uma ficha de degustação para editar.');
        }
    };

    const onDeleteClick = async () => {
        if (selectedTastingCards && selectedTastingCards.length > 0) {
            setVisibleDeleteDialog(true);
        } else {
            alert('Selecione uma ficha de degustação para excluir.');
        }
    };

    const confirmDeleteTastingCards = async () => {        
        try {
            if (selectedTastingCards.length === 1) {
                await deleteTastingCard(selectedTastingCards[0].id);
            } else if (selectedTastingCards.length > 1) {
                const tastingCardsId = selectedTastingCards.map(tastingCard => tastingCard.id);
                await deleteAllTastingCards(tastingCardsId);
            }
            
            setVisibleDeleteDialog(false);
            setSelectedTastingCards(null);
            await fetchTastingCards();
        } catch (error) {
            console.log(error);
        }
    };

    const saveTastingCard = async () => {
        try {
            await updateTastingCard(editingTastingCards.id, editingTastingCards);
            setVisibleEditDialog(false);
            setSelectedTastingCards(null);
            await fetchTastingCards();
            navigate('/tasting-list');
        } catch (error) {
            console.log(error);
        }
    };

    const exportCSV = () => {
        dt.current.exportCSV();
    };

    const onGlobalFilterChange = (e) => {
        const value = e.target.value;
        let _filters = { ...filters };
        _filters['global'].value = value;
        setFilters(_filters);
        setGlobalFilterValue(value);
    };

    const onColumnToggle = (event) => {
        let selectedColumns = event.value;
        let orderedSelectedColumns = columns.filter((col) => selectedColumns.some((sCol) => sCol.field === col.field));

        setVisibleColumns(orderedSelectedColumns);
    };

    const onSelectionChange = (e) => {
        setSelectedTastingCards(e.value);
    };

    const onSelectAllChange = (e) => {
        const _selectedTastingCards = e.checked ? tastingCards.map(cards => cards) : null;
        if (_selectedTastingCards) {
            setSelectedTastingCards(_selectedTastingCards);
        } else {
            setSelectedTastingCards(null);
        }
    };

    const leftToolbarTemplate = () => {
        return (
            <>
                <Dialog visible={visibleDeleteDialog} onHide={() => setVisibleDeleteDialog(false)} header="Excluir ficha de degustação" modal>
                    <div className="p-dialog-content">
                        <p>Deseja realmente excluir a ficha de degustação selecionada?</p>
                    </div>
                    <div className="flex flex-wrap gap-2 mt-4">
                        <Button label="Cancelar" icon="pi pi-times" className="p-button-danger" onClick={() => setVisibleDeleteDialog(false)} style={{ borderRadius: '20px' }}/>
                        <Button label="Confirmar" icon="pi pi-check" className="p-button-success" onClick={confirmDeleteTastingCards} style={{ borderRadius: '20px' }}/>
                    </div>
                </Dialog>

                <Dialog visible={visibleEditDialog} onHide={() => setVisibleEditDialog(false)} header="Editar ficha de degustação" modal>
                    <div className="p-fluid">
                        <div className="p-field">
                            <label htmlFor="tastingData">Data da degustação</label>
                            <Calendar id="tastingData" value={editingTastingCards?.tastingData} onChange={(e) => setEditingTastingCards({ ...editingTastingCards, tastingData: e.target.value })} dateFormat="dd/mm/yy" />
                        </div>
                        <div className="p-field">
                            <label htmlFor="wineTasted">Vinho degustado</label>
                            <InputText id="wineTasted" value={editingTastingCards?.wineTasted} onChange={(e) => setEditingTastingCards({ ...editingTastingCards, wineTasted: e.target.value })} />
                        </div>
                        <div className="p-field">
                            <label htmlFor="harvest">Safra</label>
                            <InputText id="harvest" value={editingTastingCards?.harvest} onChange={(e) => setEditingTastingCards({ ...editingTastingCards, harvest: e.target.value })} />
                        </div>
                        <div className="p-field">
                            <label htmlFor="grapes">Uvas</label>
                            <InputText id="grapes" value={editingTastingCards?.grapes} onChange={(e) => setEditingTastingCards({ ...editingTastingCards, grapes: e.target.value })} />
                        </div>
                        <div className="p-field">
                            <label htmlFor="country">País</label>
                            <InputText id="country" value={editingTastingCards?.country} onChange={(e) => setEditingTastingCards({ ...editingTastingCards, country: e.target.value })} />
                        </div>
                        <div className="p-field">
                            <label htmlFor="region">Região</label>
                            <InputText id="region" value={editingTastingCards?.region} onChange={(e) => setEditingTastingCards({ ...editingTastingCards, region: e.target.value })} />
                        </div>
                        <div className="p-field">
                            <label htmlFor="tastingType">Tipo de degustação</label>
                            <Dropdown id='tastingType' value={editingTastingCards?.tastingType || ''} options={enumTastingType} onChange={(e) => setEditingTastingCards({ ...editingTastingCards, tastingType: e.target.value })} placeholder='Selecione um tipo de degustação' />
                        </div>
                        <div className="p-field">
                            <label htmlFor="clarity">Clareza</label>
                            <Dropdown id='clarity' value={editingTastingCards?.clarity || ''} options={enumClarityType} onChange={(e) => setEditingTastingCards({ ...editingTastingCards, clarity: e.target.value })} placeholder='Selecione um tipo de clareza' />
                        </div>
                        <div className="p-field">
                            <label htmlFor="brightness">Brilho</label>
                            <Dropdown id='brightness' value={editingTastingCards?.brightness || ''} options={enumBrightnessType} onChange={(e) => setEditingTastingCards({ ...editingTastingCards, brightness: e.target.value })} placeholder='Selecione um tipo de brilho' />
                        </div>
                        <div className="p-field">
                            <label htmlFor="viscosity">Viscosidade</label>
                            <Dropdown id='viscosity' value={editingTastingCards?.viscosity || ''} options={enumViscosityType} onChange={(e) => setEditingTastingCards({ ...editingTastingCards, viscosity: e.target.value })} placeholder='Selecione um tipo de viscosidade' />
                        </div>
                        <div className="p-field">
                            <label htmlFor="colorRed">Tinto</label>
                            <Dropdown id='colorRed' value={editingTastingCards?.colorRed || ''} options={enumRedColorType} onChange={(e) => setEditingTastingCards({ ...editingTastingCards, colorRed: e.target.value })} placeholder='Selecione um tipo de tinto' />
                        </div>
                        <div className="p-field">
                            <label htmlFor="colorWhite">Branco</label>
                            <Dropdown id='colorWhite' value={editingTastingCards?.colorWhite || ''} options={enumWhiteColorType} onChange={(e) => setEditingTastingCards({ ...editingTastingCards, colorWhite: e.target.value })} placeholder='Selecione um tipo de branco' />
                        </div>
                        <div className="p-field">
                            <label htmlFor="colorRose">Rosé</label>
                            <Dropdown id='colorRose' value={editingTastingCards?.colorRose || ''} options={enumRoseColorType} onChange={(e) => setEditingTastingCards({ ...editingTastingCards, colorRose: e.target.value })} placeholder='Selecione um tipo de rosé' />
                        </div>
                        <div className="p-field">
                            <label htmlFor="visualInspectionClassification">Classificação inspeção visual</label>
                            <Dropdown id='visualInspectionClassification' value={editingTastingCards?.visualInspectionClassification || ''} options={enumClassificationType} onChange={(e) => setEditingTastingCards({ ...editingTastingCards, visualInspectionClassification: e.target.value })} placeholder='Selecione um tipo de classificação' />
                        </div>
                        <div className="p-field">
                            <label htmlFor="intensity">Intensidade</label>
                            <Dropdown id='intensity' value={editingTastingCards?.intensity || ''} options={enumIntensityType} onChange={(e) => setEditingTastingCards({ ...editingTastingCards, intensity: e.target.value })} placeholder='Selecione um tipo de intensidade' />
                        </div>
                        <div className="p-field">
                            <label htmlFor="olfactoryInspectionPersistence">Persistência olfativa</label>
                            <Dropdown id='olfactoryInspectionPersistence' value={editingTastingCards?.olfactoryInspectionPersistence || ''} options={enumPersistenceType} onChange={(e) => setEditingTastingCards({ ...editingTastingCards, olfactoryInspectionPersistence: e.target.value })} placeholder='Selecione um tipo de persistência' />
                        </div>
                        <div className="p-field">
                            <label htmlFor="quality">Qualidade</label>
                            <Dropdown id='quality' value={editingTastingCards?.quality || ''} options={enumQualityType} onChange={(e) => setEditingTastingCards({ ...editingTastingCards, quality: e.target.value })} placeholder='Selecione um tipo de qualidade' />
                        </div>
                        <div className="p-field">
                            <label htmlFor="fruity">Frutados</label>
                            <Dropdown id='fruity' value={editingTastingCards?.fruity || ''} options={enumFruityType} onChange={(e) => setEditingTastingCards({ ...editingTastingCards, fruity: e.target.value })} placeholder='Selecione um tipo de frutados' />
                        </div>
                        <div className="p-field">
                            <label htmlFor="dryFruits">Frutas secas</label>
                            <Dropdown id='dryFruits' value={editingTastingCards?.dryFruits || ''} options={enumDryFruitsType} onChange={(e) => setEditingTastingCards({ ...editingTastingCards, dryFruits: e.target.value })} placeholder='Selecione um tipo de frutas secas' />
                        </div>
                        <div className="p-field">
                            <label htmlFor="florals">Florais</label>
                            <Dropdown id='florals' value={editingTastingCards?.florals || ''} options={enumFloralsType} onChange={(e) => setEditingTastingCards({ ...editingTastingCards, florals: e.target.value })} placeholder='Selecione um tipo de florais' />
                        </div>
                        <div className="p-field">
                            <label htmlFor="vegetablesAndHerbs">Vegetais e herbáceos</label>
                            <Dropdown id='vegetablesAndHerbs' value={editingTastingCards?.vegetablesAndHerbs || ''} options={enumVegetablesAndHerbsType} onChange={(e) => setEditingTastingCards({ ...editingTastingCards, vegetablesAndHerbs: e.target.value })} placeholder='Selecione um tipo de vegetais e herbáceos' />
                        </div>
                        <div className="p-field">
                            <label htmlFor="minerals">Minerais</label>
                            <Dropdown id='minerals' value={editingTastingCards?.minerals || ''} options={enumMineralsType} onChange={(e) => setEditingTastingCards({ ...editingTastingCards, minerals: e.target.value })} placeholder='Selecione um tipo de minerais' />
                        </div>
                        <div className="p-field">
                            <label htmlFor="spices">Especiarias</label>
                            <Dropdown id='spices' value={editingTastingCards?.spices || ''} options={enumSpicesType} onChange={(e) => setEditingTastingCards({ ...editingTastingCards, spices: e.target.value })} placeholder='Selecione um tipo de especiarias' />
                        </div>
                        <div className="p-field">
                            <label htmlFor="animals">Animais</label>
                            <Dropdown id='animals' value={editingTastingCards?.animals || ''} options={enumAnimalsType} onChange={(e) => setEditingTastingCards({ ...editingTastingCards, animals: e.target.value })} placeholder='Selecione um tipo de animais' />
                        </div>
                        <div className="p-field">
                            <label htmlFor="empireumatics">Empireumáticos</label>
                            <Dropdown id='empireumatics' value={editingTastingCards?.empireumatics || ''} options={enumEmpireumaticsType} onChange={(e) => setEditingTastingCards({ ...editingTastingCards, empireumatics: e.target.value })} placeholder='Selecione um tipo de empireumáticos' />
                        </div>
                        <div className="p-field">
                            <label htmlFor="wood">Madeira</label>
                            <Dropdown id='wood' value={editingTastingCards?.wood || ''} options={enumWoodType} onChange={(e) => setEditingTastingCards({ ...editingTastingCards, wood: e.target.value })} placeholder='Selecione um tipo de madeira' />
                        </div>
                        <div className="p-field">
                            <label htmlFor="chemicals">Químicos e etéreos</label>
                            <Dropdown id='chemicals' value={editingTastingCards?.chemicals || ''} options={enumChemicalsAndEtherealType} onChange={(e) => setEditingTastingCards({ ...editingTastingCards, chemicals: e.target.value })} placeholder='Selecione um tipo de químicos e etéreos' />
                        </div>
                        <div className="p-field">
                            <label htmlFor="lacteal">Lácteos</label>
                            <Dropdown id='lacteal' value={editingTastingCards?.lacteal || ''} options={enumLactealType} onChange={(e) => setEditingTastingCards({ ...editingTastingCards, lacteal: e.target.value })} placeholder='Selecione um tipo de lácteos' />
                        </div>
                        <div className="p-field">
                            <label htmlFor="sweets">Adocicados</label>
                            <Dropdown id='sweets' value={editingTastingCards?.sweets || ''} options={enumSweetsType} onChange={(e) => setEditingTastingCards({ ...editingTastingCards, sweets: e.target.value })} placeholder='Selecione um tipo de adocicados' />
                        </div>
                        <div className="p-field">
                            <label htmlFor="olfactoryInspectionClassification">Classificação olfativa</label>
                            <Dropdown id='olfactoryInspectionClassification' value={editingTastingCards?.olfactoryInspectionClassification || ''} options={enumClassificationType} onChange={(e) => setEditingTastingCards({ ...editingTastingCards, olfactoryInspectionClassification: e.target.value })} placeholder='Selecione um tipo de classificação' />
                        </div>
                        <div className="p-field">
                            <label htmlFor="body">Corpo</label>
                            <Dropdown id='body' value={editingTastingCards?.body || ''} options={enumBodyType} onChange={(e) => setEditingTastingCards({ ...editingTastingCards, body: e.target.value })} placeholder='Selecione um tipo de corpo' />
                        </div>
                        <div className="p-field">
                            <label htmlFor="sweetness">Doçura</label>
                            <Dropdown id='sweetness' value={editingTastingCards?.sweetness || ''} options={enumSweetnessType} onChange={(e) => setEditingTastingCards({ ...editingTastingCards, sweetness: e.target.value })} placeholder='Selecione um tipo de doçura' />
                        </div>
                        <div className="p-field">
                            <label htmlFor="tannin">Tanino</label>
                            <Dropdown id='tannin' value={editingTastingCards?.tannin || ''} options={enumTanninType} onChange={(e) => setEditingTastingCards({ ...editingTastingCards, tannin: e.target.value })} placeholder='Selecione um tipo de tanino' />
                        </div>
                        <div className="p-field">
                            <label htmlFor="acidity">Acidez</label>
                            <Dropdown id='acidity' value={editingTastingCards?.acidity || ''} options={enumAcidityType} onChange={(e) => setEditingTastingCards({ ...editingTastingCards, acidity: e.target.value })} placeholder='Selecione um tipo de acidez' />
                        </div>
                        <div className="p-field">
                            <label htmlFor="alcohol">Álcool</label>
                            <Dropdown id='alcohol' value={editingTastingCards?.alcohol || ''} options={enumAlcoholType} onChange={(e) => setEditingTastingCards({ ...editingTastingCards, alcohol: e.target.value })} placeholder='Selecione um tipo de álcool' />
                        </div>
                        <div className="p-field">
                            <label htmlFor="gustatoryInspectionPersistence">Persistência gustatória</label>
                            <Dropdown id='gustatoryInspectionPersistence' value={editingTastingCards?.gustatoryInspectionPersistence || ''} options={enumPersistenceType} onChange={(e) => setEditingTastingCards({ ...editingTastingCards, gustatoryInspectionPersistence: e.target.value })} placeholder='Selecione um tipo de persistência' />
                        </div>
                        <div className="p-field">
                            <label htmlFor="maturity">Maturidade</label>
                            <Dropdown id='maturity' value={editingTastingCards?.maturity || ''} options={enumMaturityType} onChange={(e) => setEditingTastingCards({ ...editingTastingCards, maturity: e.target.value })} placeholder='Selecione um tipo de maturidade' />
                        </div>
                        <div className="p-field">
                            <label htmlFor="typicality">Tipicidade</label>
                            <Dropdown id='typicality' value={editingTastingCards?.typicality || ''} options={enumTypicalityType} onChange={(e) => setEditingTastingCards({ ...editingTastingCards, typicality: e.target.value })} placeholder='Selecione um tipo de tipicidade' />
                        </div>
                        <div className="p-field">
                            <label htmlFor="gustatoryInspectionClassification">Classificação gustatória</label>
                            <Dropdown id='gustatoryInspectionClassification' value={editingTastingCards?.gustatoryInspectionClassification || ''} options={enumClassificationType} onChange={(e) => setEditingTastingCards({ ...editingTastingCards, gustatoryInspectionClassification: e.target.value })} placeholder='Selecione um tipo de classificação' />
                            {/* <InputText id="gustatoryInspectionClassification" value={editingTastingCards?.gustatoryInspectionClassification} onChange={(e) => setEditingTastingCards({ ...editingTastingCards, gustatoryInspectionClassification: e.target.value })} /> */}
                        </div>
                        <div className="p-field">
                            <label htmlFor="opinion">Opinião</label>
                            <InputText id="opinion" value={editingTastingCards?.opinion} onChange={(e) => setEditingTastingCards({ ...editingTastingCards, opinion: e.target.value })} />
                        </div>
                        <div className="p-field">
                            <label htmlFor="pointScale">Escala de pontuação</label>
                            <Dropdown id='pointScale' value={editingTastingCards?.pointScale || ''} options={enumPointScale} onChange={(e) => setEditingTastingCards({ ...editingTastingCards, pointScale: e.target.value })} placeholder='Selecione uma escala de pontuação' />
                        </div>
                    </div>
                    <div className="flex flex-wrap gap-2 mt-4">
                        <Button label="Cancelar" icon="pi pi-times" className="p-button-danger" onClick={() => setVisibleEditDialog(false)} style={{ borderRadius: '20px' }}/>
                        <Button label="Salvar" icon="pi pi-check" className="p-button-success" onClick={saveTastingCard} style={{ borderRadius: '20px' }}/>
                    </div>
                </Dialog>
                <div className="flex flex-wrap gap-2">
                    <Button rounded label="Novo" icon="pi pi-plus" severity="success" onClick={onNewClick} raised style={{ borderRadius: '20px' }}/>
                    <Button rounded label="Editar" icon="pi pi-pencil" severity="secondary" onClick={onEditClick} disabled={!selectedTastingCards || selectedTastingCards.length !== 1} raised style={{ borderRadius: '20px' }}/>
                    <Button rounded label="Excluir" icon="pi pi-trash" severity="danger" onClick={onDeleteClick} disabled={!selectedTastingCards || selectedTastingCards.length === 0} raised style={{ borderRadius: '20px' }}/>
                </div>
            </>
        );
    };

    const rightToolbarTemplate = () => {
        return <Button rounded label="CSV" icon="pi pi-upload" className="p-button-help" onClick={exportCSV} raised style={{ borderRadius: '20px' }}/>;
    };

    const renderHeader = () => {
        return (
            <div className="flex justify-content-end">
                <MultiSelect data-testid="multiselect-component" value={visibleColumns} options={columns} optionLabel="header" onChange={onColumnToggle} className="w-full sm:w-20rem" display="chip" />
                <span className="p-input-icon-left">
                    <i className="pi pi-search" />
                    <InputText value={globalFilterValue} onChange={onGlobalFilterChange} placeholder="Keyword Search" />
                </span>
            </div>
        );
    };

    const header = renderHeader();

    const formatDate = (rowData) => {
        const dateParts = rowData.tastingData.split('-');
        const formattedDate = `${dateParts[2]}/${dateParts[1]}/${dateParts[0]}`;
        return formattedDate;
    };


    useEffect(() => {
        setLoading(false);
        setVisibleColumns(columns);
    }, []);

    return (
        <>
            <Card style={{ marginTop: 10 }} title="Fichas de Degustação">
                <Toolbar className="mb-4" start={leftToolbarTemplate} end={rightToolbarTemplate}></Toolbar>
                <DataTable
                    value={tastingCards}
                    paginator
                    rows={10}
                    rowsPerPageOptions={[10, 20, 30, 50]}
                    loading={loading}
                    filters={filters}
                    globalFilterFields={['tastingData', 'wineTasted', 'harvest', 'grapes', 'country', 'region', 'tastingType', 'clarity', 'brightness', 'viscosity', 'colorRed', 'colorWhite', 'colorRose', 'visualInspectionClassification', 'intensity', 'olfactoryInspectionPersistence', 'quality', 'fruity', 'dryFruits', 'florals', 'vegetablesAndHerbs', 'minerals', 'spices', 'animals', 'empireumatics', 'wood', 'chemicals', 'lacteal', 'sweets', 'olfactoryInspectionClassification', 'body', 'sweetness', 'tannin', 'acidity', 'alcohol', 'gustatoryInspectionPersistence', 'maturity', 'typicality', 'gustatoryInspectionClassification', 'opinion', 'pointScale']}
                    header={header}
                    resizableColumns
                    showGridlines
                    tableStyle={{ minWidth: '50rem' }}
                    emptyMessage="Nenhuma ficha de degustação encontrada."
                    selectionMode="multiple"
                    selection={selectedTastingCards}
                    onSelectionChange={onSelectionChange}
                    onSelectAll={onSelectAllChange}
                    ref={dt}
                >
                    <Column selectionMode='multiple' headerStyle={{ width: '3rm' }} />
                    <Column field="tastingData" header="Data da degustação" sortable body={formatDate} />
                    {visibleColumns.map((col) => (
                        <Column key={col.field} field={col.field} header={col.header} sortable filterField={col.field} />
                    ))}
                </DataTable>
            </Card>
        </>
    )
}

export default ListCardTastingComponent;
