import { useState } from 'react';
import { Card, Form } from 'react-bootstrap'
import Row from 'react-bootstrap/Row'
import Col from 'react-bootstrap/Col';
import EnumClarityType from '../../utils/enums/EnumClarityType';
import EnumBrightnessType from '../../utils/enums/EnumBrightnessType';
import EnumViscosityType from '../../utils/enums/EnumViscosityType';
import EnumRedColorType from '../../utils/enums/EnumRedColorType';
import EnumWhiteColorType from '../../utils/enums/EnumWhiteColorType';
import EnumRoseColorType from '../../utils/enums/EnumRoseColorType';
import EnumClassificationType from '../../utils/enums/EnumClassificationType';

const VisualInspection = () => {
    const clarityType = Object.values(EnumClarityType);
    const brightnessType = Object.values(EnumBrightnessType);
    const viscosityType = Object.values(EnumViscosityType);
    const redColorType = Object.values(EnumRedColorType);
    const whiteColorType = Object.values(EnumWhiteColorType);
    const roseColorType = Object.values(EnumRoseColorType);
    const classificationType = Object.values(EnumClassificationType);

    const [error, setError] = useState(false);
    const [redColorEnabled, setRedColorEnabled] = useState(true);
    const [whiteColorEnabled, setWhiteColorEnabled] = useState(true);
    const [roseColorEnabled, setRoseColorEnabled] = useState(true);

    const submitFormData = (e) => {
        e.preventDefault();
    };

    const handleRedColorSelect = (e) => {
        setRedColorEnabled(true);
        setWhiteColorEnabled(false);
        setRoseColorEnabled(false);
    };

    const handleWhiteColorSelect = (e) => {
        setRedColorEnabled(false);
        setWhiteColorEnabled(true);
        setRoseColorEnabled(false);
    };

    const handleRoseColorSelect = (e) => {
        setRedColorEnabled(false);
        setWhiteColorEnabled(false);
        setRoseColorEnabled(true);
    };

    return (
        <Card style={{ marginTop: 50 }}>
            <Card.Header as="h5">Ficha de degustação</Card.Header>
            <Card.Body>
                <Form onSubmit={submitFormData}>
                    <Row className="mb-3">
                        <Form.Group as={Row} className="mb-3">
                            <Form.Text>
                                <p><strong>Vinho: </strong>Vinho XYZ</p>
                                <h2>Cor e tonalidade</h2>
                            </Form.Text>
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
                            >
                                <option value='' disabled>Selecione o tipo de cor para vinho tinto</option>
                                {Object.values(EnumRedColorType).map((type, index) => (
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
                            >
                                <option value='' disabled>Selecione o tipo de cor para vinho branco</option>
                                {Object.values(EnumWhiteColorType).map((type, index) => (
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
                            >
                                <option value='' disabled>Selecione o tipo de cor para vinho rose</option>
                                {Object.values(EnumRoseColorType).map((type, index) => (
                                    <option key={index} value={type}>{type}</option>
                                ))}
                            </Form.Select>
                        </Form.Group>
                    </Row>
                    <Row className="mb-3">
                        <Form.Group as={Col} className='mb-3'>
                            <Form.Label>Como você classifica os aspectos visuais do vinho?</Form.Label>
                            <Form.Select
                                style={{ border: error ? '2px solid red' : '' }}
                                name='classificationType'
                                defaultValue=''
                            >
                                <option value='' disabled>Classificação</option>
                                {Object.values(EnumClassificationType).map((type, index) => (
                                    <option key={index} value={type}>{type}</option>
                                ))}
                            </Form.Select>
                        </Form.Group>
                    </Row>

                    <h2>Transparência, opacidade e viscosidade</h2>

                    <Row className="mb-3">
                        <Form.Group as={Col} className='mb-3'>
                            <Form.Label>Clareza</Form.Label>
                            <Form.Select
                                style={{ border: error ? '2px solid red' : '' }}
                                name='clarityType'
                                defaultValue=''
                            >
                                <option value='' disabled>Selecione o tipo de clareza</option>
                                {Object.values(EnumClarityType).map((type, index) => (
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
                            >
                                <option value='' disabled>Selecione o tipo de brilho</option>
                                {Object.values(EnumBrightnessType).map((type, index) => (
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
                            >
                                <option value='' disabled>Selecione o tipo de brilho</option>
                                {Object.values(EnumViscosityType).map((type, index) => (
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

export default VisualInspection