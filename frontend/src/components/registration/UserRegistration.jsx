import React, { useState } from 'react';
import { Form } from 'react-bootstrap';
import { Card } from 'primereact/card';
import InputMask from 'react-input-mask';
import Row from 'react-bootstrap/Row';
import Col from 'react-bootstrap/Col';
import { Dropdown } from 'primereact/dropdown';
import { Button } from 'primereact/button';
import { useNavigate } from "react-router-dom";
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
    const navigate = useNavigate();
    const profile = Object.values(EnumProfile);
    const [submitted, setSubmitted] = useState(false);

    const handleSubmit = async (e) => {
        e.preventDefault();
        const userData = {
            personId: person.id,
            enumProfile: EnumProfile.OENOPHILE,
            email,
            password
        };
        await saveUser(userData);
        setSubmitted(true);
        navigate('/users');
    };

    const handleLimparFormulario = () => {
        setPerson('');        
        setEmail('');
        setPassword('');
    };

    return (
        <>
            <Card style={{ marginTop: 10 }} title="Cadastro de Usuários">
                <Form>
                    <Row data-testid="user-registration-form" className='mb-3'>
                        <Form.Group as={Col} className='mb-3'>
                            <Form.Label data-testid="label-pessoa">Pessoa</Form.Label>
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
                            <Form.Label data-testid="label-perfil">Perfil</Form.Label>
                            <br />
                            <Dropdown
                                disabled
                                value={EnumProfile.OENOPHILE}
                                options={profile}
                                onChange={(e) => setEnumProfile(e.value)}
                                placeholder="Selecione o perfil"
                            />
                        </Form.Group>
                    </Row>
                    <Row className='mb-3'>
                        <Form.Group as={Col} className='mb-3'>
                            <Form.Label data-testid="label-email">Email</Form.Label>
                            <Form.Control
                                type='email'
                                name='email'
                                value={email}
                                onChange={(e) => setEmail(e.target.value)}
                                placeholder="name@example.com"
                            />
                        </Form.Group>
                        <Form.Group as={Col} className='mb-3'>
                            <Form.Label data-testid="label-senha">Senha</Form.Label>
                            <Form.Control
                                type='text'
                                name='password'
                                value={password}
                                onChange={(e) => setPassword(e.target.value)}
                                placeholder='Digite a senha'
                            />
                        </Form.Group>
                    </Row>

                    <div className="flex flex-wrap gap-2 mt-4">
                        <Button label="Limpar Formulário" icon="pi pi-times" className="p-button-danger" onClick={handleLimparFormulario} style={{ borderRadius: '20px' }}/>
                        <Button label="Confirmar" icon="pi pi-check" className="p-button-success" type='submit' onClick={handleSubmit} style={{ borderRadius: '20px' }}/>
                    </div>
                </Form>

            </Card>
        </>
    )
}

export default UserRegistration;
