import React, { useState } from 'react';
import { Form, Card, Button } from 'react-bootstrap';
import validator from 'validator';
import Row from 'react-bootstrap/Row';
import InputMask from 'react-input-mask';

const StepTwo = ({ nextStep, handleFormData, values, prevStep }) => {
    const [errors, setErrors] = useState(
        {
            addressDescription: false,
            addressNumber: false,
            complement: false,
            district: false,
            zipCode: false,
            city: false,
            uf: false,
            country: false,
            phoneNumber: false
        }
    );

    const submitFormData = (e) => {
        e.preventDefault();

        const newErrors = {
            addressDescription: validator.isEmpty(values.addressDescription),
            addressNumber: validator.isEmpty(values.addressNumber),
            district: validator.isEmpty(values.district),
            zipCode: validator.isEmpty(values.zipCode),
            city: validator.isEmpty(values.city),
            uf: validator.isEmpty(values.uf),
            country: validator.isEmpty(values.country),
            phoneNumber: validator.isEmpty(values.phoneNumber)
        }

        setErrors(newErrors);

        if (!Object.values(newErrors).some(error => error)) {
            nextStep();
        }
    };

    return (
        <>
            <Card style={{ marginTop: 10 }}>
                <Card.Body>
                    <Form onSubmit={submitFormData}>

                        <Form.Group as={Row} className='mb-3'>
                            <Form.Label as={Row}>Endereço</Form.Label>
                            <Form.Control
                                style={{ border: errors.addressDescription ? '2px solid red' : '' }}
                                name='addressDescription'
                                defaultValue={values.addressDescription}
                                type='text'
                                placeholder="Informe o endereço"
                                onChange={handleFormData('addressDescription')}
                            />
                            {errors.addressDescription && (
                                <Form.Text style={{ color: 'red' }}>Este campo é obrigatório</Form.Text>
                            )}
                        </Form.Group>

                        <Form.Group as={Row} className='mb-3'>
                            <Form.Label as={Row}>Complemento</Form.Label>
                            <Form.Control
                                name='complement'
                                defaultValue={values.complement}
                                type='text'
                                placeholder="Informe o complemento"
                                onChange={handleFormData('complement')}
                            />
                        </Form.Group>

                        <Form.Group as={Row} className='mb-3'>
                            <Form.Label as={Row}>Número</Form.Label>
                            <Form.Control
                                style={{ border: errors.addressNumber ? '2px solid red' : '' }}
                                name='addressNumber'
                                defaultValue={values.addressNumber}
                                type='number'
                                placeholder="Informe o número"
                                onChange={handleFormData('addressNumber')}
                                min="0"
                            />
                            {errors.addressNumber && (
                                <Form.Text style={{ color: 'red' }}>Este campo é obrigatório</Form.Text>
                            )}
                        </Form.Group>

                        <Form.Group as={Row} className='mb-3'>
                            <Form.Label as={Row}>Bairro</Form.Label>
                            <Form.Control
                                style={{ border: errors.district ? '2px solid red' : '' }}
                                name='district'
                                defaultValue={values.district}
                                type='text'
                                placeholder="Informe o bairro"
                                onChange={handleFormData('district')}
                            />
                            {errors.district && (
                                <Form.Text style={{ color: 'red' }}>Este campo é obrigatório</Form.Text>
                            )}
                        </Form.Group>

                        <Form.Group as={Row} className='mb-3'>
                            <Form.Label as={Row}>CEP</Form.Label>
                            <InputMask
                                mask={'99.999-999'}
                                onChange={handleFormData('zipCode')}
                                value={values.zipCode}
                            >
                                {(inputProps) => (
                                    <Form.Control
                                        {...inputProps}
                                        style={{ border: errors.zipCode ? '2px solid red' : '' }}
                                        name='zipCode'
                                        defaultValue={values.zipCode}
                                        type='text'
                                        placeholder="Informe o CEP"
                                    />
                                )}
                            </InputMask>
                            {errors.zipCode && (
                                <Form.Text style={{ color: 'red' }}>Este campo é obrigatório</Form.Text>
                            )}
                        </Form.Group>

                        <Form.Group as={Row} className='mb-3'>
                            <Form.Label as={Row}>Cidade</Form.Label>
                            <Form.Control
                                style={{ border: errors.city ? '2px solid red' : '' }}
                                name='city'
                                defaultValue={values.city}
                                type='text'
                                placeholder="Informe cidade"
                                onChange={handleFormData('city')}
                            />
                            {errors.city && (
                                <Form.Text style={{ color: 'red' }}>Este campo é obrigatório</Form.Text>
                            )}
                        </Form.Group>

                        <Form.Group as={Row} className='mb-3'>
                            <Form.Label as={Row}>UF</Form.Label>
                            <Form.Control
                                style={{ border: errors.uf ? '2px solid red' : '' }}
                                name='uf'
                                defaultValue={values.uf}
                                type='text'
                                placeholder="Informe a UF do estado"
                                onChange={handleFormData('uf')}
                            />
                            {errors.uf && (
                                <Form.Text style={{ color: 'red' }}>Este campo é obrigatório</Form.Text>
                            )}
                        </Form.Group>

                        <Form.Group as={Row} className='mb-3'>
                            <Form.Label as={Row}>País</Form.Label>
                            <Form.Control
                                style={{ border: errors.country ? '2px solid red' : '' }}
                                name='country'
                                defaultValue={values.country}
                                type='text'
                                placeholder="Informe País"
                                onChange={handleFormData('country')}
                            />
                            {errors.country && (
                                <Form.Text style={{ color: 'red' }}>Este campo é obrigatório</Form.Text>
                            )}
                        </Form.Group>

                        <Form.Group as={Row} className='mb-3'>
                            <Form.Label as={Row}>Telefone</Form.Label>
                            <InputMask
                                mask={'(99) 99999-9999'}
                                onChange={handleFormData('phoneNumber')}
                                value={values.phoneNumber}
                            >
                                {(inputProps) => (
                                    <Form.Control
                                        {...inputProps}
                                        style={{ border: errors.phoneNumber ? '2px solid red' : '' }}
                                        name='phoneNumber'
                                        defaultValue={values.phoneNumber}
                                        type='text'
                                        placeholder="Informe número de telefone"
                                    />
                                )}
                            </InputMask>
                            {errors.phoneNumber && (
                                <Form.Text style={{ color: 'red' }}>Este campo é obrigatório</Form.Text>
                            )}
                        </Form.Group>

                        <div style={{ display: "flex", justifyContent: "space-around" }}>
                            <Button variant="primary" onClick={prevStep}>
                                Voltar
                            </Button>

                            <Button variant="primary" type="submit">Continuar</Button>
                        </div>

                    </Form>
                </Card.Body>
            </Card>
        </>
    )
}

export default StepTwo