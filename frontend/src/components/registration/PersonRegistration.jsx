import React, { useState } from 'react';
import { Form } from 'react-bootstrap';
import { Card } from 'primereact/card';
import Row from 'react-bootstrap/Row';
import Col from 'react-bootstrap/Col';
import { Dropdown } from 'primereact/dropdown';
import { Button } from 'primereact/button';
import usePersonComponentHook from '../../hooks/registration/usePersonComponentHook';
import useListStateComponentHook from '../../hooks/registration/useListStateComponentHook';
import useListCountryComponentHook from '../../hooks/registration/useListCountryComponentHook';

const PersonRegistration = () => {
  const { state } = useListStateComponentHook();
  const { country: countries } = useListCountryComponentHook();

  const {
    name, setName,
    document, setDocument,
    birthDate, setBirthDate,
    address, setAddress,
    savePerson
  } = usePersonComponentHook();

  const [submitted, setSubmitted] = useState(false);

  const handleAddressChange = (e) => {
    const { name, value } = e.target;
    setAddress(prevAddress => ({
      ...prevAddress,
      [name]: value
    }));
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    setAddress(prevAddress => ({
      ...prevAddress
    }));
    await savePerson();
    setSubmitted(true);
    handleLimparFormulario();
  };

  const handleLimparFormulario = () => {
    setName('');
    setDocument('');
    setBirthDate('');
    setAddress({
      addressDescription: '',
      addressNumber: '',
      complement: '',
      district: '',
      zipCode: '',
      city: '',
      uf: '',
      country: '',
      phoneNumber: ''
    });
  };

  return (
    <>
      <Card style={{ marginTop: 10 }} title="Cadastro de Pessoa">
        <Form onSubmit={handleSubmit}>
          <Row className="mb-3">
            <Form.Group as={Col} className="mb-3">
              <Form.Label>Nome</Form.Label>
              <Form.Control
                name='name'
                value={name}
                type='text'
                placeholder="Informe seu nome completo"
                onChange={(e) => setName(e.target.value)}
              />
            </Form.Group>
            <Form.Group as={Col} className="mb-3">
              <Form.Label>CPF</Form.Label>
              <Form.Control
                name='document'
                value={document}
                type='text'
                placeholder="Informe seu CPF"
                onChange={(e) => setDocument(e.target.value)}
              />
            </Form.Group>
            <Form.Group as={Col} className="mb-3">
              <Form.Label>Data de Nascimento</Form.Label>
              <Form.Control
                name='birthDate'
                value={birthDate}
                type='date'
                placeholder="Informe sua data de nascimento"
                onChange={(e) => setBirthDate(e.target.value)}
              />
            </Form.Group>
          </Row>
        </Form>
      </Card>

      <Card style={{ marginTop: 10 }} title="Cadastro de Endereço">
        <Form onSubmit={handleSubmit}>
          <Row className='mb-3'>
            <Form.Group as={Col} className='mb-3'>
              <Form.Label>Endereço</Form.Label>
              <Form.Control
                name='addressDescription'
                value={address.addressDescription}
                type='text'
                placeholder="Informe o endereço"
                onChange={handleAddressChange}
              />
            </Form.Group>
            <Form.Group as={Col} className='mb-3'>
              <Form.Label>Número</Form.Label>
              <Form.Control
                name='addressNumber'
                value={address.addressNumber}
                type='text'
                placeholder="Informe o número"
                onChange={handleAddressChange}
              />
            </Form.Group>
            <Form.Group as={Col} className='mb-3'>
              <Form.Label>Complemento</Form.Label>
              <Form.Control
                name='complement'
                value={address.complement}
                type='text'
                placeholder="Informe o complemento"
                onChange={handleAddressChange}
              />
            </Form.Group>
          </Row>

          <Row className='mb-3'>
            <Form.Group as={Col} className='mb-3'>
              <Form.Label>Bairro</Form.Label>
              <Form.Control
                name='district'
                value={address.district}
                type='text'
                placeholder="Informe o bairro"
                onChange={handleAddressChange}
              />
            </Form.Group>
            <Form.Group as={Col} className='mb-3'>
              <Form.Label>CEP</Form.Label>
              <Form.Control
                name='zipCode'
                value={address.zipCode}
                type='text'
                placeholder="Informe o CEP"
                onChange={handleAddressChange}
              />
            </Form.Group>
            <Form.Group as={Col} className='mb-3'>
              <Form.Label>Cidade</Form.Label>
              <Form.Control
                name='city'
                value={address.city}
                type='text'
                placeholder="Informe a cidade"
                onChange={handleAddressChange}
              />
            </Form.Group>
          </Row>
          <Row className='mb-3'>
            <Form.Group as={Col} className='mb-3'>
              <Form.Label>UF</Form.Label>
              <br />
              <Dropdown
                filter
                id='state'
                value={address.uf}
                options={state.map(state => ({ label: state.stateName, value: state.uf }))}
                placeholder='Selecione um estado'
                onChange={(e) => setAddress(prevAddress => ({ ...prevAddress, uf: e.value }))}
              />
            </Form.Group>
            <Form.Group as={Col} className='mb-3'>
              <Form.Label>País</Form.Label>
              <br />
              <Dropdown
                id='country'
                value={address.country}
                options={countries.map(country => ({ label: country.countryName, value: country.countryName }))}
                filter
                placeholder='Selecione um país'
                onChange={(e) => setAddress(prevAddress => ({ ...prevAddress, country: e.value }))}
              />
            </Form.Group>
            <Form.Group as={Col} className='mb-3'>
              <Form.Label>Telefone</Form.Label>
              <Form.Control
                name='phoneNumber'
                value={address.phoneNumber}
                type='text'
                placeholder="Informe o telefone"
                onChange={handleAddressChange}
              />
            </Form.Group>
          </Row>
          <div className="flex flex-wrap gap-2 mt-4">
            <Button label="Limpar Formulário" icon="pi pi-times" className="p-button-danger" />
            <Button label="Confirmar" icon="pi pi-check" className="p-button-success" type='submit' />
          </div>
        </Form>
      </Card>
    </>
  )
}

export default PersonRegistration;
