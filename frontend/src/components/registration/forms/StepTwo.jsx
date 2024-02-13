import React, { useState } from 'react';
import { Form, Card, Button } from 'react-bootstrap';
import validator from 'validator';
import Row from 'react-bootstrap/Row';

const StepTwo = ({ nextStep, handleFormData, values, prevStep }) => {

    const [error, setError] = useState(false);

    const submitFormData = (e) => {
        e.preventDefault();

        if (
            validator.isEmpty(values.addressDescription) ||
            validator.isEmpty(values.addressNumber) ||
            validator.isEmpty(values.complement) ||
            validator.isEmpty(values.district) ||
            validator.isEmpty(values.zipCode) ||
            validator.isEmpty(values.city) ||
            validator.isEmpty(values.uf) ||
            validator.isEmpty(values.country) ||
            validator.isEmpty(values.phoneNumber)
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
                            <Form.Label as={Row}>Endereço</Form.Label>
                            <Form.Control
                                style={{ border: error ? '2px solid red' : '' }}
                                name='addressDescription'
                                defaultValue={values.addressDescription}
                                type='text'
                                placeholder="Informe o endereço"
                                onChange={handleFormData('addressDescription')}
                            />
                            {error ? (
                                <Form.Text style={{ color: 'red' }}>Este campo é obrigatório</Form.Text>
                            ) : (
                                ''
                            )}
                        </Form.Group>

                        <Form.Group as={Row} className='mb-3'>
                            <Form.Label as={Row}>Complemento</Form.Label>
                            <Form.Control
                                style={{ border: error ? '2px solid red' : '' }}
                                name='complement'
                                defaultValue={values.complement}
                                type='text'
                                placeholder="Informe o complemento"
                                onChange={handleFormData('complement')}
                            />
                            {error ? (
                                <Form.Text style={{ color: 'red' }}>Este campo é obrigatório</Form.Text>
                            ) : (
                                ''
                            )}
                        </Form.Group>

                        <Form.Group as={Row} className='mb-3'>
                            <Form.Label as={Row}>Número</Form.Label>
                            <Form.Control
                                style={{ border: error ? '2px solid red' : '' }}
                                name='addressNumber'
                                defaultValue={values.addressNumber}
                                type='number'
                                placeholder="Informe o número"
                                onChange={handleFormData('addressNumber')}
                            />
                            {error ? (
                                <Form.Text style={{ color: 'red' }}>Este campo é obrigatório</Form.Text>
                            ) : (
                                ''
                            )}
                        </Form.Group>

                        <Form.Group as={Row} className='mb-3'>
                            <Form.Label as={Row}>Bairro</Form.Label>
                            <Form.Control
                                style={{ border: error ? '2px solid red' : '' }}
                                name='district'
                                defaultValue={values.district}
                                type='text'
                                placeholder="Informe o bairro"
                                onChange={handleFormData('district')}
                            />
                            {error ? (
                                <Form.Text style={{ color: 'red' }}>Este campo é obrigatório</Form.Text>
                            ) : (
                                ''
                            )}
                        </Form.Group>

                        <Form.Group as={Row} className='mb-3'>
                            <Form.Label as={Row}>CEP</Form.Label>
                            <Form.Control
                                style={{ border: error ? '2px solid red' : '' }}
                                name='zipCode'
                                defaultValue={values.zipCode}
                                type='text'
                                placeholder="Informe o CEP"
                                onChange={handleFormData('zipCode')}
                            />
                            {error ? (
                                <Form.Text style={{ color: 'red' }}>Este campo é obrigatório</Form.Text>
                            ) : (
                                ''
                            )}
                        </Form.Group>

                        <Form.Group as={Row} className='mb-3'>
                            <Form.Label as={Row}>Cidade</Form.Label>
                            <Form.Control
                                style={{ border: error ? '2px solid red' : '' }}
                                name='city'
                                defaultValue={values.city}
                                type='text'
                                placeholder="Informe cidade"
                                onChange={handleFormData('city')}
                            />
                            {error ? (
                                <Form.Text style={{ color: 'red' }}>Este campo é obrigatório</Form.Text>
                            ) : (
                                ''
                            )}
                        </Form.Group>

                        <Form.Group as={Row} className='mb-3'>
                            <Form.Label as={Row}>UF</Form.Label>
                            <Form.Control
                                style={{ border: error ? '2px solid red' : '' }}
                                name='uf'
                                defaultValue={values.uf}
                                type='text'
                                placeholder="Informe a UF do estado"
                                onChange={handleFormData('uf')}
                            />
                            {error ? (
                                <Form.Text style={{ color: 'red' }}>Este campo é obrigatório</Form.Text>
                            ) : (
                                ''
                            )}
                        </Form.Group>

                        <Form.Group as={Row} className='mb-3'>
                            <Form.Label as={Row}>País</Form.Label>
                            <Form.Control
                                style={{ border: error ? '2px solid red' : '' }}
                                name='country'
                                defaultValue={values.country}
                                type='text'
                                placeholder="Informe País"
                                onChange={handleFormData('country')}
                            />
                            {error ? (
                                <Form.Text style={{ color: 'red' }}>Este campo é obrigatório</Form.Text>
                            ) : (
                                ''
                            )}
                        </Form.Group>

                        <Form.Group as={Row} className='mb-3'>
                            <Form.Label as={Row}>Telefone</Form.Label>
                            <Form.Control
                                style={{ border: error ? '2px solid red' : '' }}
                                name='phoneNumber'
                                defaultValue={values.phoneNumber}
                                type='text'
                                placeholder="Informe número de telefone"
                                onChange={handleFormData('phoneNumber')}
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

                            <Button variant="primary" type="submit">Continue</Button>
                        </div>

                    </Form>
                </Card.Body>
            </Card>
        </>
    )
}

export default StepTwo