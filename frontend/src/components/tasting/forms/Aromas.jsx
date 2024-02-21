import { useState } from 'react';
import { Card, Form } from 'react-bootstrap'
import Row from 'react-bootstrap/Row'
import Col from 'react-bootstrap/Col';

import EnumFruitType from '../../../utils/enums/EnumFruityType';
import EnumDryFruitType from '../../../utils/enums/EnumDryFruitsType';
import EnumFloralsType from '../../../utils/enums/EnumFloralsType';
import EnumVegetablesAndHerbsType from '../../../utils/enums/EnumVegetablesAndHerbsType';

import EnumMineralsType from '../../../utils/enums/EnumMineralsType';
import EnumSpicesType from '../../../utils/enums/EnumSpicesType';
import EnumAnimalsType from '../../../utils/enums/EnumAnimalsType';
import EnumEmpireumaticsType from '../../../utils/enums/EnumEmpireumaticsType';

import EnumWoodType from '../../../utils/enums/EnumWoodType';
import EnumChemicalsAndEtherealType from '../../../utils/enums/EnumChemicalsAndEtherealType';
import EnumLactealType from '../../../utils/enums/EnumLactealType';
import EnumSweetsType from '../../../utils/enums/EnumSweetsType ';

const Aromas = () => {

  const fruitType = Object.values(EnumFruitType);
  const dryFruitType = Object.values(EnumDryFruitType);
  const floralsType = Object.values(EnumFloralsType);
  const vegetablesAndHerbsType = Object.values(EnumVegetablesAndHerbsType);

  const mineralsType = Object.values(EnumMineralsType);
  const spicesType = Object.values(EnumSpicesType);
  const animalsType = Object.values(EnumAnimalsType);
  const empireumaticsType = Object.values(EnumEmpireumaticsType);

  const woodType = Object.values(EnumWoodType);
  const chemicalsAndEtherealType = Object.values(EnumChemicalsAndEtherealType);
  const lactealType = Object.values(EnumLactealType);
  const sweetsType = Object.values(EnumSweetsType);

  const [error, setError] = useState(false);

  const submitFormData = (e) => {
    e.preventDefault();
  };

  return (
    <Card style={{ marginTop: 50 }}>
      <Card.Header as="h5">Aromas</Card.Header>
      <Card.Body>
        <Form onSubmit={submitFormData}>

          <Row className="mb-3">
            <Form.Group as={Col} className='mb-3'>
              <Form.Label>Frutados</Form.Label>
              <Form.Select
                style={{ border: error ? '2px solid red' : '' }}
                name='fruitType'
                defaultValue=''
              >
                <option value='' disabled>Selecione o tipo de aroma</option>
                {Object.values(EnumFruitType).map((type, index) => (
                  <option key={index} value={type}>{type}</option>
                ))}
              </Form.Select>
            </Form.Group>

            <Form.Group as={Col} className='mb-3'>
              <Form.Label>Frutas secas</Form.Label>
              <Form.Select
                style={{ border: error ? '2px solid red' : '' }}
                name='dryFruitType'
                defaultValue=''
              >
                <option value='' disabled>Selecione o tipo de aroma</option>
                {Object.values(EnumDryFruitType).map((type, index) => (
                  <option key={index} value={type}>{type}</option>
                ))}
              </Form.Select>
            </Form.Group>

            <Form.Group as={Col} className='mb-3'>
              <Form.Label>Florais</Form.Label>
              <Form.Select
                style={{ border: error ? '2px solid red' : '' }}
                name='floralsType'
                defaultValue=''
              >
                <option value='' disabled>Selecione o tipo de aroma</option>
                {Object.values(EnumFloralsType).map((type, index) => (
                  <option key={index} value={type}>{type}</option>
                ))}
              </Form.Select>
            </Form.Group>

            <Form.Group as={Col} className='mb-3'>
              <Form.Label>Florais</Form.Label>
              <Form.Select
                style={{ border: error ? '2px solid red' : '' }}
                name='vegetablesAndHerbsType'
                defaultValue=''
              >
                <option value='' disabled>Selecione o tipo de aroma</option>
                {Object.values(EnumVegetablesAndHerbsType).map((type, index) => (
                  <option key={index} value={type}>{type}</option>
                ))}
              </Form.Select>
            </Form.Group>
          </Row>

          <Row className="mb-3">
            <Form.Group as={Col} className='mb-3'>
              <Form.Label>Minerais</Form.Label>
              <Form.Select
                style={{ border: error ? '2px solid red' : '' }}
                name='mineralsType'
                defaultValue=''
              >
                <option value='' disabled>Selecione o tipo de aroma</option>
                {Object.values(EnumMineralsType).map((type, index) => (
                  <option key={index} value={type}>{type}</option>
                ))}
              </Form.Select>
            </Form.Group>

            <Form.Group as={Col} className='mb-3'>
              <Form.Label>Especiarias</Form.Label>
              <Form.Select
                style={{ border: error ? '2px solid red' : '' }}
                name='spicesType'
                defaultValue=''
              >
                <option value='' disabled>Selecione o tipo de aroma</option>
                {Object.values(EnumSpicesType).map((type, index) => (
                  <option key={index} value={type}>{type}</option>
                ))}
              </Form.Select>
            </Form.Group>

            <Form.Group as={Col} className='mb-3'>
              <Form.Label>Animais</Form.Label>
              <Form.Select
                style={{ border: error ? '2px solid red' : '' }}
                name='animalsType'
                defaultValue=''
              >
                <option value='' disabled>Selecione o tipo de aroma</option>
                {Object.values(EnumAnimalsType).map((type, index) => (
                  <option key={index} value={type}>{type}</option>
                ))}
              </Form.Select>
            </Form.Group>

            <Form.Group as={Col} className='mb-3'>
              <Form.Label>Empireumáticos</Form.Label>
              <Form.Select
                style={{ border: error ? '2px solid red' : '' }}
                name='empireumaticsType'
                defaultValue=''
              >
                <option value='' disabled>Selecione o tipo de aroma</option>
                {Object.values(EnumEmpireumaticsType).map((type, index) => (
                  <option key={index} value={type}>{type}</option>
                ))}
              </Form.Select>
            </Form.Group>
          </Row>

          <Row className="mb-3">
            <Form.Group as={Col} className='mb-3'>
              <Form.Label>Madeira</Form.Label>
              <Form.Select
                style={{ border: error ? '2px solid red' : '' }}
                name='woodType'
                defaultValue=''
              >
                <option value='' disabled>Selecione o tipo de aroma</option>
                {Object.values(EnumWoodType).map((type, index) => (
                  <option key={index} value={type}>{type}</option>
                ))}
              </Form.Select>
            </Form.Group>

            <Form.Group as={Col} className='mb-3'>
              <Form.Label>Químicos e etéreos</Form.Label>
              <Form.Select
                style={{ border: error ? '2px solid red' : '' }}
                name='chemicalsAndEtherealType'
                defaultValue=''
              >
                <option value='' disabled>Selecione o tipo de aroma</option>
                {Object.values(EnumChemicalsAndEtherealType).map((type, index) => (
                  <option key={index} value={type}>{type}</option>
                ))}
              </Form.Select>
            </Form.Group>

            <Form.Group as={Col} className='mb-3'>
              <Form.Label>Lácteos</Form.Label>
              <Form.Select
                style={{ border: error ? '2px solid red' : '' }}
                name='lactealType'
                defaultValue=''
              >
                <option value='' disabled>Selecione o tipo de aroma</option>
                {Object.values(EnumLactealType).map((type, index) => (
                  <option key={index} value={type}>{type}</option>
                ))}
              </Form.Select>
            </Form.Group>

            <Form.Group as={Col} className='mb-3'>
              <Form.Label>Adocicados</Form.Label>
              <Form.Select
                style={{ border: error ? '2px solid red' : '' }}
                name='sweetsType'
                defaultValue=''
              >
                <option value='' disabled>Selecione o tipo de aroma</option>
                {Object.values(EnumSweetsType).map((type, index) => (
                  <option key={index} value={type}>{type}</option>
                ))}
              </Form.Select>
            </Form.Group>
          </Row>

        </Form>
      </Card.Body>
    </Card>
    
  )
}

export default Aromas