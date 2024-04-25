import React, { useState } from 'react';
import { Form, Button } from 'react-bootstrap'
import { Card } from 'primereact/card';
import Row from 'react-bootstrap/Row'
import Col from 'react-bootstrap/Col';
import EnumWineType from "../../utils/enums/EnumWineType";
import EnumWineClassification from '../../utils/enums/EnumWineClassification';
import useWineComponentHook from '../../hooks/wine/useWineComponentHook';

const WineRegistration = () => {
    const {
        name, setName,
        price, setPrice,
        purchaseLocation, setPurchaseLocation,
        purchaseDate, setPurchaseDate,
        wineType, setWineType,
        wineClassification, setWineClassification,
        alcoholContent, setAlcoholContent,
        volumeMl, setVolumeMl,
        grape, setGrape,
        winery, setWinery,
        serviceTemperature, setServiceTemperature,
        harvest, setHarvest,
        country, setCountry,
        guardTime, setGuardTime,
        region, setRegion,
        maturation, setMaturation,
        harmonization, setHarmonization,
        dthreg, setDthreg,
        userreg, setUserreg,
        dthalt, setDthalt,
        useralt, setUseralt,
        saveWine
    } = useWineComponentHook();

    const wineTypes = Object.values(EnumWineType);
    const wineClassifications = Object.values(EnumWineClassification);

    const [submitted, setSubmitted] = useState(false);

    const handleSubmit = (e) => {
        e.preventDefault();
        saveWine();
        setSubmitted(true);
        setName('');
        setPrice('');
        setPurchaseLocation('');
        setPurchaseDate('');
        setWineType('');
        setWineClassification('');
        setAlcoholContent('');
        setVolumeMl('');
        setGrape('');
        setWinery('');
        setServiceTemperature('');
        setHarvest('');
        setCountry('');
        setGuardTime('');
        setRegion('');
        setMaturation('');
        setHarmonization('');
    };

    return (
        <Card style={{ marginTop: 10 }} title="Cadastro de Vinho">
            <Form onSubmit={handleSubmit}>
                <Row className="mb-3">
                    <Form.Group as={Row} className="mb-3">
                        <Form.Label>Rótulo</Form.Label>
                        <Form.Control
                            name='name'
                            value={name}
                            type='text'
                            placeholder="Informe o rótulo do vinho"
                            onChange={(e) => setName(e.target.value)}
                            data-testid="form-control-name"
                        />
                    </Form.Group>
                </Row>

                <Row className="mb-3">
                    <Form.Group as={Col} className="mb-3">
                        <Form.Label>Preço</Form.Label>
                        <Form.Control
                            name='price'
                            value={price}
                            type='text'
                            placeholder="Informe o preço do vinho em R$"
                            onChange={(e) => setPrice(e.target.value)}
                        />
                    </Form.Group>
                    <Form.Group as={Col} className="mb-3">
                        <Form.Label>Local de compra</Form.Label>
                        <Form.Control
                            name='purchaseLocation'
                            value={purchaseLocation}
                            type='text'
                            placeholder="Informe o local de compra do vinho"
                            onChange={(e) => setPurchaseLocation(e.target.value)}
                        />
                    </Form.Group>
                </Row>

                <Row className="mb-3">
                    <Form.Group as={Col} className="mb-3">
                        <Form.Label>Data de compra</Form.Label>
                        <Form.Control
                            name='purchaseDate'
                            value={purchaseDate}
                            type='date'
                            placeholder="Informe a data de compra no formato dd/mm/aaaa"
                            onChange={(e) => setPurchaseDate(e.target.value)}
                        />
                    </Form.Group>
                    <Form.Group as={Col} className="mb-3">
                        <Form.Label>Tipo de vinho</Form.Label>
                        <Form.Select aria-label="Default select example"
                            name='wineType'
                            value={wineType}
                            onChange={(e) => setWineType(e.target.value)}
                        >
                            <option>Selectione o tipo de vinho</option>
                            {Object.values(EnumWineType).map((type, index) => (
                                <option key={index} value={type}>{type}</option>
                            ))}
                        </Form.Select>
                    </Form.Group>
                    <Form.Group as={Col} className="mb-3">
                        <Form.Label>Classificação do vinho</Form.Label>
                        <Form.Select aria-label="Default select example"
                            name='wineClassification'
                            value={wineClassification}
                            onChange={(e) => setWineClassification(e.target.value)}
                        >
                            <option>Selectione a classificação do vinho</option>
                            {Object.values(EnumWineClassification).map((type, index) => (
                                <option key={index} value={type}>{type}</option>
                            ))}
                        </Form.Select>
                    </Form.Group>
                </Row>

                <Row className="mb-3">
                    <Form.Group as={Col} className="mb-3">
                        <Form.Label>Graduação alcoólica</Form.Label>
                        <Form.Control
                            name='alcoholContent'
                            value={alcoholContent}
                            type='text'
                            placeholder="Informe a graduação alcoólica do vinho"
                            onChange={(e) => setAlcoholContent(e.target.value)}
                        />
                    </Form.Group>
                    <Form.Group as={Col} className="mb-3">
                        <Form.Label>Volume da garrafa (ml)</Form.Label>
                        <Form.Control
                            name='volumeMl'
                            value={volumeMl}
                            type='text'
                            placeholder="Informe o volume da garrafa em ml"
                            onChange={(e) => setVolumeMl(e.target.value)}
                        />
                    </Form.Group>
                    <Form.Group as={Col} className="mb-3">
                        <Form.Label>Uva</Form.Label>
                        <Form.Control
                            name='grape'
                            value={grape}
                            type='text'
                            placeholder="Informe a uva do vinho"
                            onChange={(e) => setGrape(e.target.value)}
                        />
                    </Form.Group>
                </Row>

                <Row className="mb-3">
                    <Form.Group as={Col} className="mb-3">
                        <Form.Label>Produtor</Form.Label>
                        <Form.Control
                            name='winery'
                            value={winery}
                            type='text'
                            placeholder="Informe o produtor do vinho"
                            onChange={(e) => setWinery(e.target.value)}
                        />
                    </Form.Group>
                    <Form.Group as={Col} className="mb-3">
                        <Form.Label>Temperatura de serviço</Form.Label>
                        <Form.Control
                            name='serviceTemperature'
                            value={serviceTemperature}
                            type='text'
                            placeholder="Informe a temperatura de serviço do vinho"
                            onChange={(e) => setServiceTemperature(e.target.value)}
                        />
                    </Form.Group>
                    <Form.Group as={Col} className="mb-3">
                        <Form.Label>Safra</Form.Label>
                        <Form.Control
                            name='harvest'
                            value={harvest}
                            type='text'
                            placeholder="Informe a safra do vinho"
                            onChange={(e) => setHarvest(e.target.value)}
                        />
                    </Form.Group>
                </Row>

                <Row className="mb-3">
                    <Form.Group as={Col} className="mb-3">
                        <Form.Label>País de origem</Form.Label>
                        <Form.Control
                            name='country'
                            value={country}
                            type='text'
                            placeholder="Informe o país de origem do vinho"
                            onChange={(e) => setCountry(e.target.value)}
                        />
                    </Form.Group>
                    <Form.Group as={Col} className="mb-3">
                        <Form.Label>Tempo de guarda</Form.Label>
                        <Form.Control
                            name='guardTime'
                            value={guardTime}
                            type='text'
                            placeholder="Informe o tempo de guarda do vinho"
                            onChange={(e) => setGuardTime(e.target.value)}
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
                    <Form.Group as={Col} className="mb-3">
                        <Form.Label>Maturação</Form.Label>
                        <Form.Control
                            name='maturation'
                            value={maturation}
                            type='text'
                            placeholder="Informe como se deu a maturação do vinho"
                            onChange={(e) => setMaturation(e.target.value)}
                        />
                    </Form.Group>
                </Row>

                <Form.Group as={Row} className="mb-3">
                    <Form.Label as={Row}>Harmonização</Form.Label>
                    <Form.Control
                        name='harmonization'
                        value={harmonization}
                        type='text'
                        placeholder="Informe como harmonizar o vinho"
                        onChange={(e) => setHarmonization(e.target.value)}
                    />
                </Form.Group>
                <Button variant="primary" type="submit" style={{ borderRadius: '20px' }}>Cadastrar</Button>
            </Form>
            
        </Card>
    )
}

export default WineRegistration