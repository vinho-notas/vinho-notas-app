import { useState } from 'react';
import { Card, Form } from 'react-bootstrap'
import Row from 'react-bootstrap/Row'
import Col from 'react-bootstrap/Col';

import EnumPointScale from '../../../utils/enums/EnumPointScale';

const GeneralValuation = () => {
    const pointScale = Object.values(EnumPointScale);

    const [error, setError] = useState(false);

    const submitFormData = (e) => {
        e.preventDefault();
    };

    return (
        <Card style={{ marginTop: 10 }}>
            <Card.Header as="h5">Avaliação geral</Card.Header>
            <Card.Body>
                <Form onSubmit={submitFormData}>
                    
                    <Row className="mb-3">
                        <Form.Group as={Col} className='mb-3'>
                            <Form.Label>Avaliação geral</Form.Label>
                            <Form.Select
                                style={{ border: error ? '2px solid red' : '' }}
                                name='pointScale'
                                defaultValue=''
                            >
                                <option value='' disabled>De forma geral, como você classifica o vinho?</option>
                                {Object.values(EnumPointScale).map((type, index) => (
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

export default GeneralValuation