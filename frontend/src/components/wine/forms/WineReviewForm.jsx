import { useState } from 'react'
import { Form, Card, Button } from 'react-bootstrap';
import Row from 'react-bootstrap/Row';

const WineReviewForm = ({ nextStep, handleFormData, prevStep, values }) => {
    const { whatTasted } = values;

    const [error, setError] = useState(false);

    const submitFormData = (e) => {
        e.preventDefault();
        nextStep();
    };

    return (
        <>
            <p style={{ textAlign: 'left', marginTop: 20 }}><strong>Vinho:</strong> {whatTasted}{" "}</p>
            <Form onSubmit={submitFormData}>

                <Form.Group as={Row} className='mb-3'>
                    <Form.Label as={Row}>Data da avaliação</Form.Label>
                    <Form.Control
                        style={{ border: error ? '2px solid red' : '' }}
                        name='whenTasted'
                        defaultValue={values.whenTasted}
                        type='date'
                        // placeholder="Informe o email"
                        onChange={handleFormData('whenTasted')}
                    />
                </Form.Group>

                <Form.Group as={Row} className='mb-3'>
                    <Form.Label as={Row}>Aspectos visuais</Form.Label>
                    <Form.Control
                        style={{ border: error ? '2px solid red' : '' }}
                        name='whatSaw'
                        defaultValue={values.whatSaw}
                        type='text'
                        placeholder="O que você viu?"
                        onChange={handleFormData('whatSaw')}
                    />
                </Form.Group>

                <Form.Group as={Row} className='mb-3'>
                    <Form.Label as={Row}>Aromas</Form.Label>
                    <Form.Control
                        style={{ border: error ? '2px solid red' : '' }}
                        name='whatAromas'
                        defaultValue={values.whatAromas}
                        type='text'
                        placeholder="Que aromas sentiu?"
                        onChange={handleFormData('whatAromas')}
                    />
                </Form.Group>

                <Form.Group as={Row} className='mb-3'>
                    <Form.Label as={Row}>Sabores</Form.Label>
                    <Form.Control
                        style={{ border: error ? '2px solid red' : '' }}
                        name='whatFlavors'
                        defaultValue={values.whatFlavors}
                        type='text'
                        placeholder="Que sabores percebeu?"
                        onChange={handleFormData('whatFlavors')}
                    />
                </Form.Group>

                <Form.Group as={Row} className='mb-3'>
                    <Form.Label as={Row}>Opinião geral</Form.Label>
                    <Form.Control
                        style={{ border: error ? '2px solid red' : '' }}
                        name='whatOpinion'
                        defaultValue={values.whatOpinion}
                        type='text'
                        placeholder="Qual a sua opinião geral sobre o vinho?"
                        onChange={handleFormData('whatOpinion')}
                    />
                </Form.Group>

                <Form.Group as={Row} className='mb-3'>
                    <Form.Label as={Row}>Pontuação</Form.Label>
                    <Form.Select onChange={handleFormData('pointScale')}>
                        <option>Qual pontuação você atribui ao vinho?</option>
                        <option value="CLASSIC">Um vinho incrível, sem falhas e prazeroso</option>
                        <option value="OUTSTANDING">Vinho excelente, com qualidade superior ao esperado</option>
                        <option value="VERYGOOD">Bom, possui qualidades muito interessantes e o vinho tem potencial</option>
                        <option value="GOOD">Vinho bem feito</option>
                        <option value="MEDIOCRE">Satisfatório, com falhas pequenas</option>
                        <option value="NOTRECOMMENDED">Não recomendado</option>
                    </Form.Select>
                </Form.Group>

                <div style={{ display: "flex", justifyContent: "space-around" }}>
                            <Button variant="primary" onClick={prevStep}>
                                Previous
                            </Button>

                            <Button variant="primary" type="submit">
                                Submit
                            </Button>
                        </div>

            </Form>
        </>
    )
}

export default WineReviewForm