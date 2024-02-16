import React, { useState } from 'react';
import { Form, Card, Button } from 'react-bootstrap';
import validator from 'validator';
import Row from 'react-bootstrap/Row';
import InputMask from 'react-input-mask';

const StepThree = ({ nextStep, handleFormData, prevStep, values }) => {

    const [errors, setErrors] = useState(
        {
            email: false,
            password: false
        }
    );


    const submitFormData = (e) => {
        e.preventDefault();

        const newErrors = {
            email: validator.isEmpty(values.email),
            password: validator.isEmpty(values.password)
        }

        setErrors(newErrors);

        if (!Object.values(newErrors).some(error => error)) {
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
                                style={{ border: errors.email ? '2px solid red' : '' }}
                                name='email'
                                defaultValue={values.email}
                                type='email'
                                placeholder="Informe o email"
                                onChange={handleFormData('email')}
                                pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,}\.[a-z]{2,}$"
                                title="Insira um email válido no formato seuemail@email.com"
                            />
                            {errors.email && (
                                <Form.Text style={{ color: 'red' }}>Este campo é obrigatório</Form.Text>
                            )}
                        </Form.Group>

                        <Form.Group as={Row} className='mb-3'>
                            <Form.Label as={Row}>Senha</Form.Label>
                            <Form.Control
                                style={{ border: errors.password ? '2px solid red' : '' }}
                                name='password'
                                defaultValue={values.password}
                                type='password'
                                placeholder="Informe uma senha"
                                onChange={handleFormData('password')}
                                maxLength={4}
                            />
                            {errors.password && (
                                <Form.Text style={{ color: 'red' }}>Este campo é obrigatório</Form.Text>
                            )}
                        </Form.Group>

                        <div style={{ display: "flex", justifyContent: "space-around" }}>
                            <Button variant="primary" onClick={prevStep}>
                                Voltar
                            </Button>

                            <Button variant="primary" type="submit">
                                Cadastrar
                            </Button>
                        </div>

                    </Form>
                </Card.Body>
            </Card>
        </>
    )
}

export default StepThree