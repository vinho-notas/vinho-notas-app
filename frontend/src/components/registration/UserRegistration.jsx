import React, { useState } from 'react';
import { Form } from 'react-bootstrap';
import { Card } from 'primereact/card';
import Row from 'react-bootstrap/Row';
import Col from 'react-bootstrap/Col';
import { Dropdown } from 'primereact/dropdown';
import { Button } from 'primereact/button';

import useUserComponentHook from '../../hooks/registration/useUserComponentHook';
import useListPersonComponentHook from '../../hooks/registration/useListPersonComponentHook';
import EnumProfile from '../../utils/enums/EnumProfile';

const UserRegistration = () => {
    const { persons } = useListPersonComponentHook();
    const {
        person, setPerson,
        enumProfile, setEnumProfile,
        email, setEmail,
        password, setPassword,
        saveUser
    } = useUserComponentHook();

    const profile = Object.values(EnumProfile);

    const [submitted, setSubmitted] = useState(false);

    const handleSubmit = async (e) => {
        e.preventDefault();
        const userData = {
            personId: person.id,
            enumProfile,
            email,
            password
        };
        await saveUser(userData);
        setSubmitted(true);
        handleLimparFormulario();
    };

    const handleLimparFormulario = () => {
        setPerson('');
        setEnumProfile('');
        setEmail('');
        setPassword('');
    };

    return (
        <>
            <Card style={{ marginTop: 10 }} title="Cadastro de Usuários">
                <Form onSubmit={handleSubmit}>
                    <Row className='mb-3'>
                        <Form.Group as={Col} className='mb-3'>
                            <Form.Label>Pessoa</Form.Label>
                            <br />
                            <Dropdown
                                value={person}
                                options={persons}
                                onChange={(e) => setPerson(e.value)}
                                optionLabel="name"
                                placeholder="Selecione a pessoa"
                            />

                        </Form.Group>
                        <Form.Group as={Col} className='mb-3'>
                            <Form.Label>Perfil</Form.Label>
                            <br />
                            <Dropdown
                                value={enumProfile}
                                options={profile}
                                onChange={(e) => setEnumProfile(e.value)}
                                placeholder="Selecione o perfil"
                            />
                        </Form.Group>
                    </Row>
                    <Row className='mb-3'>
                        <Form.Group as={Col} className='mb-3'>
                            <Form.Label>Email</Form.Label>
                            <Form.Control
                                type='text'
                                name='email'
                                value={email}
                                onChange={(e) => setEmail(e.target.value)}
                                placeholder='Digite o email'
                            />
                        </Form.Group>
                        <Form.Group as={Col} className='mb-3'>
                            <Form.Label>Senha</Form.Label>
                            <Form.Control
                                type='password'
                                name='password'
                                value={password}
                                onChange={(e) => setPassword(e.target.value)}
                                placeholder='Digite a senha'
                            />
                        </Form.Group>
                    </Row>

                    <div className="flex flex-wrap gap-2 mt-4">
                        <Button label="Limpar Formulário" icon="pi pi-times" className="p-button-danger" onClick={handleLimparFormulario} />
                        <Button label="Confirmar" icon="pi pi-check" className="p-button-success" type='submit' />
                    </div>
                </Form>

            </Card>
        </>
    )
}

export default UserRegistration;
