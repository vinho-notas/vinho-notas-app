import React, { useState } from 'react';
import { Form, Card, Button } from 'react-bootstrap';
import validator from 'validator';
import Row from 'react-bootstrap/Row';
import InputMask from 'react-input-mask';

const StepOne = ({ nextStep, handleFormData, values }) => {
    const [errors, setErrors] = useState(
        {
            name: false,
            document: false,
            birthDate: false
        }
    );

    const submitFormData = (e) => {
        e.preventDefault();

        const newErrors = {
            name: validator.isEmpty(values.name),
            document: validator.isEmpty(values.document),
            birthDate: validator.isEmpty(values.birthDate)
        };

        setErrors(newErrors);

        if (!newErrors.name && !newErrors.document && !newErrors.birthDate) {
            nextStep();
        }
    };

    return (
        <div>
            <Card style={{ marginTop: 100 }}>
                <Card.Body>
                    <Form onSubmit={submitFormData}>
                        <Form.Group as={Row} className='mb-3'>
                            <Form.Label as={Row}>Nome</Form.Label>
                            <Form.Control
                                style={{ border: errors.name ? '2px solid red' : '' }}
                                name='name'
                                defaultValue={values.name}
                                type='text'
                                placeholder="Informe seu nome completo"
                                onChange={handleFormData('name')}
                            />
                            {errors.name && (
                                <Form.Text style={{ color: 'red' }}>Este campo é obrigatório</Form.Text>
                            )}
                        </Form.Group>

                        <Form.Group as={Row} className='mb-3'>
                            <Form.Label as={Row}>Documento</Form.Label>
                            <InputMask
                                mask={'999.999.999-99'}
                                onChange={handleFormData('document')}
                                value={values.document}
                            >
                                {(inputProps) => (
                                    <Form.Control
                                        {...inputProps}
                                        style={{ border: errors.document ? '2px solid red' : '' }}
                                        name='document'
                                        defaultValue={values.document}
                                        type='text'
                                        placeholder="Informe seu CPF"
                                    />
                                )}
                            </InputMask>
                            {errors.document && (
                                <Form.Text style={{ color: 'red' }}>Este campo é obrigatório</Form.Text>
                            )}
                        </Form.Group>

                        <Form.Group as={Row} className='mb-3'>
                            <Form.Label as={Row}>Data de nascimento</Form.Label>
                            <Form.Control
                                style={{ border: errors.birthDate ? '2px solid red' : '' }}
                                name='birthDate'
                                defaultValue={values.birthDate}
                                type='date'
                                placeholder="Informe sua data de nascimento"
                                onChange={handleFormData('birthDate')}
                            />
                            {errors.birthDate && (
                                <Form.Text style={{ color: 'red' }}>Este campo é obrigatório</Form.Text>
                            )}
                        </Form.Group>

                        <Button variant="primary" type="submit">Continue</Button>

                    </Form>
                </Card.Body>
            </Card>
        </div>
    );
};

export default StepOne;