import { useState } from 'react';
import { Card, Form } from 'react-bootstrap'
import Row from 'react-bootstrap/Row'
import Col from 'react-bootstrap/Col';

import EnumIntensityType from '../../../utils/enums/EnumIntensityType';
import EnumPersistenceType from '../../../utils/enums/EnumPersistenceType';
import EnumQualityType from '../../../utils/enums/EnumQualityType';

import EnumClassificationType from '../../../utils/enums/EnumClassificationType';
import Aromas from './Aromas';

const OlfactoryInspection = () => {
  const intensityType = Object.values(EnumIntensityType);
  const persistenceType = Object.values(EnumPersistenceType);
  const qualityType = Object.values(EnumQualityType);
  const classificationType = Object.values(EnumClassificationType);

  const [error, setError] = useState(false);

  const submitFormData = (e) => {
    e.preventDefault();
  };

  return (
    <Card style={{ marginTop: 10 }}>
      <Card.Header as="h5">Inspeção olfativa</Card.Header>
      <Card.Body>
        <Form onSubmit={submitFormData}>         

          <Row className="mb-3">
            <Form.Group as={Col} className='mb-3'>
              <Form.Label>Intensidade</Form.Label>
              <Form.Select
                style={{ border: error ? '2px solid red' : '' }}
                name='intensityType'
                defaultValue=''
              >
                <option value='' disabled>Selecione a intensidade olfativa</option>
                {Object.values(EnumIntensityType).map((type, index) => (
                  <option key={index} value={type}>{type}</option>
                ))}
              </Form.Select>
            </Form.Group>

            <Form.Group as={Col} className='mb-3'>
              <Form.Label>Persistência</Form.Label>
              <Form.Select
                style={{ border: error ? '2px solid red' : '' }}
                name='persistenceType'
                defaultValue=''
              >
                <option value='' disabled>Selecione a persistência olfativa</option>
                {Object.values(EnumPersistenceType).map((type, index) => (
                  <option key={index} value={type}>{type}</option>
                ))}
              </Form.Select>
            </Form.Group>

            <Form.Group as={Col} className='mb-3'>
              <Form.Label>Qualidade</Form.Label>
              <Form.Select
                style={{ border: error ? '2px solid red' : '' }}
                name='qualityType'
                defaultValue=''
              >
                <option value='' disabled>Selecione a qualidade olfativa</option>
                {Object.values(EnumQualityType).map((type, index) => (
                  <option key={index} value={type}>{type}</option>
                ))}
              </Form.Select>
            </Form.Group>
          </Row>

          <Row className="mb-3">
            <Aromas />
          </Row>

          <Row className="mb-3">
            <Form.Group as={Col} className='mb-3'>
              <Form.Label>Classificação</Form.Label>
              <Form.Select
                style={{ border: error ? '2px solid red' : '' }}
                name='classificationType'
                defaultValue=''
              >
                <option value='' disabled>Como você classifica os aspectos visuais do vinho?</option>
                {Object.values(EnumClassificationType).map((type, index) => (
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

export default OlfactoryInspection