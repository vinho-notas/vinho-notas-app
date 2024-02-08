import React, { useState } from 'react';
import { Form, Card, Button } from 'react-bootstrap';
import validator from 'validator';
import Row from 'react-bootstrap/Row';

const StepThree = ({ nextStep, handleFormData, prevStep, values }) => {

    const [error, setError] = useState(false);

    const submitFormData = (e) => {
        e.preventDefault();

        if (
            validator.isEmpty(values.email) ||
            validator.isEmpty(values.password)
        ) {
            setError(true);
        } else {
            nextStep();
        }
    };

    return (
        <>
            <Card style={{ marginTop: 100 }}>
                <Card.Body>
                    <Form onSubmit={submitFormData}>

                        <Form.Group as={Row} className='mb-3'>
                            <Form.Label as={Row}>Email</Form.Label>
                            <Form.Control
                                style={{ border: error ? '2px solid red' : '' }}
                                name='email'
                                defaultValue={values.email}
                                type='email'
                                placeholder="Informe o email"
                                onChange={handleFormData('email')}
                            />
                            {error ? (
                                <Form.Text style={{ color: 'red' }}>Este campo é obrigatório</Form.Text>
                            ) : (
                                ''
                            )}
                        </Form.Group>

                        <Form.Group as={Row} className='mb-3'>
                            <Form.Label as={Row}>Senha</Form.Label>
                            <Form.Control
                                style={{ border: error ? '2px solid red' : '' }}
                                name='password'
                                defaultValue={values.password}
                                type='text'
                                placeholder="Informe uma senha"
                                onChange={handleFormData('password')}
                            />
                            {error ? (
                                <Form.Text style={{ color: 'red' }}>Este campo é obrigatório</Form.Text>
                            ) : (
                                ''
                            )}
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
                </Card.Body>
            </Card>
        </>
    )
}

export default StepThree