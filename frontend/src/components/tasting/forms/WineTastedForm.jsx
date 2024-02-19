import { useState } from 'react'
import { Form, Card, Button } from 'react-bootstrap';
import Row from 'react-bootstrap/Row';
import EnumTastingType from '../../../utils/enums/EnumTastingType';

const WineTastedForm = ({ nextStep, handleFormData, prevStep, values }) => {
    const { whatTasted } = values;

    const [error, setError] = useState(false);

    const submitFormData = (e) => {
        e.preventDefault();
        nextStep();
    };

    const tastingType = Object.values(EnumTastingType);

    return (
        <>
            <p style={{ textAlign: 'left', marginTop: 20 }}><strong>Vinho:</strong> {whatTasted}{" "}</p>
            <Form onSubmit={submitFormData}>
                <Form.Group as={Row} className='mb-3'>
                    <Form.Label as={Row}>Data da degustação</Form.Label>
                    <Form.Control
                        style={{ border: error ? '2px solid red' : '' }}
                        name='tastingDate'
                        defaultValue={values.tastingDate}
                        type='date'
                        onChange={handleFormData('tastingDate')}
                    />
                </Form.Group>

                <Form.Group as={Row} className='mb-3'>
                    <Form.Label as={Row}>Tipo de degustação</Form.Label>
                    <Form.Select
                        style={{ border: error ? '2px solid red' : '' }}
                        name='tastingType'
                        defaultValue=''
                        onChange={handleFormData('tastingType')}
                    >
                        {Object.values(EnumTastingType).map((type, index) => (
                            <option key={index} value={type}>{type}</option>
                        ))}
                    </Form.Select>
                </Form.Group>

                <Form.Group as={Row} className='mb-3'>
                    <Form.Label as={Row}>Ficha de degustação</Form.Label>
                    <Form.Control
                        style={{ border: error ? '2px solid red' : '' }}
                        name='tastingCards'
                        defaultValue={values.tastingCards}
                        type='text'
                        onChange={handleFormData('tastingCards')}
                    />
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
    )
}

export default WineTastedForm