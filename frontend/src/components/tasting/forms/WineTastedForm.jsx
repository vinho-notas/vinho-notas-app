import { useState } from 'react'
import { Form, Button } from 'react-bootstrap';
import Row from 'react-bootstrap/Row';
import Col from 'react-bootstrap/Col';
import EnumTastingType from '../../../utils/enums/EnumTastingType';
import { Menu } from 'primereact/menu';
import TastingCard from '../TastingCard';


const WineTastedForm = ({ nextStep, handleFormData, prevStep, values }) => {
    const { whatTasted } = values;

    const [error, setError] = useState(false);

    const [showTastingCard, setShowTastingCard] = useState(false);

    const submitFormData = (e) => {
        e.preventDefault();
        nextStep();
    };
    const tastingType = Object.values(EnumTastingType);

    const itemsTastingCard = [
        {
            label: 'Ficha de degustação',
            items: [
                {
                    label: 'Nova ficha de degustação',
                    icon: 'pi pi-plus',
                    command: () => setShowTastingCard(true)
                },
                {
                    label: 'Pesquisar ficha de degustação',
                    icon: 'pi pi-search'
                }
            ]
        }
    ];

    return (
        <>
            {showTastingCard ? (
                <TastingCard />
            ) : (
                <>
                    <p style={{ textAlign: 'left', marginTop: 20 }}><strong>Vinho:</strong> {whatTasted}{" "}</p>
                    <Form onSubmit={submitFormData}>
                        <Row className="mb-3">

                        <Form.Group as={Col} className='mb-3'>
                            <Form.Label as={Row}>Data da degustação</Form.Label>
                            <Form.Control
                                style={{ border: error ? '2px solid red' : '' }}
                                name='tastingDate'
                                defaultValue={values.tastingDate}
                                type='date'
                                onChange={handleFormData('tastingDate')}
                            />
                        </Form.Group>

                        <Form.Group as={Col} className='mb-3'>
                            <Form.Label as={Row}>Tipo de degustação</Form.Label>
                            <Form.Select
                                style={{ border: error ? '2px solid red' : '' }}
                                name='tastingType'
                                defaultValue=''
                                onChange={handleFormData('tastingType')}
                            >
                                <option value='' disabled>Selecione o tipo de degustação</option>
                                {Object.values(EnumTastingType).map((type, index) => (
                                    <option key={index} value={type}>{type}</option>
                                ))}
                            </Form.Select>
                        </Form.Group>
                        </Row>

                        <Form.Group as={Row} className='mb-3'>
                            <Form.Label as={Row}>Ficha de degustação</Form.Label>
                            <Menu model={itemsTastingCard} />
                        </Form.Group>

                        <div style={{ display: "flex", justifyContent: "space-around" }}>
                            <Button variant="primary" onClick={prevStep}>
                                Previous
                            </Button>

                            <Button variant="primary" type="submit">
                                Submit
                            </Button>
                        </div>

                    </Form>
                </>
            )}
        </>
    )
}

export default WineTastedForm