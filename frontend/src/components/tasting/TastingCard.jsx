import React, { useState } from 'react';
import { Form, Button } from 'react-bootstrap'
import { Card } from 'primereact/card';
import Row from 'react-bootstrap/Row'
import Col from 'react-bootstrap/Col';
import EnumTastingType from '../../utils/enums/EnumTastingType';
import EnumClarityType from '../../utils/enums/EnumClarityType';
import EnumBrightnessType from '../../utils/enums/EnumBrightnessType';
import EnumViscosityType from '../../utils/enums/EnumViscosityType';
import EnumRedColorType from '../../utils/enums/EnumRedColorType';
import EnumWhiteColorType from '../../utils/enums/EnumWhiteColorType';
import EnumRoseColorType from '../../utils/enums/EnumRoseColorType';
import EnumClassificationType from '../../utils/enums/EnumClassificationType';
import EnumIntensityType from '../../utils/enums/EnumIntensityType';
import EnumPersistenceType from '../../utils/enums/EnumPersistenceType';
import EnumQualityType from '../../utils/enums/EnumQualityType';
import EnumFruityType from '../../utils/enums/EnumFruityType';
import EnumDryFruitsType from '../../utils/enums/EnumDryFruitsType';
import EnumFloralsType from '../../utils/enums/EnumFloralsType';
import EnumMineralsType from '../../utils/enums/EnumMineralsType';
import EnumVegetablesAndHerbsType from '../../utils/enums/EnumVegetablesAndHerbsType';
import EnumSpicesType from '../../utils/enums/EnumSpicesType';
import EnumAnimalsType from '../../utils/enums/EnumAnimalsType';
import EnumEmpireumaticsType from '../../utils/enums/EnumEmpireumaticsType';
import EnumWoodType from '../../utils/enums/EnumWoodType';
import EnumChemicalsAndEtherealType from '../../utils/enums/EnumChemicalsAndEtherealType';
import EnumLactealType from '../../utils/enums/EnumLactealType';
import EnumSweetsType from '../../utils/enums/EnumSweetsType ';
import EnumBodyType from '../../utils/enums/EnumBodyType';
import EnumSweetnessType from '../../utils/enums/EnumSweetnessType';
import EnumTanninType from '../../utils/enums/EnumTanninType';
import EnumAcidityType from '../../utils/enums/EnumAcidityType';
import EnumAlcoholType from '../../utils/enums/EnumAlcoholType';
import EnumMaturityType from '../../utils/enums/EnumMaturityType';
import EnumTypicalityType from '../../utils/enums/EnumTypicalityType';
import EnumPointScale from '../../utils/enums/EnumPointScale';
import useTastingCardComponentHook from '../../hooks/tasting/useTastingCardComponentHook';
import ListCardTastingComponent from './forms/ListTastingCardComponent';

const TastingCard = () => {
    const [error, setError] = useState(false);
    const [redColorEnabled, setRedColorEnabled] = useState(true);
    const [whiteColorEnabled, setWhiteColorEnabled] = useState(true);
    const [roseColorEnabled, setRoseColorEnabled] = useState(true);

    const {
        tastingType, setTastingType,
        wineTasted, setWineTasted,
        tastingData, setTastingData,
        harvest, setHarvest,
        grapes, setGrapes,
        country, setCountry,
        region, setRegion,
        clarity, setClarity,
        brightness, setBrightness,
        viscosity, setViscosity,
        colorRed, setColorRed,
        colorWhite, setColorWhite,
        colorRose, setColorRose,
        visualInspectionClassification, setVisualInspectionClassification,
        intensity, setIntensity,
        olfactoryInspectionPersistence, setOlfactoryInspectionPersistence,
        quality, setQuality,
        fruity, setFruity,
        dryFruits, setDryFruits,
        florals, setFlorals,
        vegetablesAndHerbs, setVegetablesAndHerbs,
        minerals, setMinerals,
        spices, setSpices,
        animals, setAnimals,
        empireumatics, setEmpireumatics,
        wood, setWood,
        chemicals, setChemicals,
        lacteal, setLacteal,
        sweets, setSweets,
        olfactoryInspectionClassification, setOlfactoryInspectionClassification,
        body, setBody,
        sweetness, setSweetness,
        tannin, setTannin,
        acidity, setAcidity,
        alcohol, setAlcohol,
        gustatoryInspectionPersistence, setGustatoryInspectionPersistence,
        maturity, setMaturity,
        typicality, setTypicality,
        gustatoryInspectionClassification, setGustatoryInspectionClassification,
        opinion, setOpinion,
        pointScale, setPointScale,
        saveTastingCard
    } = useTastingCardComponentHook();

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

    const [submitted, setSubmitted] = useState(false);

    const handleSubmit = (e) => {
        e.preventDefault();
        saveTastingCard();
        setSubmitted(true);
        setTastingType('');
        setWineTasted('');
        setTastingData('');
        setHarvest('');
        setGrapes('');
        setCountry('');
        setRegion('');
        setClarity('');
        setBrightness('');
        setViscosity('');
        setColorRed('');
        setColorWhite('');
        setColorRose('');
        setVisualInspectionClassification('');
        setIntensity('');
        setOlfactoryInspectionPersistence('');
        setQuality('');
        setFruity('');
        setDryFruits('');
        setFlorals('');
        setVegetablesAndHerbs('');
        setMinerals('');
        setSpices('');
        setAnimals('');
        setEmpireumatics('');
        setWood('');
        setChemicals('');
        setLacteal('');
        setSweets('');
        setOlfactoryInspectionClassification('');
        setBody('');
        setSweetness('');
        setTannin('');
        setAcidity('');
        setAlcohol('');
        setGustatoryInspectionPersistence('');
        setMaturity('');
        setTypicality('');
        setGustatoryInspectionClassification('');
        setOpinion('');
        setPointScale('');
    };

    const handleRedColorSelect = (e) => {
        setRedColorEnabled(true);
        setWhiteColorEnabled(false);
        setRoseColorEnabled(false);
        setColorRed(e.target.value)
    };

    const handleWhiteColorSelect = (e) => {
        setRedColorEnabled(false);
        setWhiteColorEnabled(true);
        setRoseColorEnabled(false);
        setColorWhite(e.target.value)
    };

    const handleRoseColorSelect = (e) => {
        setRedColorEnabled(false);
        setWhiteColorEnabled(false);
        setRoseColorEnabled(true);
        setColorRose(e.target.value)
    };

    return (
        <>
            <Card style={{ marginTop: 10 }} title="Ficha de degustação">
                <Form onSubmit={handleSubmit}>
                    <Row className="mb-3">
                        <Form.Group as={Row} className="mb-3">
                            <Form.Label>Vinho</Form.Label>
                            <Form.Control
                                name='wineTasted'
                                value={wineTasted}
                                type='text'
                                placeholder="Informe o rótulo do vinho"
                                onChange={(e) => setWineTasted(e.target.value)}
                            />
                        </Form.Group>
                        <Form.Group as={Col} className="mb-3">
                            <Form.Label>Tipo de degustação</Form.Label>
                            <Form.Select
                                style={{ border: error ? '2px solid red' : '' }}
                                name='tastingType'
                                defaultValue=''
                                value={tastingType}
                                onChange={(e) => setTastingType(e.target.value)}
                            >
                                <option value='' disabled>Selecione o tipo de degustação</option>
                                {enumTastingType.map((type, index) => (
                                    <option key={index} value={type}>{type}</option>
                                ))}
                            </Form.Select>
                        </Form.Group>
                    </Row>
                    <Row className="mb-3">
                        <Form.Group as={Col} className="mb-3">
                            <Form.Label>Data da degustação</Form.Label>
                            <Form.Control
                                name='tastingDate'
                                value={tastingData}
                                type='date'
                                placeholder="Informe a data da degustação no formato dd/mm/aaaa"
                                onChange={(e) => setTastingData(e.target.value)}
                            />
                        </Form.Group>
                        <Form.Group as={Col} className="mb-3">
                            <Form.Label>Safra</Form.Label>
                            <Form.Control
                                name='harvest'
                                value={harvest}
                                type='text'
                                placeholder="Informe a safra do vinho degustado"
                                onChange={(e) => setHarvest(e.target.value)}
                            />
                        </Form.Group>
                        <Form.Group as={Col} className="mb-3">
                            <Form.Label>Uvas</Form.Label>
                            <Form.Control
                                name='grapes'
                                value={grapes}
                                type='text'
                                placeholder="Informe as uvas que compõe o vinho"
                                onChange={(e) => setGrapes(e.target.value)}
                            />
                        </Form.Group>
                    </Row>
                    <Row className="mb-3">
                        <Form.Group as={Col} className="mb-3">
                            <Form.Label>País</Form.Label>
                            <Form.Control
                                name='country'
                                value={country}
                                type='text'
                                placeholder="Informe o país de origem do vinho"
                                onChange={(e) => setCountry(e.target.value)}
                            />
                        </Form.Group>
                        <Form.Group as={Col} className="mb-3">
                            <Form.Label>Região</Form.Label>
                            <Form.Control
                                name='region'
                                value={region}
                                type='text'
                                placeholder="Informe a região de origem do vinho"
                                onChange={(e) => setRegion(e.target.value)}
                            />
                        </Form.Group>
                    </Row>
                    <Row className="mb-3">
                        <Form.Group as={Row} className="mb-3">
                            <Form.Text>
                                <h5>Aspectos visuais do vinho</h5>
                            </Form.Text>
                        </Form.Group>
                    </Row>
                    <Row className="mb-3">
                        <Form.Group as={Col} className='mb-3'>
                            <Form.Label>Clareza</Form.Label>
                            <Form.Select
                                style={{ border: error ? '2px solid red' : '' }}
                                name='clarityType'
                                defaultValue=''
                                value={clarity}
                                onChange={(e) => setClarity(e.target.value)}
                            >
                                <option value='' disabled>Selecione o tipo de clareza</option>
                                {Object.values(enumClarityType).map((type, index) => (
                                    <option key={index} value={type}>{type}</option>
                                ))}
                            </Form.Select>
                        </Form.Group>
                        <Form.Group as={Col} className='mb-3'>
                            <Form.Label>Brilho</Form.Label>
                            <Form.Select
                                style={{ border: error ? '2px solid red' : '' }}
                                name='brightnessType'
                                defaultValue=''
                                value={brightness}
                                onChange={(e) => setBrightness(e.target.value)}
                            >
                                <option value='' disabled>Selecione o tipo de brilho</option>
                                {Object.values(enumBrightnessType).map((type, index) => (
                                    <option key={index} value={type}>{type}</option>
                                ))}
                            </Form.Select>
                        </Form.Group>
                        <Form.Group as={Col} className='mb-3'>
                            <Form.Label>Viscosidade</Form.Label>
                            <Form.Select
                                style={{ border: error ? '2px solid red' : '' }}
                                name='viscosityType'
                                defaultValue=''
                                value={viscosity}
                                onChange={(e) => setViscosity(e.target.value)}
                            >
                                <option value='' disabled>Selecione o tipo de brilho</option>
                                {Object.values(enumViscosityType).map((type, index) => (
                                    <option key={index} value={type}>{type}</option>
                                ))}
                            </Form.Select>
                        </Form.Group>
                    </Row>
                    <Row className="mb-3">
                        <Form.Group as={Col} className='mb-3'>
                            <Form.Label>Cor Tinto</Form.Label>
                            <Form.Select
                                style={{ border: error ? '2px solid red' : '' }}
                                name='redColorType'
                                defaultValue=''
                                onChange={handleRedColorSelect}
                                disabled={!redColorEnabled}
                                value={colorRed}
                            >
                                <option value='' disabled>Selecione o tipo de cor para vinho tinto</option>
                                {Object.values(enumRedColorType).map((type, index) => (
                                    <option key={index} value={type}>{type}</option>
                                ))}
                            </Form.Select>
                        </Form.Group>
                        <Form.Group as={Col} className='mb-3'>
                            <Form.Label>Cor Branco</Form.Label>
                            <Form.Select
                                style={{ border: error ? '2px solid red' : '' }}
                                name='whiteColorType'
                                defaultValue=''
                                onChange={handleWhiteColorSelect}
                                disabled={!whiteColorEnabled}
                                value={colorWhite}
                            >
                                <option value='' disabled>Selecione o tipo de cor para vinho branco</option>
                                {Object.values(enumWhiteColorType).map((type, index) => (
                                    <option key={index} value={type}>{type}</option>
                                ))}
                            </Form.Select>
                        </Form.Group>
                        <Form.Group as={Col} className='mb-3'>
                            <Form.Label>Cor Rose</Form.Label>
                            <Form.Select
                                style={{ border: error ? '2px solid red' : '' }}
                                name='roseColorType'
                                defaultValue=''
                                onChange={handleRoseColorSelect}
                                disabled={!roseColorEnabled}
                                value={colorRose}
                            >
                                <option value='' disabled>Selecione o tipo de cor para vinho rose</option>
                                {Object.values(enumRoseColorType).map((type, index) => (
                                    <option key={index} value={type}>{type}</option>
                                ))}
                            </Form.Select>
                        </Form.Group>
                    </Row>
                    <Row className="mb-3">
                        <Form.Group as={Col} className='mb-3'>
                            <Form.Label>Como você classifica a qualidade dos aspectos visuais do vinho?</Form.Label>
                            <Form.Select
                                style={{ border: error ? '2px solid red' : '' }}
                                name='classificationType'
                                defaultValue=''
                                value={visualInspectionClassification}
                                onChange={(e) => setVisualInspectionClassification(e.target.value)}
                            >
                                <option value='' disabled>Classificação dos aspectos visuais do vinho</option>
                                {Object.values(enumClassificationType).map((type, index) => (
                                    <option key={index} value={type}>{type}</option>
                                ))}
                            </Form.Select>
                        </Form.Group>
                    </Row>
                    <Row className="mb-3">
                        <Form.Group as={Row} className="mb-3">
                            <Form.Text>
                                <h5>Aspectos olfativos do vinho</h5>
                            </Form.Text>
                        </Form.Group>
                    </Row>
                    <Row className="mb-3">
                        <Form.Group as={Col} className='mb-3'>
                            <Form.Label>Intensidade</Form.Label>
                            <Form.Select
                                style={{ border: error ? '2px solid red' : '' }}
                                name='intensityType'
                                defaultValue=''
                                value={intensity}
                                onChange={(e) => setIntensity(e.target.value)}
                            >
                                <option value='' disabled>Selecione a intensidade olfativa</option>
                                {Object.values(enumIntensityType).map((type, index) => (
                                    <option key={index} value={type}>{type}</option>
                                ))}
                            </Form.Select>
                        </Form.Group>
                        <Form.Group as={Col} className='mb-3'>
                            <Form.Label>Persistência</Form.Label>
                            <Form.Select
                                style={{ border: error ? '2px solid red' : '' }}
                                name='persistenceType'
                                defaultValue=''
                                value={olfactoryInspectionPersistence}
                                onChange={(e) => setOlfactoryInspectionPersistence(e.target.value)}
                            >
                                <option value='' disabled>Selecione a persistência olfativa</option>
                                {Object.values(enumPersistenceType).map((type, index) => (
                                    <option key={index} value={type}>{type}</option>
                                ))}
                            </Form.Select>
                        </Form.Group>
                        <Form.Group as={Col} className='mb-3'>
                            <Form.Label>Qualidade</Form.Label>
                            <Form.Select
                                style={{ border: error ? '2px solid red' : '' }}
                                name='qualityType'
                                defaultValue=''
                                value={quality}
                                onChange={(e) => setQuality(e.target.value)}
                            >
                                <option value='' disabled>Selecione a qualidade olfativa</option>
                                {Object.values(enumQualityType).map((type, index) => (
                                    <option key={index} value={type}>{type}</option>
                                ))}
                            </Form.Select>
                        </Form.Group>
                    </Row>
                    <Row className="mb-3">
                        <Form.Group as={Row} className="mb-3">
                            <Form.Text>
                                <h5>Aromas</h5>
                            </Form.Text>
                        </Form.Group>
                    </Row>
                    <Row className="mb-3">
                        <Form.Group as={Col} className='mb-3'>
                            <Form.Label>Frutados</Form.Label>
                            <Form.Select
                                style={{ border: error ? '2px solid red' : '' }}
                                name='fruitType'
                                defaultValue=''
                                value={fruity}
                                onChange={(e) => setFruity(e.target.value)}
                            >
                                <option value='' disabled>Selecione o tipo de aroma</option>
                                {Object.values(enumFruityType).map((type, index) => (
                                    <option key={index} value={type}>{type}</option>
                                ))}
                            </Form.Select>
                        </Form.Group>
                        <Form.Group as={Col} className='mb-3'>
                            <Form.Label>Frutas secas</Form.Label>
                            <Form.Select
                                style={{ border: error ? '2px solid red' : '' }}
                                name='dryFruitType'
                                defaultValue=''
                                value={dryFruits}
                                onChange={(e) => setDryFruits(e.target.value)}
                            >
                                <option value='' disabled>Selecione o tipo de aroma</option>
                                {Object.values(enumDryFruitsType).map((type, index) => (
                                    <option key={index} value={type}>{type}</option>
                                ))}
                            </Form.Select>
                        </Form.Group>
                        <Form.Group as={Col} className='mb-3'>
                            <Form.Label>Florais</Form.Label>
                            <Form.Select
                                style={{ border: error ? '2px solid red' : '' }}
                                name='floralsType'
                                defaultValue=''
                                value={florals}
                                onChange={(e) => setFlorals(e.target.value)}
                            >
                                <option value='' disabled>Selecione o tipo de aroma</option>
                                {Object.values(enumFloralsType).map((type, index) => (
                                    <option key={index} value={type}>{type}</option>
                                ))}
                            </Form.Select>
                        </Form.Group>
                        <Form.Group as={Col} className='mb-3'>
                            <Form.Label>Aromas de Vegetais e Ervas</Form.Label>
                            <Form.Select
                                style={{ border: error ? '2px solid red' : '' }}
                                name='vegetablesAndHerbsType'
                                defaultValue=''
                                value={vegetablesAndHerbs}
                                onChange={(e) => setVegetablesAndHerbs(e.target.value)}
                            >
                                <option value='' disabled>Selecione o tipo de aroma</option>
                                {Object.values(enumVegetablesAndHerbsType).map((type, index) => (
                                    <option key={index} value={type}>{type}</option>
                                ))}
                            </Form.Select>
                        </Form.Group>
                    </Row>
                    <Row className="mb-3">
                        <Form.Group as={Col} className='mb-3'>
                            <Form.Label>Minerais</Form.Label>
                            <Form.Select
                                style={{ border: error ? '2px solid red' : '' }}
                                name='mineralsType'
                                defaultValue=''
                                value={minerals}
                                onChange={(e) => setMinerals(e.target.value)}
                            >
                                <option value='' disabled>Selecione o tipo de aroma</option>
                                {Object.values(enumMineralsType).map((type, index) => (
                                    <option key={index} value={type}>{type}</option>
                                ))}
                            </Form.Select>
                        </Form.Group>
                        <Form.Group as={Col} className='mb-3'>
                            <Form.Label>Especiarias</Form.Label>
                            <Form.Select
                                style={{ border: error ? '2px solid red' : '' }}
                                name='spicesType'
                                defaultValue=''
                                value={spices}
                                onChange={(e) => setSpices(e.target.value)}
                            >
                                <option value='' disabled>Selecione o tipo de aroma</option>
                                {Object.values(enumSpicesType).map((type, index) => (
                                    <option key={index} value={type}>{type}</option>
                                ))}
                            </Form.Select>
                        </Form.Group>
                        <Form.Group as={Col} className='mb-3'>
                            <Form.Label>Animais</Form.Label>
                            <Form.Select
                                style={{ border: error ? '2px solid red' : '' }}
                                name='animalsType'
                                defaultValue=''
                                value={animals}
                                onChange={(e) => setAnimals(e.target.value)}
                            >
                                <option value='' disabled>Selecione o tipo de aroma</option>
                                {Object.values(enumAnimalsType).map((type, index) => (
                                    <option key={index} value={type}>{type}</option>
                                ))}
                            </Form.Select>
                        </Form.Group>
                        <Form.Group as={Col} className='mb-3'>
                            <Form.Label>Empireumáticos</Form.Label>
                            <Form.Select
                                style={{ border: error ? '2px solid red' : '' }}
                                name='empireumaticsType'
                                defaultValue=''
                                value={empireumatics}
                                onChange={(e) => setEmpireumatics(e.target.value)}
                            >
                                <option value='' disabled>Selecione o tipo de aroma</option>
                                {Object.values(enumEmpireumaticsType).map((type, index) => (
                                    <option key={index} value={type}>{type}</option>
                                ))}
                            </Form.Select>
                        </Form.Group>
                    </Row>
                    <Row className="mb-3">
                        <Form.Group as={Col} className='mb-3'>
                            <Form.Label>Madeira</Form.Label>
                            <Form.Select
                                style={{ border: error ? '2px solid red' : '' }}
                                name='woodType'
                                defaultValue=''
                                value={wood}
                                onChange={(e) => setWood(e.target.value)}
                            >
                                <option value='' disabled>Selecione o tipo de aroma</option>
                                {Object.values(enumWoodType).map((type, index) => (
                                    <option key={index} value={type}>{type}</option>
                                ))}
                            </Form.Select>
                        </Form.Group>
                        <Form.Group as={Col} className='mb-3'>
                            <Form.Label>Químicos e etéreos</Form.Label>
                            <Form.Select
                                style={{ border: error ? '2px solid red' : '' }}
                                name='chemicalsAndEtherealType'
                                defaultValue=''
                                value={chemicals}
                                onChange={(e) => setChemicals(e.target.value)}
                            >
                                <option value='' disabled>Selecione o tipo de aroma</option>
                                {Object.values(enumChemicalsAndEtherealType).map((type, index) => (
                                    <option key={index} value={type}>{type}</option>
                                ))}
                            </Form.Select>
                        </Form.Group>
                        <Form.Group as={Col} className='mb-3'>
                            <Form.Label>Lácteos</Form.Label>
                            <Form.Select
                                style={{ border: error ? '2px solid red' : '' }}
                                name='lactealType'
                                defaultValue=''
                                value={lacteal}
                                onChange={(e) => setLacteal(e.target.value)}
                            >
                                <option value='' disabled>Selecione o tipo de aroma</option>
                                {Object.values(enumLactealType).map((type, index) => (
                                    <option key={index} value={type}>{type}</option>
                                ))}
                            </Form.Select>
                        </Form.Group>
                        <Form.Group as={Col} className='mb-3'>
                            <Form.Label>Adocicados</Form.Label>
                            <Form.Select
                                style={{ border: error ? '2px solid red' : '' }}
                                name='sweetsType'
                                defaultValue=''
                                value={sweets}
                                onChange={(e) => setSweets(e.target.value)}
                            >
                                <option value='' disabled>Selecione o tipo de aroma</option>
                                {Object.values(enumSweetsType).map((type, index) => (
                                    <option key={index} value={type}>{type}</option>
                                ))}
                            </Form.Select>
                        </Form.Group>
                    </Row>
                    <Row className="mb-3">
                        <Form.Group as={Col} className='mb-3'>
                            <Form.Label>Como você classifica a qualidade dos aspectos olfativos do vinho?</Form.Label>
                            <Form.Select
                                style={{ border: error ? '2px solid red' : '' }}
                                name='classificationType'
                                defaultValue=''
                                value={olfactoryInspectionClassification}
                                onChange={(e) => setOlfactoryInspectionClassification(e.target.value)}
                            >
                                <option value='' disabled>Classificação dos aspectos olfativos do vinho</option>
                                {Object.values(enumClassificationType).map((type, index) => (
                                    <option key={index} value={type}>{type}</option>
                                ))}
                            </Form.Select>
                        </Form.Group>
                    </Row>
                    <Row className="mb-3">
                        <Form.Group as={Row} className="mb-3">
                            <Form.Text>
                                <h5>Aspectos gustativos do vinho</h5>
                            </Form.Text>
                        </Form.Group>
                    </Row>
                    <Row className="mb-3">
                        <Form.Group as={Col} className='mb-3'>
                            <Form.Label>Corpo</Form.Label>
                            <Form.Select
                                style={{ border: error ? '2px solid red' : '' }}
                                name='bodyType'
                                defaultValue=''
                                value={body}
                                onChange={(e) => setBody(e.target.value)}
                            >
                                <option value='' disabled>Selecione o corpo</option>
                                {Object.values(enumBodyType).map((type, index) => (
                                    <option key={index} value={type}>{type}</option>
                                ))}
                            </Form.Select>
                        </Form.Group>
                        <Form.Group as={Col} className='mb-3'>
                            <Form.Label>Doçura</Form.Label>
                            <Form.Select
                                style={{ border: error ? '2px solid red' : '' }}
                                name='sweetnessType'
                                defaultValue=''
                                value={sweetness}
                                onChange={(e) => setSweetness(e.target.value)}
                            >
                                <option value='' disabled>Selecione a doçura</option>
                                {Object.values(enumSweetnessType).map((type, index) => (
                                    <option key={index} value={type}>{type}</option>
                                ))}
                            </Form.Select>
                        </Form.Group>
                        <Form.Group as={Col} className='mb-3'>
                            <Form.Label>Taninos</Form.Label>
                            <Form.Select
                                style={{ border: error ? '2px solid red' : '' }}
                                name='tanninType'
                                defaultValue=''
                                value={tannin}
                                onChange={(e) => setTannin(e.target.value)}
                            >
                                <option value='' disabled>Selecione os taninos</option>
                                {Object.values(enumTanninType).map((type, index) => (
                                    <option key={index} value={type}>{type}</option>
                                ))}
                            </Form.Select>
                        </Form.Group>
                        <Form.Group as={Col} className='mb-3'>
                            <Form.Label>Acidez</Form.Label>
                            <Form.Select
                                style={{ border: error ? '2px solid red' : '' }}
                                name='acidityType'
                                defaultValue=''
                                value={acidity}
                                onChange={(e) => setAcidity(e.target.value)}
                            >
                                <option value='' disabled>Selecione a acidez</option>
                                {Object.values(enumAcidityType).map((type, index) => (
                                    <option key={index} value={type}>{type}</option>
                                ))}
                            </Form.Select>
                        </Form.Group>
                    </Row>
                    <Row className="mb-3">
                        <Form.Group as={Col} className='mb-3'>
                            <Form.Label>Álcool</Form.Label>
                            <Form.Select
                                style={{ border: error ? '2px solid red' : '' }}
                                name='alcoholType'
                                defaultValue=''
                                value={alcohol}
                                onChange={(e) => setAlcohol(e.target.value)}
                            >
                                <option value='' disabled>Selecione o álcool</option>
                                {Object.values(enumAlcoholType).map((type, index) => (
                                    <option key={index} value={type}>{type}</option>
                                ))}
                            </Form.Select>
                        </Form.Group>
                        <Form.Group as={Col} className='mb-3'>
                            <Form.Label>Persistência</Form.Label>
                            <Form.Select
                                style={{ border: error ? '2px solid red' : '' }}
                                name='persistenceType'
                                defaultValue=''
                                value={gustatoryInspectionPersistence}
                                onChange={(e) => setGustatoryInspectionPersistence(e.target.value)}
                            >
                                <option value='' disabled>Selecione a persistência</option>
                                {Object.values(enumPersistenceType).map((type, index) => (
                                    <option key={index} value={type}>{type}</option>
                                ))}
                            </Form.Select>
                        </Form.Group>
                        <Form.Group as={Col} className='mb-3'>
                            <Form.Label>Maturidade</Form.Label>
                            <Form.Select
                                style={{ border: error ? '2px solid red' : '' }}
                                name='maturityType'
                                defaultValue=''
                                value={maturity}
                                onChange={(e) => setMaturity(e.target.value)}
                            >
                                <option value='' disabled>Selecione a maturidade</option>
                                {Object.values(enumMaturityType).map((type, index) => (
                                    <option key={index} value={type}>{type}</option>
                                ))}
                            </Form.Select>
                        </Form.Group>
                        <Form.Group as={Col} className='mb-3'>
                            <Form.Label>Tipicidade</Form.Label>
                            <Form.Select
                                style={{ border: error ? '2px solid red' : '' }}
                                name='typicalityType'
                                defaultValue=''
                                value={typicality}
                                onChange={(e) => setTypicality(e.target.value)}
                            >
                                <option value='' disabled>Selecione a tipicidade</option>
                                {Object.values(enumTypicalityType).map((type, index) => (
                                    <option key={index} value={type}>{type}</option>
                                ))}
                            </Form.Select>
                        </Form.Group>
                    </Row>
                    <Row className="mb-3">
                        <Form.Group as={Col} className='mb-3'>
                            <Form.Label>Como você classifica a qualidade dos aspectos gustativos do vinho?</Form.Label>
                            <Form.Select
                                style={{ border: error ? '2px solid red' : '' }}
                                name='classificationType'
                                defaultValue=''
                                value={gustatoryInspectionClassification}
                                onChange={(e) => setGustatoryInspectionClassification(e.target.value)}
                            >
                                <option value='' disabled>Classificação dos aspectos gustativos do vinho</option>
                                {Object.values(enumClassificationType).map((type, index) => (
                                    <option key={index} value={type}>{type}</option>
                                ))}
                            </Form.Select>
                        </Form.Group>
                    </Row>
                    <Row className="mb-3">
                        <Form.Group as={Col} className='mb-3'>
                            <Form.Label>Opinião sobre o vinho</Form.Label>
                            <Form.Control
                                name='opinion'
                                value={opinion}
                                type='text'
                                placeholder="Informe sua opinião sobre o vinho degustado"
                                onChange={(e) => setOpinion(e.target.value)}
                            />
                        </Form.Group>
                    </Row>
                    <Row className="mb-3">
                        <Form.Group as={Col} className='mb-3'>
                            <Form.Label>De forma geral, como você classifica o vinho?</Form.Label>
                            <Form.Select
                                style={{ border: error ? '2px solid red' : '' }}
                                name='pointScale'
                                defaultValue=''
                                value={pointScale}
                                onChange={(e) => setPointScale(e.target.value)}
                            >
                                <option value='' disabled>Avaliação geral</option>
                                {Object.values(enumPointScale).map((type, index) => (
                                    <option key={index} value={type}>{type}</option>
                                ))}
                            </Form.Select>
                        </Form.Group>
                    </Row>
                    <Button variant="primary" type="submit" style={{ borderRadius: '20px' }}>Cadastrar</Button>
                </Form>
            </Card>
            <ListCardTastingComponent />
        </>
    )
}

export default TastingCard