import React, { useState } from 'react';
import {Form, Card, Button} from 'react-bootstrap';
import validator from 'validator';
import Row from 'react-bootstrap/Row';

const StepOne = ({ nextStep, handleFormData, values }) => {
    const [error, setError] = useState(false);

    const submitFormData =(e) => {
        e.preventDefault();        

        if (
            validator.isEmpty(values.name) ||
            validator.isEmpty(values.document) ||
            validator.isEmpty(values.birthDate)
        ) {
            setError(true);
        } else {
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
                            style={{ border: error ? '2px solid red' : ''}}
                            name='name'
                            defaultValue={values.name}
                            type='text'
                            placeholder="Informe seu nome completo"
                            onChange={handleFormData('name')}
                        />
                        {error ? (
                            <Form.Text style={{ color: 'red' }}>Este campo é obrigatório</Form.Text>
                        ) : (
                            ''                        
                        )}
                    </Form.Group>

                    <Form.Group as={Row} className='mb-3'>
                        <Form.Label as={Row}>Documento</Form.Label>
                            <Form.Control 
                                style={{ border: error ? '2px solid red' : ''}}
                                name='document'
                                defaultValue={values.document}
                                type='text'
                                placeholder="Informe seu CPF"
                                onChange={handleFormData('document')}
                            />
                            {error ? (
                                <Form.Text style={{ color: 'red' }}>Este campo é obrigatório</Form.Text>
                            ) : (
                                ''                        
                            )}
                    </Form.Group>

                    <Form.Group as={Row} className='mb-3'>
                        <Form.Label as={Row}>Data de nascimento</Form.Label>
                            <Form.Control 
                                style={{ border: error ? '2px solid red' : ''}}
                                name='birthDate'
                                defaultValue={values.birthDate}
                                type='text'
                                placeholder="Informe sua data de nascimento"
                                onChange={handleFormData('birthDate')}
                            />
                            {error ? (
                                <Form.Text style={{ color: 'red' }}>Este campo é obrigatório</Form.Text>
                            ) : (
                                ''                        
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