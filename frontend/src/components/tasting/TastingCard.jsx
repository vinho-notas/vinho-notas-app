import { Card, Form, Button } from 'react-bootstrap'
import Row from 'react-bootstrap/Row'
import Col from 'react-bootstrap/Col';
import VisualInspection from '../tasting/forms/VisualInspection';
import OlfatoryInspection from '../tasting/forms/OlfactoryInspection';
import GustatoryInspection from '../tasting/forms/GustatoryInspection';
import GeneralValuation from './forms/GeneralValuation';

const TastingCard = () => {
    return (
        <Card style={{ marginTop: 50 }}>
            <Card.Header as="h5">Ficha de degustação</Card.Header>
            <Card.Body>
                <Form>
                    <Row className="mb-3">
                        <Form.Group as={Row} className="mb-3">
                            <Form.Label>Vinho</Form.Label>
                            <Form.Control
                                name='name'
                                type='text'
                                placeholder="Informe o rótulo do vinho"
                            />
                        </Form.Group>
                    </Row>

                    <Row className="mb-3">
                        <Form.Group as={Col} className="mb-3">
                            <Form.Label>Data da degustação</Form.Label>
                            <Form.Control
                                name='tastingDate'
                                type='date'
                                placeholder="Informe a data da degustação no formato dd/mm/aaaa"
                            />
                        </Form.Group>

                        <Form.Group as={Col} className="mb-3">
                            <Form.Label>Safra</Form.Label>
                            <Form.Control
                                name='harvest'
                                type='text'
                                placeholder="Informe a safra do vinho degustado"
                            />
                        </Form.Group>

                        <Form.Group as={Col} className="mb-3">
                            <Form.Label>Uvas</Form.Label>
                            <Form.Control
                                name='grapes'
                                type='text'
                                placeholder="Informe as uvas que compõe o vinho"
                            />
                        </Form.Group>
                    </Row>

                    <Row className="mb-3">
                        <Form.Group as={Col} className="mb-3">
                            <Form.Label>País</Form.Label>
                            <Form.Control
                                name='country'
                                type='text'
                                placeholder="Informe o país de origem do vinho"
                            />
                        </Form.Group>

                        <Form.Group as={Col} className="mb-3">
                            <Form.Label>Região</Form.Label>
                            <Form.Control
                                name='region'
                                type='text'
                                placeholder="Informe a região de origem do vinho"
                            />
                        </Form.Group>
                    </Row>

                    <Row className="mb-3">     
                        <Form.Group as={Col} className="mb-3">                            
                            <VisualInspection />
                            <OlfatoryInspection />
                            <GustatoryInspection />
                            <GeneralValuation />
                        </Form.Group>
                    </Row>

                    <Button variant="primary" type="submit">Cadastrar</Button>

                </Form>
            </Card.Body>
        </Card>
    )
}

export default TastingCard