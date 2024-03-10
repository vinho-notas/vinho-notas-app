import { useState } from 'react';
import { Card, Form } from 'react-bootstrap'
import Row from 'react-bootstrap/Row'
import Col from 'react-bootstrap/Col';

import EnumBodyType from '../../../utils/enums/EnumBodyType';
import EnumSweetnessType from '../../../utils/enums/EnumSweetnessType';
import EnumTanninType from '../../../utils/enums/EnumTanninType';
import EnumAcidityType from '../../../utils/enums/EnumAcidityType';
import EnumAlcoholType from '../../../utils/enums/EnumAlcoholType';
import EnumPersistenceType from '../../../utils/enums/EnumPersistenceType';
import EnumMaturityType from '../../../utils/enums/EnumMaturityType';
import EnumTypicalityType from '../../../utils/enums/EnumTypicalityType';
import EnumClassificationType from '../../../utils/enums/EnumClassificationType';

const GustatoryInspection = () => {

    const bodyType = Object.values(EnumBodyType);
    const sweetnessType = Object.values(EnumSweetnessType);
    const tanninType = Object.values(EnumTanninType);
    const acidityType = Object.values(EnumAcidityType);
    const alcoholType = Object.values(EnumAlcoholType);
    const persistenceType = Object.values(EnumPersistenceType);
    const maturityType = Object.values(EnumMaturityType);
    const typicalityType = Object.values(EnumTypicalityType);
    const classificationType = Object.values(EnumClassificationType);

    const [error, setError] = useState(false);

    const submitFormData = (e) => {
        e.preventDefault();
    };

    return (
        <Card style={{ marginTop: 10 }}>
            <Card.Header as="h5">Inspeção gustativa</Card.Header>
            <Card.Body>
                <Form onSubmit={submitFormData}>
                    
                    <Row className="mb-3">
                        <Form.Group as={Col} className='mb-3'>
                            <Form.Label>Corpo</Form.Label>
                            <Form.Select
                                style={{ border: error ? '2px solid red' : '' }}
                                name='bodyType'
                                defaultValue=''
                            >
                                <option value='' disabled>Selecione o corpo</option>
                                {Object.values(EnumBodyType).map((type, index) => (
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
                            >
                                <option value='' disabled>Selecione a doçura</option>
                                {Object.values(EnumSweetnessType).map((type, index) => (
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
                            >
                                <option value='' disabled>Selecione os taninos</option>
                                {Object.values(EnumTanninType).map((type, index) => (
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
                            >
                                <option value='' disabled>Selecione a acidez</option>
                                {Object.values(EnumAcidityType).map((type, index) => (
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
                            >
                                <option value='' disabled>Selecione o álcool</option>
                                {Object.values(EnumAlcoholType).map((type, index) => (
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
                            >
                                <option value='' disabled>Selecione a persistência</option>
                                {Object.values(EnumPersistenceType).map((type, index) => (
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
                            >
                                <option value='' disabled>Selecione a maturidade</option>
                                {Object.values(EnumMaturityType).map((type, index) => (
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
                            >
                                <option value='' disabled>Selecione a tipicidade</option>
                                {Object.values(EnumTypicalityType).map((type, index) => (
                                    <option key={index} value={type}>{type}</option>
                                ))}
                            </Form.Select>
                        </Form.Group>
                    </Row>

                    <Row className="mb-3">
                        <Form.Group as={Col} className='mb-3'>
                            <Form.Label>Classificação</Form.Label>
                            <Form.Select
                                style={{ border: error ? '2px solid red' : '' }}
                                name='classificationType'
                                defaultValue=''
                            >
                                <option value='' disabled>Como você classifica os aspectos gustativos do vinho?</option>
                                {Object.values(EnumClassificationType).map((type, index) => (
                                    <option key={index} value={type}>{type}</option>
                                ))}
                            </Form.Select>
                        </Form.Group>
                    </Row>

                </Form>
            </Card.Body>

        </Card>
    )
}

export default GustatoryInspection