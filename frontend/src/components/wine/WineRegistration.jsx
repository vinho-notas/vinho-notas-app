import { Form, Card, Button } from 'react-bootstrap'
import Row from 'react-bootstrap/Row'
import Col from 'react-bootstrap/Col';
import EnumWineType from "../../utils/enums/EnumWineType";
import EnumWineClassification from '../../utils/enums/EnumWineClassification';

const WineRegistration = () => {
    const wineType = Object.values(EnumWineType);
    const wineClassification = Object.values(EnumWineClassification);

    return (
        <Card style={{ marginTop: 100 }}>
            <Card.Header as="h5">Cadastro de Vinho</Card.Header>
            <Card.Body>
                <Form>
                    <Row className="mb-3">
                        <Form.Group as={Row} className="mb-3">
                            <Form.Label>Rótulo</Form.Label>
                            <Form.Control
                                name='name'
                                type='text'
                                placeholder="Informe o rótulo do vinho"
                            />
                        </Form.Group>
                    </Row>

                    <Row className="mb-3">
                        <Form.Group as={Col} className="mb-3">
                            <Form.Label>Preço</Form.Label>
                            <Form.Control
                                name='price'
                                type='text'
                                placeholder="Informe o preço do vinho em R$"
                            />
                        </Form.Group>

                        <Form.Group as={Col} className="mb-3">
                            <Form.Label>Local de compra</Form.Label>
                            <Form.Control
                                name='purchaseLocation'
                                type='text'
                                placeholder="Informe o local de compra do vinho"
                            />
                        </Form.Group>
                    </Row>

                    <Row className="mb-3">
                        <Form.Group as={Col} className="mb-3">
                            <Form.Label>Data de compra</Form.Label>
                            <Form.Control
                                name='purchaseDate'
                                type='date'
                                placeholder="Informe a data de compra no formato dd/mm/aaaa"
                            />
                        </Form.Group>

                        <Form.Group as={Col} className="mb-3">
                            <Form.Label>Tipo de vinho</Form.Label>
                            <Form.Select aria-label="Default select example">
                                <option>Selectione o tipo de vinho</option>
                                {Object.values(EnumWineType).map((type, index) => (
                                    <option key={index} value={type}>{type}</option>
                                ))}
                            </Form.Select>
                        </Form.Group>

                        <Form.Group as={Col} className="mb-3">
                            <Form.Label>Classificação do vinho</Form.Label>
                            <Form.Select aria-label="Default select example">
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
                                type='text'
                                placeholder="Informe a graduação alcoólica do vinho"
                            />
                        </Form.Group>

                        <Form.Group as={Col} className="mb-3">
                            <Form.Label>Volume da garrafa (ml)</Form.Label>
                            <Form.Control
                                name='volumeMl'
                                type='text'
                                placeholder="Informe o volume da garrafa em ml"
                            />
                        </Form.Group>

                        <Form.Group as={Col} className="mb-3">
                            <Form.Label>Uva</Form.Label>
                            <Form.Control
                                name='grape'
                                type='text'
                                placeholder="Informe a uva do vinho"
                            />
                        </Form.Group>
                    </Row>

                    <Row className="mb-3">
                        <Form.Group as={Col} className="mb-3">
                            <Form.Label>Produtor</Form.Label>
                            <Form.Control
                                name='winery'
                                type='text'
                                placeholder="Informe o produtor do vinho"
                            />
                        </Form.Group>

                        <Form.Group as={Col} className="mb-3">
                            <Form.Label>Temperatura de serviço</Form.Label>
                            <Form.Control
                                name='serviceTemperature'
                                type='text'
                                placeholder="Informe a temperatura de serviço do vinho"
                            />
                        </Form.Group>

                        <Form.Group as={Col} className="mb-3">
                            <Form.Label>Safra</Form.Label>
                            <Form.Control
                                name='harvest'
                                type='text'
                                placeholder="Informe a safra do vinho"
                            />
                        </Form.Group>
                    </Row>

                    <Row className="mb-3">
                        <Form.Group as={Col} className="mb-3">
                            <Form.Label>País de origem</Form.Label>
                            <Form.Control
                                name='country'
                                type='text'
                                placeholder="Informe o país de origem do vinho"
                            />
                        </Form.Group>

                        <Form.Group as={Col} className="mb-3">
                            <Form.Label>Tempo de guarda</Form.Label>
                            <Form.Control
                                name='guardTime'
                                type='text'
                                placeholder="Informe o tempo de guarda do vinho"
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

                        <Form.Group as={Col} className="mb-3">
                            <Form.Label>Maturação</Form.Label>
                            <Form.Control
                                name='maturation'
                                type='text'
                                placeholder="Informe como se deu a maturação do vinho"
                            />
                        </Form.Group>
                    </Row>

                    <Form.Group as={Row} className="mb-3">
                        <Form.Label as={Row}>Harmonização</Form.Label>
                        <Form.Control
                            name='harmonization'
                            type='text'
                            placeholder="Informe como harmonizar o vinho"
                        />
                    </Form.Group>

                    <Button variant="primary" type="submit">Cadastrar</Button>

                </Form>
            </Card.Body>
        </Card>
    )
}

export default WineRegistration